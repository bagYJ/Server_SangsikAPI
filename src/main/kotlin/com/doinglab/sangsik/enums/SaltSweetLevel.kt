package com.doinglab.sangsik.enums

import com.doinglab.sangsik.interfaces.EnumConvert
import jakarta.persistence.AttributeConverter

enum class SaltSweetLevel(
    override val value: Int,
    override val desc: String
): EnumConvert {
    SMALL(0, "적음"), NORMAL(1, "보통"), MANY(2, "많음");

    @jakarta.persistence.Converter
    class Converter: AttributeConverter<SaltSweetLevel, Int> {
        override fun convertToDatabaseColumn(sweetLevel: SaltSweetLevel?) = sweetLevel?.value

        override fun convertToEntityAttribute(value: Int?): SaltSweetLevel? = entries.firstOrNull { it.value == value }
    }
}
