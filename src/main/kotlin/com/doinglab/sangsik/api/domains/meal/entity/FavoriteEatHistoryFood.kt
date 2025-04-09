package com.doinglab.sangsik.api.domains.meal.entity

import jakarta.persistence.*

@Entity(name = "FavoriteEatHistoryFood")
class FavoriteEatHistoryFood(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val userId: Long,
    val favoriteId: Long,
    @Convert(converter = EatHistoryFood.Dto.Converter::class)
    val foodInfo: List<EatHistoryFood.Dto>,
) {
    data class Dto(
        val id: Long,
        val userId: Long,
        val favoriteId: Long,
        val foodInfo: List<EatHistoryFood.Dto>
    ) {
        fun toEntity() = FavoriteEatHistoryFood(id, userId, favoriteId, foodInfo)

        constructor(userId: Long, favoriteId: Long, foods: List<EatHistoryFood.Dto>) : this(
            id = 0L,
            userId = userId,
            favoriteId = favoriteId,
            foodInfo = foods
        )
    }

    fun toDto() = Dto(id, userId, favoriteId, foodInfo)
}
