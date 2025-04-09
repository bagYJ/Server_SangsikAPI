package com.doinglab.sangsik.api.domains.user.dto.request

import com.doinglab.sangsik.Exception.CustomException
import com.doinglab.sangsik.enums.LoginSource
import com.doinglab.sangsik.enums.StatusCode
import com.doinglab.sangsik.utils.checkEmail
import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "비밀번호 재설정")
data class RequestPutUserPasswordDto(
    @Schema(description = "이메일")
    val email: String,
    @Schema(description = "재설정 코드")
    val code: String,
    @Schema(description = "비밀번호")
    val password: String,
    @Schema(description = "비밀번호 확인")
    val verifyPassword: String,
    @JsonIgnore
    val loginSource: LoginSource = LoginSource.LOCAL
) {
    init {
        if (!email.trim().checkEmail()) throw CustomException(StatusCode.CHECK_EMAIL_PATTERN)
        if (code.length != 6) throw CustomException(StatusCode.CHECK_CODE_LENGTH)
        if (password.length < 8) throw CustomException(StatusCode.CHECK_PASSWORD_LENGTH)
        if (password != verifyPassword) throw CustomException(StatusCode.UNSAMED_PASSWORD)
    }
}
