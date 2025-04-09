package com.doinglab.sangsik.api.domains.user.dto.request

import com.doinglab.sangsik.enums.Platform
import com.doinglab.sangsik.enums.PushType
import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "토큰정보")
data class RequestPutUserTokenDto(
    @Schema(description = "푸시토큰 정보")
    val token: String,
    @Schema(description = "유저 플랫폼")
    val platform: Platform,
    @Schema(description = "로케일정보 (ko, en...)")
    val locale: String? = null,
    @JsonIgnore
    val pushType: PushType = PushType.MESSAGE
)
