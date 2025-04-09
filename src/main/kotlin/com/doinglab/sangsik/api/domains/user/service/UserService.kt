package com.doinglab.sangsik.api.domains.user.service

import com.doinglab.sangsik.Exception.CustomException
import com.doinglab.sangsik.api.domains.auth.entity.ClientCompany
import com.doinglab.sangsik.api.domains.common.dto.CustomDto
import com.doinglab.sangsik.api.domains.common.dto.MailConfigDto
import com.doinglab.sangsik.api.domains.user.dto.request.*
import com.doinglab.sangsik.api.domains.user.dto.response.ResponseGetUserDto
import com.doinglab.sangsik.api.domains.user.dto.response.ResponseGetUserEmailAvailabilityDto
import com.doinglab.sangsik.api.domains.user.dto.response.ResponsePutUserPasswordCodeDto
import com.doinglab.sangsik.api.domains.user.dto.response.ResponseUserDto
import com.doinglab.sangsik.api.domains.user.entity.*
import com.doinglab.sangsik.api.domains.user.repo.UserRepo
import com.doinglab.sangsik.enums.LoginSource
import com.doinglab.sangsik.enums.StatusCode
import com.doinglab.sangsik.enums.UnitHeight
import com.doinglab.sangsik.enums.UnitWeight
import com.doinglab.sangsik.utils.*
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class UserService(
    private val userRepo: UserRepo,
    private val mail: MailConfigDto
) {
    private val logger = getLogger()

    fun doGetUser(user: User.Dto): ResponseGetUserDto =
        ResponseGetUserDto(user, user.dietProgram, user.agreement)

    @Caching(evict = [
        CacheEvict(key = "#user.id", value = ["enrolledUser"], beforeInvocation = false),
        CacheEvict(key = "#user.accessToken", value = ["user"], beforeInvocation = false)
    ])
    fun doPutUser(user: User.Dto, request: RequestPutUserDto): ResponseGetUserDto =
        userRepo.saveUser(user.toApply(request))?.let {
            ResponseGetUserDto(it, user.dietProgram, user.agreement)
        } ?: throw CustomException(StatusCode.FAIL_UPDATE)

    @Caching(evict = [
        CacheEvict(key = "#user.id", value = ["enrolledUser"], beforeInvocation = false),
        CacheEvict(key = "#user.accessToken", value = ["user"], beforeInvocation = false)
    ])
    fun doPutUserUnitHeight(user: User.Dto, unit: UnitHeight): ResponseGetUserDto =
        userRepo.saveUser(user.toApply(unit))?.let {
            ResponseGetUserDto(it, user.dietProgram, user.agreement)
        } ?: throw CustomException(StatusCode.FAIL_UPDATE)

    @Caching(evict = [
        CacheEvict(key = "#user.id", value = ["enrolledUser"], beforeInvocation = false),
        CacheEvict(key = "#user.accessToken", value = ["user"], beforeInvocation = false)
    ])
    fun doPutUserUnitWeight(user: User.Dto, unit: UnitWeight): ResponseGetUserDto =
        userRepo.saveUser(user.toApply(unit))?.let {
            ResponseGetUserDto(it, user.dietProgram, user.agreement)
        } ?: throw CustomException(StatusCode.FAIL_UPDATE)

    fun doPostUser(request: RequestPostUserDto, company: ClientCompany.Dto): ResponseUserDto =
        when (request.loginSource) {
            LoginSource.LOCAL -> userRepo.findUserByEmail(request.emailId, request.loginSource).let {
                if (it != null) throw CustomException(StatusCode.ALREADY_EMAIL)
                else {
                    val token = TokenGenerator.generateNewToken()
                    val id = userRepo.saveUser(
                        User.Dto(token, request, company.id.toInt()).apply {
                            password = request.password!!.sha256()
                        }
                    )?.id ?: throw CustomException(StatusCode.FAIL_INSERT)

                    ResponseUserDto(id, token, true)
                }
            }
            else -> userRepo.findUserByFacebookId(request.socialId!!, request.loginSource).let { user ->
                if (user != null) ResponseUserDto(user)
                else {
                    val token = TokenGenerator.generateNewToken()
                    val id = userRepo.saveUser(
                        User.Dto(token, request, company.id.toInt())
                    )?.id ?: throw CustomException(StatusCode.FAIL_INSERT)

                    ResponseUserDto(id, token, true)
                }
            }
        }.apply {
            userRepo.saveUserAgreement(UserAgreement.Dto(body.id, request))
        }

    fun doPutUserLogin(request: RequestUserDto): ResponseUserDto =
        when (request.loginSource) {
            LoginSource.LOCAL -> userRepo.findUserByEmail(request.emailId, request.loginSource)?.apply {
                if (password != request.password?.sha256()) throw CustomException(StatusCode.UNSAMED_PASSWORD)
            }
            else -> userRepo.findUserByFacebookId(request.socialId!!, request.loginSource)
        }?.let { user ->
            if (user.firstlogin) {
                user.apply {
                    firstlogin = false
                    lastLoginDate = LocalDateTime.now()
                }
                userRepo.saveUser(user)
            }

            ResponseUserDto(user)
        } ?: throw CustomException(StatusCode.NOT_FOUND_USER)

    @Caching(evict = [
        CacheEvict(key = "#user.id", value = ["enrolledUser"], beforeInvocation = false),
        CacheEvict(key = "#user.accessToken", value = ["user"], beforeInvocation = false)
    ])
    fun doPutUserToken(user: User.Dto, request: RequestPutUserTokenDto): CustomDto {
        userRepo.saveUserPushToken(UserPushToken.Dto(user.id, request))
        request.locale?.let { locale ->
            userRepo.saveUser(user.id, locale)
        }

        return CustomDto()
    }

    fun doGetUserEmailAvailability(email: String): ResponseGetUserEmailAvailabilityDto {
        if (!email.trim().checkEmail()) throw CustomException(StatusCode.CHECK_EMAIL_PATTERN)

        return ResponseGetUserEmailAvailabilityDto(email, userRepo.findUserByEmail(email, LoginSource.LOCAL) == null)
    }

    fun doPostUserEmailPasswordReset(request: RequestUserEmailDto): CustomDto {
        val mailConfig = when (request.locale) {
            "en" -> mail.en
            else -> mail.ko
        }
        val mailInfo = userRepo.findUserByEmail(request.email, request.loginSource)?.let {
            val code = TokenGenerator.getRandomNumber(6)
            userRepo.saveUser(
                it.apply {
                    resetpasswordCode = code
                    resetpasswordLimit = LocalDateTime.now().plusMinutes(mail.passwordCodeLimit)
                }
            )
            Triple(mailConfig.resetPasswordCode.title, mailConfig.resetPasswordCode.resource, code)
        } ?: Triple(mailConfig.checkEmail.title, mailConfig.checkEmail.resource, "")

        UtilsMail.send(
            to = request.email,
            subject = mailInfo.first,
            contents = javaClass.getResource(mailInfo.second)!!.readText()
                .replace("{{CODE}}", mailInfo.third)
                .replace("{{LIMIT}}", mail.passwordCodeLimit.toString())
                .replace("{{PERSONAL}}", mailConfig.personal)
                .replace("{{ADDRESS}}", mailConfig.address)
                .replace("{{EMAIL}}", mail.from)
        )

        return CustomDto()
    }

    fun doPutUserPasswordCode(request: RequestPutUserPasswordCodeDto): ResponsePutUserPasswordCodeDto =
        userRepo.findUserByEmail(request.email, request.loginSource)?.let { user ->
            user.resetpasswordLimit?.run {
                if (this < LocalDateTime.now()) throw CustomException(StatusCode.EXPIRED_CODE)
            } ?: throw CustomException(StatusCode.EXPIRED_CODE)
            user.resetpasswordCode?.run {
                if (this != request.code) throw CustomException(StatusCode.UNSAMED_CODE)
            } ?: throw CustomException(StatusCode.EMPTY_CODE)

            ResponsePutUserPasswordCodeDto(user.email, request.code)
        } ?: throw CustomException(StatusCode.NOT_FOUND_USER)

    fun doPutUserPassword(request: RequestPutUserPasswordDto): CustomDto =
        userRepo.findUserByEmail(request.email, request.loginSource)?.let { user ->
            user.resetpasswordCode?.run {
                if (this != request.code) throw CustomException(StatusCode.UNSAMED_CODE)
            } ?: throw CustomException(StatusCode.EMPTY_CODE)

            userRepo.saveUser(user.apply {
                password = request.password.sha256()
                resetpasswordCode = null
                resetpasswordLimit = null
            })

            CustomDto()
        } ?: throw CustomException(StatusCode.NOT_FOUND_USER)

    fun doPostUserAgreement(user: User.Dto, request: RequestPostUserAgreementDto): CustomDto =
        userRepo.findUserAgreement(user.id)?.let { userAgreement ->
            userRepo.saveUserAgreement(userAgreement.toApply(request))?.let {
                CustomDto()
            } ?: throw CustomException(StatusCode.FAIL_INSERT)
        } ?: throw CustomException(StatusCode.NOT_FOUND_USER)

    fun doPutUserAgreementMarketing(userId: Long, isSelectablePersonalMarketing: Boolean) =
        userRepo.findUserAgreement(userId)?.let { userAgreement ->
            userRepo.saveUserAgreement(userAgreement.toApply(isSelectablePersonalMarketing))?.let {
                CustomDto()
            } ?: throw CustomException(StatusCode.FAIL_UPDATE)
        } ?: throw CustomException(StatusCode.NOT_FOUND_USER)

    @Caching(evict = [
        CacheEvict(key = "#user.id", value = ["enrolledUser"], beforeInvocation = false),
        CacheEvict(key = "#user.accessToken", value = ["user"], beforeInvocation = false)
    ])
    fun doDeleteUser(user: User.Dto, request: RequestDeleteUserDto): CustomDto {
        try {
            val withdrawalAnswer = mutableListOf<UserWithdrawalAnswer.Dto>()
            request.answerId.map {
                withdrawalAnswer.add(UserWithdrawalAnswer.Dto(it, request))
            }
            userRepo.saveAllUserWithdrawalAnswer(withdrawalAnswer)
            userRepo.saveUserWithdrawal(UserWithdrawal.Dto(user))
            userRepo.withdrawal(user)

            return CustomDto()
        } catch (e: Exception) {
            throw CustomException(StatusCode.FAIL_INSERT)
        }
    }

    fun doPostUserPasswordCheck(user: User.Dto, password: String): CustomDto =
        user.let {
            if (it.password != password.sha256()) throw CustomException(StatusCode.UNSAMED_PASSWORD)

            CustomDto()
        }

    @Caching(evict = [
        CacheEvict(key = "#user.id", value = ["enrolledUser"], beforeInvocation = false),
        CacheEvict(key = "#user.accessToken", value = ["user"], beforeInvocation = false)
    ])
    fun doPutUserPasswordChange(user: User.Dto, request: RequestPutUserPasswordChangeDto): CustomDto {
        user.password = request.password.sha256()

        return userRepo.saveUser(user)?.let {
            CustomDto()
        } ?: throw CustomException(StatusCode.FAIL_UPDATE)
    }

    @Caching(evict = [
        CacheEvict(key = "#user.id", value = ["enrolledUser"], beforeInvocation = false),
        CacheEvict(key = "#user.accessToken", value = ["user"], beforeInvocation = false)
    ])
    fun doGetUserLogout(user: User.Dto): CustomDto =
        userRepo.deleteUserPushToken(user.id).let {
            CustomDto()
        }

    @Cacheable(key = "#accessToken", value = ["user"], unless = "#result == null")
    fun findUserByAccessToken(accessToken: String): User.Dto? =
        userRepo.findUserByAccessToken(accessToken)

    fun findUser(userId: Long): User.Dto? =
        userRepo.findUserById(userId)

    fun findUsers(userIds: List<Long>): List<User.Dto>? =
        userRepo.findUserByIds(userIds)

}
