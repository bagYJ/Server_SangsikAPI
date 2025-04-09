package com.doinglab.sangsik.api.domains.meal.dto.response

import com.doinglab.sangsik.api.amazon.s3.S3FileUrlGetter
import com.doinglab.sangsik.api.domains.common.dto.BaseDto
import com.doinglab.sangsik.api.domains.meal.dto.response.ResponseGetMealDto.GetMealBody.Nutrients
import com.doinglab.sangsik.api.domains.meal.entity.EatHistoryFood
import com.doinglab.sangsik.api.domains.meal.entity.RepeatEatHistory
import io.swagger.v3.oas.annotations.media.Schema

data class ResponseGetRepeatIdDto(
    override val body: GetFavoriteIdBody
): BaseDto() {
    data class GetFavoriteIdBody(
        @Schema(description = "즐겨찾기 ID")
        val id: Long,
        @Schema(description = "즐겨찾기명")
        val title: String,
        @Schema(description = "이미지")
        val imagePath: String?,
        @Schema(description = "칼로리")
        val calorie: Double,
        @Schema(description = "권장 영양소")
        val recommend: Nutrients,
        @Schema(description = "총 영양소")
        val total: Nutrients?,
    )

    constructor(
        repeatEatHistory: RepeatEatHistory.Dto,
        repeatEatHistoryFood: List<EatHistoryFood.Dto>,
        calorie: Double,
        s3EatHistoryImageFileUrlGetter: S3FileUrlGetter
    ): this(
        body = GetFavoriteIdBody(
            id = repeatEatHistory.id,
            title = repeatEatHistory.title,
            imagePath = repeatEatHistory.imagePath?.takeIf { it.isNotEmpty() }?.let { s3EatHistoryImageFileUrlGetter.getFileUrl(it) },
            calorie = repeatEatHistory.calorie,
            recommend = ResponseGetMealDto.makeNutrient(calorie),
            total = ResponseGetMealDto.makeNutrient(repeatEatHistoryFood)
        )
    )
}
