package com.doinglab.sangsik.api.domains.user.dto.request

import com.doinglab.sangsik.Exception.CustomException
import com.doinglab.sangsik.Exception.RequestDataInvalidException
import com.doinglab.sangsik.enums.LoginSource
import com.doinglab.sangsik.enums.StatusCode
import com.doinglab.sangsik.utils.checkEmail
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email

@Schema(description = "로그인/회원가입")
data class RequestUserDto(
    @Schema(description = "이메일 아이디")
    @field:Email
    val emailId: String,
    @Schema(description = "이메일 패스워드")
    val password: String?,
    @Schema(description = "SNS 아이디")
    val socialId: String?,
    @Schema(description = "유저가 사용할 로그인/회원가입 방법")
    val loginSource: LoginSource,
//    @Schema(description = "이름 (회원가입시에만 사용)")
//    val name: String?
) {
    init {
        when (loginSource) {
            LoginSource.LOCAL -> {
                if (password.isNullOrEmpty()) throw RequestDataInvalidException("password is empty")
                if (!emailId.trim().checkEmail()) throw CustomException(StatusCode.CHECK_EMAIL_PATTERN)
            }
            else -> if (socialId.isNullOrEmpty()) throw RequestDataInvalidException("social id is empty")
        }
    }
}
