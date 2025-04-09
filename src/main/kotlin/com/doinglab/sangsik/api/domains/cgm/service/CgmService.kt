package com.doinglab.sangsik.api.domains.cgm.service

import com.doinglab.sangsik.Exception.CustomException
import com.doinglab.sangsik.api.aes.dto.AesDto
import com.doinglab.sangsik.api.amazon.mq.ActiveMQService
import com.doinglab.sangsik.api.cgm.dto.CgmConfigDto
import com.doinglab.sangsik.api.cgm.dto.response.ResponseCgmAccountPostTokenDto
import com.doinglab.sangsik.api.cgm.dto.response.ResponseDoGetCgmsDto
import com.doinglab.sangsik.api.cgm.dto.response.ResponseDoGetSensorsDto
import com.doinglab.sangsik.api.domains.cgm.dto.UserLocaleDto
import com.doinglab.sangsik.api.domains.cgm.dto.response.ResponseGetAuthorizeDto
import com.doinglab.sangsik.api.domains.cgm.entity.UserCgmBloodGlucose
import com.doinglab.sangsik.api.domains.cgm.entity.UserCgmToken
import com.doinglab.sangsik.api.domains.cgm.repo.CgmRepo
import com.doinglab.sangsik.api.domains.common.dto.BaseDto
import com.doinglab.sangsik.api.domains.common.service.SlackService
import com.doinglab.sangsik.api.domains.dietProgram.service.DietProgramService
import com.doinglab.sangsik.api.domains.user.entity.User
import com.doinglab.sangsik.api.domains.user.repo.UserRepo
import com.doinglab.sangsik.api.domains.user.service.UserService
import com.doinglab.sangsik.define.CoreDefine.Companion.DASH
import com.doinglab.sangsik.define.CoreDefine.Companion.MESSAGE_QUEUE
import com.doinglab.sangsik.define.CoreTime.Companion.YYYY_MM_DD_T_HH_MM_SS_XXX
import com.doinglab.sangsik.define.CoreTime.Companion.ZONE_SEOUL
import com.doinglab.sangsik.enums.CgmStage
import com.doinglab.sangsik.enums.StatusCode
import com.doinglab.sangsik.utils.*
import jakarta.servlet.http.HttpServletRequest
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.utils.URIBuilder
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class CgmService(
    private val activeMQService: ActiveMQService,
    private val dietProgramService: DietProgramService,
    private val slackService: SlackService,
    private val userService: UserService,
    private val userRepo: UserRepo,
    private val cgmRepo: CgmRepo,
    private val request: HttpServletRequest,
    private val cgmConfig: CgmConfigDto,
    private val aesProperties: AesDto
) {
    private val logger = getLogger()

    @Value("\${activeMQ.cgmQueue.name}")
    private lateinit var queueName: String
    @Value("\${activeMQ.cgmQueue.group.scrap}")
    private lateinit var cgmGroupScrap: String
    @Value("\${activeMQ.cgmQueue.group.batch}")
    private lateinit var cgmGroupBatch: String

    private val httpClients = HttpClients.custom()
        .setDefaultRequestConfig(RequestConfig.custom()
            .setConnectTimeout(cgmConfig.timeout)
            .setSocketTimeout(cgmConfig.timeout)
            .setConnectionRequestTimeout(cgmConfig.timeout)
            .build())
        .build()

    fun doGetCgmAuthorize(user: User.Dto): ResponseGetAuthorizeDto =
        cgmConfig.account.authorize.let {
            ResponseGetAuthorizeDto(body = ResponseGetAuthorizeDto.GetAuthorizeBody(
                "${cgmConfig.account.uri}${it.path}?${it.typeName}=${it.type}&client_id=${cgmConfig.clientId}&redirect_uri=${cgmConfig.redirectUri}&state=${UserLocaleDto(user.id, user.locale).serialize().encrypt(aesProperties.aesKey)}&scope=offline_access",
                cgmConfig.goodsUri
            ))
        }

    fun doGetCgmConnect(code: String, state: String): String =
        state.decrypt(aesProperties.aesKey).deserialzie<UserLocaleDto>().let { user ->
            userRepo.findUserById(user.userId)?.let {
                activeMQService.sendMessage(code, it.id.toInt(), cgmGroupScrap, queueName)

                "${when (user.locale) {
                    "ko", "kr" -> "ko"
                    else -> "en"
                }}/connect"
            } ?: throw CustomException(StatusCode.NOT_FOUND_USER)
        }

    fun doDeleteCgmDisconnect(user: User.Dto): BaseDto =
        findUserCgmToken(user.id)?.let {
            try {
                cgmRepo.deleteUserCgmToken(user)
                MESSAGE_QUEUE.remove("${user.id}-$cgmGroupScrap")
                MESSAGE_QUEUE.remove("${user.id}-$cgmGroupBatch")
                BaseDto()
            } catch (e: Exception) {
                throw CustomException(StatusCode.FAIL_DELETE)
            }
        } ?: throw CustomException(StatusCode.NOT_FOUND_USER)

    fun doPostCgm(user: User.Dto): BaseDto =
        findUserCgmToken(user.id)?.let {
            activeMQService.sendMessage(DASH, user.id.toInt(), cgmGroupScrap, queueName, user.uuid)

            BaseDto()
        } ?: throw CustomException(StatusCode.NOT_FOUND_USER)

    fun doPostCgmRefresh() =
        cgmRepo.findUserCgmTokens()?.let { tokens ->
            userService.findUsers(tokens.map { it.userId })?.map { user ->
                cgmRefresh(user, tokens.first { it.userId == user.id })
            }
        }

    fun findUserCgmToken(userId: Long): UserCgmToken.Dto? =
        cgmRepo.findUserCgmToken(userId)

    fun findUserCgmBloodGlucose(user: User.Dto, sdt: LocalDateTime, edt: LocalDateTime): List<UserCgmBloodGlucose.Dto>? {
        val cgmBloodSugars = cgmRepo.findUserCgmBloodGlucose(user.id, sdt, edt)
        val cgmList: MutableList<UserCgmBloodGlucose.Dto> = mutableListOf()
        try {
            user.cgmCalledAt?.apply {
                if (this < LocalDateTime.now().minusMinutes(5) && (cgmBloodSugars.isNullOrEmpty() || cgmBloodSugars.maxOfOrNull { it.eventAt }?.isAfter(this) == true)) {
                    cgmRepo.findUserCgmToken(user.id)?.let { token ->
                        getCgms(ResponseCgmAccountPostTokenDto(token, aesProperties.aesKey), sdt, edt).let { cgms ->
                            cgmList.addAll(cgms.map { UserCgmBloodGlucose.Dto(user.id, it, aesProperties.aesKey) })

                            activeMQService.sendMessage(DASH, user.id.toInt(), cgmGroupScrap, queueName, user.uuid)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            slackService.sendMessage(request, e)
            logger.error("Cgm Error: ${e.stackTraceToString()}")
        }

        return (cgmBloodSugars?.plus(cgmList))?.sortedBy { it.eventAt }
    }

    fun findUserCgmBloodGlucose(user: User.Dto, date: LocalDate): List<UserCgmBloodGlucose.Dto>? =
        findUserCgmBloodGlucose(user, date.atStartOfDay(), date.atTime(23, 59, 59))

    fun findUserCgmTokens(userId: Long): List<UserCgmToken.ConnectDate>? =
        cgmRepo.findUserCgmTokens(userId)?.map {
            UserCgmToken.ConnectDate(it.createdAt.toLocalDate(), it.disconnectedAt?.toLocalDate() ?: LocalDate.now())
        }

    // ActiveMQ 사용
    fun connectCgm(code: String, userId: Long) =
        try {
            userService.findUser(userId)?.let { user ->
                httpPost(cgmConfig.tokenUri(), listOf(
                    Pair("grant_type", cgmConfig.account.token.type),
                    Pair("code", code),
                    Pair("client_id", cgmConfig.clientId),
                    Pair("client_secret", cgmConfig.clientSecret),
                    Pair("redirect_uri", cgmConfig.redirectUri),
                )).deserialzie<ResponseCgmAccountPostTokenDto>().let { response ->
                    logger.info("connect - user id: ${user.id}, access token: ${response.accessToken}, refresh token: ${response.refreshToken}")

                    cgmRepo.findUserCgmToken(response.userId)?.let { userCgmToken ->
                        if (userId != userCgmToken.userId) {
                            userService.findUser(userCgmToken.userId)?.let {
                                cgmRepo.deleteUserCgmToken(it)
                            }
                        }
                    }
                    cgmRepo.insertUserCgmToken(UserCgmToken.Dto(user, response, aesProperties.aesKey))
                }
            } ?: throw CustomException(StatusCode.NOT_FOUND_USER)
        } catch (e: Exception) {
            logger.error(e.stackTraceToString())
        }

    fun refreshCgmTokenForStaffId(staffId: Long) =
        dietProgramService.findActiveDietProgramEnrolledUserForStaffId(staffId)?.map { it.userId }?.let {
            cgmRepo.findUserCgmTokens(it)?.let { userCgmTokens ->
                findUserAndInsertUserCgmBloodGlucose(userCgmTokens)
            }
        }

    fun findUserAndInsertUserCgmBloodGlucose(userCgmTokens: List<UserCgmToken.Dto>) =
        userService.findUsers(userCgmTokens.map { it.userId })?.let { users ->
            userCgmTokens.map { token ->
                users.firstOrNull { it.id == token.userId }?.let { user ->
                    insertUserCgmBloodGlucose(user, ResponseCgmAccountPostTokenDto(token, aesProperties.aesKey), token)
                }
            }
        }

    fun insertUserCgmBloodGlucose(userId: Long) =
        userService.findUser(userId)?.let { user ->
            cgmRepo.findUserCgmToken(userId)?.let { token ->
                insertUserCgmBloodGlucose(user, ResponseCgmAccountPostTokenDto(token, aesProperties.aesKey), token)
            }
        }

    fun insertUserCgmBloodGlucose(user: User.Dto, token: ResponseCgmAccountPostTokenDto, recentToken: UserCgmToken.Dto, tokenUpdated: Boolean = false): LocalDateTime =
        try {
            getCgms(token, (recentToken.cgmCalledAt ?: getSensors(token).maxOfOrNull { it.startedAt } ?: recentToken.updatedAt), LocalDateTime.now()).let { cgms ->
                cgmRepo.insertUserCgmBloodGlucose(user.id, cgms, aesProperties.aesKey)

                when (cgms.isNotEmpty()) {
                    true -> cgms.filter { CgmStage.of(it.stage) in CgmStage.notComplete && it.eventAt > LocalDateTime.now().minusHours(1) }.minOfOrNull { it.eventAt } ?: cgms.maxOf { it.eventAt }
                    false -> LocalDateTime.now()
                }
            }.apply {
                when (tokenUpdated) {
                    true -> cgmRepo.updateUserCgmToken(user, token, aesProperties.aesKey, this, LocalDateTime.now())
                    false -> cgmRepo.updateUserCgmToken(user, this)
                }
            }
        } catch (e: Exception) {
            logger.error("error cgm : ${e.stackTraceToString()}")
        } as LocalDateTime
    // ActiveMQ 사용

    private fun getCgms(token: ResponseCgmAccountPostTokenDto, start: LocalDateTime, end: LocalDateTime): List<ResponseDoGetCgmsDto> =
        httpGet(cgmConfig.cgmsUri(), token.accessToken, listOf(
            Pair("start", start.atZone(ZONE_SEOUL).format(YYYY_MM_DD_T_HH_MM_SS_XXX)),
            Pair("end", end.atZone(ZONE_SEOUL).format(YYYY_MM_DD_T_HH_MM_SS_XXX))
        )).deserialzie<List<ResponseDoGetCgmsDto>>()

    private fun getSensors(token: ResponseCgmAccountPostTokenDto): List<ResponseDoGetSensorsDto> =
        httpGet(cgmConfig.sensorsUri(), token.accessToken).deserialzie<List<ResponseDoGetSensorsDto>>()

    private fun cgmRefresh(user: User.Dto, recentToken: UserCgmToken.Dto) =
        try {
            httpPost(cgmConfig.refreshUri(), listOf(
                Pair("grant_type", cgmConfig.account.refresh.type),
                Pair("client_id", cgmConfig.clientId),
                Pair("client_secret", cgmConfig.clientSecret),
                Pair("refresh_token", recentToken.refreshToken.decrypt(aesProperties.aesKey)),
            )).deserialzie<ResponseCgmAccountPostTokenDto>().let { token ->
                logger.info("refresh - user id: ${user.id}, access token: ${token.accessToken}, refresh token: ${token.refreshToken}")

                insertUserCgmBloodGlucose(user, token, recentToken, true)
            }
        } catch (e: Exception) {
            doDeleteCgmDisconnect(user)
            slackService.sendMessage(request, e)
            logger.error("cgmRefresh failed (${user.id})", e)
        }

    private fun httpPost(uri: String, params: List<Pair<String, String>>?): String =
        HttpPost(uri).let {
            ArrayList<BasicNameValuePair>().let { parameterList ->
                params?.map { (k, v) ->
                    parameterList.add(BasicNameValuePair(k, v))
                }
                it.entity = UrlEncodedFormEntity(parameterList)
            }
            EntityUtils.toString(httpClients.execute(it).entity)
        }

    private fun httpGet(uri: String, accessToken: String?, params: List<Pair<String, String>>? = null): String =
        URIBuilder(uri).let { builder ->
            params?.map { (k, v) ->
                builder.addParameter(k, v)
            }
            HttpGet(builder.build()).let { http ->
                if (accessToken?.isNotEmpty() == true) {
                    http.addHeader("Authorization", "Bearer $accessToken")
                }
                EntityUtils.toString(httpClients.execute(http).entity)
            }
        }
}
