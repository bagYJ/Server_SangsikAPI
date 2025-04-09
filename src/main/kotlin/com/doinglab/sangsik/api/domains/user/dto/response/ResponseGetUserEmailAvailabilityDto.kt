package com.doinglab.sangsik.api.domains.user.dto.response

import com.doinglab.sangsik.api.domains.common.dto.BaseDto
import io.swagger.v3.oas.annotations.media.Schema

@Schema
data class ResponseGetUserEmailAvailabilityDto(
    @Schema(description = "결과")
    override val body: GetUserEmailAvailabilityBodyDto
): BaseDto() {
    data class GetUserEmailAvailabilityBodyDto(
        @Schema(description = "이메일")
        val email: String,
        @Schema(description = "사용가능여부")
        val availability: Boolean
    )

    constructor(email: String, availability: Boolean) : this(GetUserEmailAvailabilityBodyDto(
        email = email,
        availability = availability
    ))
}

