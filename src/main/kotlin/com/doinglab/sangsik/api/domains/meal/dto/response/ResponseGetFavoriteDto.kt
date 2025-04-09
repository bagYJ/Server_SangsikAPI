package com.doinglab.sangsik.api.domains.meal.dto.response

import com.doinglab.sangsik.api.domains.common.dto.BaseDto
import com.doinglab.sangsik.api.domains.meal.entity.FavoriteEatHistory
import io.swagger.v3.oas.annotations.media.Schema

data class ResponseGetFavoriteDto(
    override val body: List<GetFavoriteBody>?
): BaseDto() {
    data class GetFavoriteBody(
        @Schema(description = "즐겨찾기 ID")
        val id: Long,
        @Schema(description = "즐겨찾기명")
        val title: String,
        @Schema(description = "이미지")
        val imagePath: String?,
        @Schema(description = "칼로리")
        val calorie: Double
    )

    companion object {
        fun create(favoriteEatHistories: List<FavoriteEatHistory.Dto>?) = ResponseGetFavoriteDto(favoriteEatHistories?.map { favoriteEatHistory ->
            GetFavoriteBody(
                id = favoriteEatHistory.id,
                title = favoriteEatHistory.title,
                imagePath = favoriteEatHistory.imagePath,
                calorie = favoriteEatHistory.calorie
            )
        })
    }
}
