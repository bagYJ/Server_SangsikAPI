package com.doinglab.sangsik.enums

import jakarta.persistence.AttributeConverter

enum class FoodlensVersion(
    val value: Int
) {
    FOODLENS(1), CALOAI(2);

    @jakarta.persistence.Converter
    class Converter: AttributeConverter<FoodlensVersion, Int> {
        override fun convertToDatabaseColumn(foodlensVersion: FoodlensVersion?) = foodlensVersion?.value

        override fun convertToEntityAttribute(value: Int?): FoodlensVersion? = entries.firstOrNull { it.value == value }
    }
}
