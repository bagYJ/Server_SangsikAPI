package com.doinglab.sangsik.api.domains.meal.dto.request

import io.swagger.v3.oas.annotations.media.Schema

data class RequestPostFavoriteDto(
    @Schema(description = "식단 ID")
    val eatHistoryId: Long,
    @Schema(description = "즐겨찾기명")
    val title: String
)
