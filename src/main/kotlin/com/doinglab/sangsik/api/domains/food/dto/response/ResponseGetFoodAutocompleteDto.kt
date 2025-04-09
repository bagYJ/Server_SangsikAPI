package com.doinglab.sangsik.api.domains.food.dto.response

import com.doinglab.sangsik.api.domains.common.dto.BaseDto
import io.swagger.v3.oas.annotations.media.Schema

data class ResponseGetFoodAutocompleteDto(
    override val body: List<GetFoodAutocompleteBody>,
): BaseDto() {
    data class GetFoodAutocompleteBody(
        @Schema(description = "음식 ID")
        val id: Long,
        @Schema(description = "음식명")
        val foodName: String,
        @Schema(description = "제조사")
        val manufacturer: String?,
        @Schema(description = "AI 파트 키")
        val predictKey: String?
    )
}
