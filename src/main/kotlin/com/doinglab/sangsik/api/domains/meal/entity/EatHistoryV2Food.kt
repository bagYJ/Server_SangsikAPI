package com.doinglab.sangsik.api.domains.meal.entity

import com.doinglab.sangsik.config.converter.NumericBooleanConverter
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity(name = "EatHistoryV2Food")
class EatHistoryV2Food(
    @Id
    val id: Long,
    val eatHistoryV2Id: Long,
    val userId: Long,
    val xMin: Int,
    val xMax: Int,
    val yMin: Int,
    val yMax: Int,
    val imgPath: String,
    val name: String,
    val fullName: String,
    val ingredientName: String,
    val eatAmount: Int,
    val gram: Double,
    val energy: Double,
    val carbohydrate: Double,
    val protein: Double,
    val fat: Double,
    val sugar: Double,
    val dietaryFiber: Double,
    val proteinGrains: Double,
    val proteinVegetable: Double,
    val energyFruit: Double,
    val energyVegetable: Double,
    @Convert(converter = NumericBooleanConverter::class)
    val isDeleted: Boolean,
    val updatedAt: LocalDateTime,
    val createdAt: LocalDateTime
)
