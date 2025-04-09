package com.doinglab.sangsik.enums

import com.doinglab.sangsik.interfaces.EnumConvert
import jakarta.persistence.AttributeConverter

enum class Gender(
    override val value: Int,
    override val desc: String,
    val calc: Int,
    val mass: Int
): EnumConvert {
    MALE(0, "남성", 5, 22), FEMALE(1, "여성", -161, 21);

    @jakarta.persistence.Converter
    class Converter: AttributeConverter<Gender, Int> {
        override fun convertToDatabaseColumn(gender: Gender?) = gender?.value

        override fun convertToEntityAttribute(value: Int?): Gender? = entries.firstOrNull { it.value == value }
    }
}
