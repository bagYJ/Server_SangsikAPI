package com.doinglab.sangsik.api.domains.meal.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "FavoriteLink")
class FavoriteLink(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val userId: Long,
    val favoriteId: Long,
    val eatHistoryId: Long
) {
    data class Dto(
        val id: Long,
        val userId: Long,
        val favoriteId: Long,
        val eatHistoryId: Long
    ) {
        fun toEntity() = FavoriteLink(id, userId, favoriteId, eatHistoryId)

        constructor(userId: Long, favoriteId: Long, eatHistoryId: Long): this(
            id = 0L,
            userId = userId,
            favoriteId = favoriteId,
            eatHistoryId = if (eatHistoryId > 0) eatHistoryId else userId
        )
    }

    fun toDto() = Dto(id, userId, favoriteId, eatHistoryId)
}
