package com.doinglab.sangsik.api.domains.user.dto.request

import com.doinglab.sangsik.Exception.CustomException
import com.doinglab.sangsik.enums.StatusCode
import io.swagger.v3.oas.annotations.media.Schema

data class RequestPutUserPasswordChangeDto(
    @Schema(description = "비밀번호")
    val password: String,
    @Schema(description = "비밀번호 확인")
    val verifyPassword: String,
) {
    init {
        if (password.length < 8) throw CustomException(StatusCode.CHECK_PASSWORD_LENGTH)
        if (password != verifyPassword) throw CustomException(StatusCode.UNSAMED_PASSWORD)
    }
}
