package com.doinglab.sangsik.config.converter

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class NumericBooleanConverter: AttributeConverter<Boolean, Int> {
    override fun convertToDatabaseColumn(p0: Boolean?): Int? =
        when (p0) {
            null -> null
            false -> 0
            else -> 1
        }

    override fun convertToEntityAttribute(p0: Int?): Boolean? =
        when (p0) {
            null -> null
            0 -> false
            else -> true
        }

}
