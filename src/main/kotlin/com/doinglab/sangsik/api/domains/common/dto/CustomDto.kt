package com.doinglab.sangsik.api.domains.common.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "")
data class CustomDto(
    override val body: String? = null
): BaseDto()
