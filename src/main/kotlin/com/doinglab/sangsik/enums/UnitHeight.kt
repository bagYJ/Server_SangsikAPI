package com.doinglab.sangsik.enums

import jakarta.persistence.AttributeConverter

enum class UnitHeight(
    val value: Int,
    val calc: Float
) {
    CM(0, 30.48F), FT(1, 30.48F);

    @jakarta.persistence.Converter
    class Converter: AttributeConverter<UnitHeight, Int> {
        override fun convertToDatabaseColumn(unit: UnitHeight?) = unit?.value

        override fun convertToEntityAttribute(value: Int?): UnitHeight? = entries.firstOrNull { it.value == value }
    }
}
