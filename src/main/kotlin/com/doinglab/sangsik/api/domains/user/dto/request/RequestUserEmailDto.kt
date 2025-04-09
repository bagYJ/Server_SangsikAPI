package com.doinglab.sangsik.api.domains.user.dto.request

import com.doinglab.sangsik.Exception.CustomException
import com.doinglab.sangsik.enums.LoginSource
import com.doinglab.sangsik.enums.StatusCode
import com.doinglab.sangsik.utils.checkEmail
import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "이메일정보")
data class RequestUserEmailDto(
    @Schema(description = "이메일")
    val email: String,
    @Schema(description = "로케일 (ko, en)")
    val locale: String? = "ko",
    @JsonIgnore
    val loginSource: LoginSource = LoginSource.LOCAL
) {
    init {
        if (!email.trim().checkEmail()) throw CustomException(StatusCode.CHECK_EMAIL_PATTERN)
    }
}
