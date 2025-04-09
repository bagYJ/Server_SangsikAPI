package com.doinglab.sangsik.api.domains.meal.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "FavoriteEatHistory")
class FavoriteEatHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val userId: Long,
    val eatHistoryId: Long,
    val title: String,
    val imagePath: String?,
    val calorie: Double = -1.0
) {
    data class Dto(
        val id: Long,
        val userId: Long,
        val eatHistoryId: Long,
        val title: String,
        val imagePath: String?,
        val calorie: Double = -1.0
    ) {
        fun toEntity() = FavoriteEatHistory(id, userId, eatHistoryId, title, imagePath, calorie)

        constructor(meal: EatHistory.Dto, title: String, calorie: Double): this(
            id = 0L,
            userId = meal.userId,
            eatHistoryId = meal.id,
            title = title,
            imagePath = meal.imgThumbPath,
            calorie = calorie
        )

        constructor(): this(
            id = 0L,
            userId = 0L,
            eatHistoryId = 0L,
            title = "",
            imagePath = "",
            calorie = 0.0
        )
    }

    fun toDto() = Dto(id, userId, eatHistoryId, title, imagePath, calorie)
}
