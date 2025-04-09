package com.doinglab.sangsik.api.domains.meal.entity

import jakarta.persistence.*

@Entity(name = "RepeatEatHistoryFood")
class RepeatEatHistoryFood(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val userId: Long,
    val repeatId: Long,
    @Convert(converter = EatHistoryFood.Dto.Converter::class)
    val foodInfo: List<EatHistoryFood.Dto>,
) {
    data class Dto(
        val id: Long,
        val userId: Long,
        val repeatId: Long,
        val foodInfo: List<EatHistoryFood.Dto>
    ) {
        fun toEntity() = RepeatEatHistoryFood(id, userId, repeatId, foodInfo)

        constructor(userId: Long, repeatId: Long, foods: List<EatHistoryFood.Dto>) : this(
            id = 0L,
            userId = userId,
            repeatId = repeatId,
            foodInfo = foods
        )
    }

    fun toDto() = Dto(id, userId, repeatId, foodInfo)
}
