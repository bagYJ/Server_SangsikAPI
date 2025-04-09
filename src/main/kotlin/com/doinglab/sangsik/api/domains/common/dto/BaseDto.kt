package com.doinglab.sangsik.api.domains.common.dto

import com.doinglab.sangsik.enums.StatusCode
import io.swagger.v3.oas.annotations.media.Schema

open class BaseDto(
    @Schema(description = "결과")
    open val body: Any? = null,
    @Schema(description = "성공여부")
    var success: Boolean = true,
    @Schema(description = "결과코드")
    var code: StatusCode = StatusCode.SUCCESS
)
