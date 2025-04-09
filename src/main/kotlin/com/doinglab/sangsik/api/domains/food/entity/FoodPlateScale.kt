package com.doinglab.sangsik.api.domains.food.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "FoodPlateScale")
class FoodPlateScale(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val className: String,
    val size: String,
    val sizeName: String,
    val min: Int,
    val max: Int,
    val minArea: Int,
    val maxArea: Int,
    val scale: Float,
    val sizeCheck: Int,
    val classKorName: String,
    val sizeKorName: String,
    val sizeDisplayName: String,
    val fullScale: Float,
    val imageUrl: String
) {
    data class Dto(
        val id: Long,
        val className: String,
        val size: String,
        val sizeName: String,
        val min: Int,
        val max: Int,
        val minArea: Int,
        val maxArea: Int,
        val scale: Float,
        val sizeCheck: Int,
        val classKorName: String,
        val sizeKorName: String,
        val sizeDisplayName: String,
        val fullScale: Float,
        val imageUrl: String
    )

    fun toDto() = Dto(id, className, size, sizeName, min, max, minArea, maxArea, scale, sizeCheck, classKorName, sizeKorName, sizeDisplayName, fullScale, imageUrl)
}
