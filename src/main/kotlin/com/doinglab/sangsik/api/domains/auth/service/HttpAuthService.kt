package com.doinglab.sangsik.api.domains.auth.service

import com.doinglab.sangsik.Exception.NotAllowedAccessException
import com.doinglab.sangsik.Exception.NotFoundException
import com.doinglab.sangsik.api.domains.auth.repo.ClientCompanyRepo
import com.doinglab.sangsik.api.domains.cgm.service.CgmService
import com.doinglab.sangsik.api.domains.dietProgram.service.DietProgramService
import com.doinglab.sangsik.api.domains.user.entity.User
import com.doinglab.sangsik.api.domains.user.repo.UserRepo
import com.doinglab.sangsik.api.domains.user.service.UserService
import com.doinglab.sangsik.define.CoreDefine.Companion.APP_NAME
import com.doinglab.sangsik.define.CoreDefine.Companion.AUTHORIZATION_TEXT_BASIC
import com.doinglab.sangsik.define.CoreDefine.Companion.IGNORE_URI_PATH
import com.doinglab.sangsik.enums.CustomCalorieType
import com.doinglab.sangsik.utils.authKeyDectypt
import com.doinglab.sangsik.utils.getLogger
import com.doinglab.sangsik.utils.sha256
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.HandlerMapping
import java.time.LocalDateTime

@Service
class HttpAuthService(
    private val userService: UserService,
    private val dietProgramService: DietProgramService,
    private val cgmService: CgmService,
    private val clientCompanyRepo: ClientCompanyRepo,
    private val userRepo: UserRepo
) {
    private val logger = getLogger()

    fun httpAuth(
        request: HttpServletRequest,
        response: HttpServletResponse
    ): Boolean {
        try {
            if (request.method == RequestMethod.OPTIONS.name) return true
            if (request.requestURI == "/error") return false

            if (IGNORE_URI_PATH.any { it == request.requestURI }) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND)
                throw NotFoundException()
            }

            (request.getHeader(HttpHeaders.AUTHORIZATION) ?: "")
                .authKeyDectypt(AUTHORIZATION_TEXT_BASIC)
                .split(":".toRegex()).run {
                    if (size != 2) {
                        logger.error("Header authorization key check. [${request.getHeader("authorization")}][$this]")
                        throw NotAllowedAccessException("Header authorization key check")
                    }

                    if (this[0].isEmpty() && this[1].isEmpty()) {
                        logger.error("Check authorization company auth param [$this]")
                        throw NotAllowedAccessException("Check authorization company auth param [$this]")
                    }

                    if (this[0] == APP_NAME.replaceFirstChar { it.lowercase() }) {
                        clientCompanyRepo.findClientCompany(this[0], this[1].sha256())?.let {
                            request.setAttribute("sangsikCompany", it)
                        } ?: throw NotAllowedAccessException("Check company is not match [$this]")
                    } else {
                        throw NotAllowedAccessException("Check company is not match [$this]")
                    }
                }

            request.getHeader("AccessToken")?.trim()?.run {
                userService.findUserByAccessToken(this )?.let { user ->
                    request.setAttribute("auth", user.apply {
                        User.Dto.DietProgram(dietProgramService.findDietProgramEnrolledUser(user.id, LocalDateTime.now())).let {
                            this.dietProgram = it
                            if (it.calorieIntegrate) {
                                this.customcalorie = it.recommendCalorie?.toInt() ?: 0
                                this.customcalorieDate = it.recommendCalorieDate
                                this.customCalorieType = CustomCalorieType.PARTNER
                            }
                        }
                        this.agreement = User.Dto.Agreement(userRepo.findUserAgreement(user.id))
                        cgmService.findUserCgmToken(user.id)?.let {
                            this.cgmConnect = true
                            this.cgmConnectDate = it.createdAt.toLocalDate()
                            this.cgmCalledAt = it.cgmCalledAt
                        }
                    })
                }
            }

            return true
        } catch (e: Exception) {
            logger.warn("Exception httpAuthNormal, url[${request.method}][${request.requestURI}]" +
                    "[${request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE)}]" +
                    "[${request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE)}]")
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
            return false
        }
    }
}
