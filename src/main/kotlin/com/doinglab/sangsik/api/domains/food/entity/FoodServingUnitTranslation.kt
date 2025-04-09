package com.doinglab.sangsik.api.domains.food.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "FoodServingUnitTranslation")
class FoodServingUnitTranslation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idx: Long,
    val unitKey: String,
    val nameKo: String? = "",
    val nameEn: String? = "",
    val nameJa: String? = "",
    val nameDe: String? = "",
    val nameEs: String? = "",
    val nameZh: String? = ""
) {
    data class Dto(
        val idx: Long,
        val unitKey: String,
        val nameKo: String? = "",
        val nameEn: String? = "",
        val nameJa: String? = "",
        val nameDe: String? = "",
        val nameEs: String? = "",
        val nameZh: String? = ""
    )

    fun toDto() = Dto(idx, unitKey, nameKo, nameEn, nameJa, nameDe, nameEs, nameZh)
}
