package com.doinglab.sangsik.enums

import jakarta.persistence.AttributeConverter

enum class UnitWeight(
    val value: Int,
    val calc: Float
) {
    KG(0, 2.205F), LBS(1, 2.205F);

    @jakarta.persistence.Converter
    class Converter: AttributeConverter<UnitWeight, Int> {
        override fun convertToDatabaseColumn(unit: UnitWeight?) = unit?.value

        override fun convertToEntityAttribute(value: Int?): UnitWeight? = entries.firstOrNull { it.value == value }
    }
}
