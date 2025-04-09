package com.doinglab.sangsik.api.domains.food.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name = "FoodInfoPlate")
class FoodInfoPlate(
    @Id
    val FoodNumber: Long,
    val className: String,
    val classSize: String
) {
    data class Dto(
        val FoodNumber: Long,
        val className: String,
        val classSize: String
    )

    fun toDto() = Dto(FoodNumber, className, classSize)
}
