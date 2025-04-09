package com.doinglab.sangsik.api.domains.meal.dto.response

import com.doinglab.sangsik.api.domains.common.dto.BaseDto
import com.doinglab.sangsik.api.domains.meal.entity.RepeatEatHistory
import io.swagger.v3.oas.annotations.media.Schema

data class ResponseGetRepeatDto(
    override val body: List<GetRepeatBody>?
): BaseDto() {
    data class GetRepeatBody(
        @Schema(description = "반복기록 ID")
        val id: Long,
        @Schema(description = "반복기록명")
        val title: String,
        @Schema(description = "이미지")
        val imagePath: String?,
        @Schema(description = "칼로리")
        val calorie: Double
    )

    companion object {
        fun create(repeatEatHistories: List<RepeatEatHistory.Dto>?) = ResponseGetRepeatDto(repeatEatHistories?.map { repeatEatHistory ->
            GetRepeatBody(
                id = repeatEatHistory.id,
                title = repeatEatHistory.title,
                imagePath = repeatEatHistory.imagePath,
                calorie = repeatEatHistory.calorie
            )
        })
    }
}
