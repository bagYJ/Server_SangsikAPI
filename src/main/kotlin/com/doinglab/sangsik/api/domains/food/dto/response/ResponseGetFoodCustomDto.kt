package com.doinglab.sangsik.api.domains.food.dto.response

import com.doinglab.sangsik.api.domains.common.dto.BaseDto
import com.doinglab.sangsik.api.domains.food.entity.FoodInfoR2
import io.swagger.v3.oas.annotations.media.Schema

data class ResponseGetFoodCustomDto(
    override val body: List<GetFoodCustomBody>?
): BaseDto() {
    data class GetFoodCustomBody(
        @Schema(description = "음식 ID")
        val id: Long,
        @Schema(description = "음식명")
        val foodName: String?,
        @Schema(description = "음식 칼로리")
        val calorie: Double?
    )

    constructor(foodInfos: List<FoodInfoR2.Dto>?, userId: Long): this(foodInfos?.map { foodInfo ->
        GetFoodCustomBody(foodInfo.foodNumber, foodInfo.koName, foodInfo.energy)
    })
}
