package com.doinglab.sangsik.api.domains.user.dto.response

import com.doinglab.sangsik.api.domains.common.dto.BaseDto
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "비밀번호 재설정 코드 확인")
data class ResponsePutUserPasswordCodeDto(
    override val body: PutUserPasswordCodeBodyDto
): BaseDto() {
    data class PutUserPasswordCodeBodyDto(
        @Schema(description = "이메일")
        val email: String,
        @Schema(description = "코드")
        val code: String
    )

    constructor(email: String, code: String): this(PutUserPasswordCodeBodyDto(
        email = email,
        code = code
    ))
}
