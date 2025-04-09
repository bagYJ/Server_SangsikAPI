package com.doinglab.sangsik.api.domains.meal.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "RepeatLink")
class RepeatLink(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val userId: Long,
    val repeatId: Long,
    val eatHistoryId: Long
) {
    data class Dto(
        val id: Long,
        val userId: Long,
        val repeatId: Long,
        val eatHistoryId: Long
    ) {
        fun toEntity() = RepeatLink(id, userId, repeatId, eatHistoryId)

        constructor(userId: Long, repeatId: Long, eatHistoryId: Long) : this(
            id = 0L,
            userId = userId,
            repeatId = repeatId,
            eatHistoryId = eatHistoryId
        )
    }

    fun toDto() = Dto(id, userId, repeatId, eatHistoryId)
}
