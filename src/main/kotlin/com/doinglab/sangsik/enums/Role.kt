package com.doinglab.sangsik.enums

import com.doinglab.sangsik.interfaces.EnumConvert
import jakarta.persistence.AttributeConverter

enum class Role(
    override val value: Int,
    override val desc: String
): EnumConvert {
    NONE(0, "미사용"), API(1, "API"), SIZE(2, "Size"), COORDINATE(4, "음식좌표"), INGREDIENTS(8, "재료정보"), NUTRIENT(16, "기본영양소정보");

    companion object {
        val foodLens = setOf(NONE, API, SIZE)
        val caloAi = setOf(NONE, API, SIZE, COORDINATE, INGREDIENTS, NUTRIENT)

    }

    @jakarta.persistence.Converter
    class Converter: AttributeConverter<Role, Int> {
        override fun convertToDatabaseColumn(role: Role?) = role?.value

        override fun convertToEntityAttribute(value: Int?): Role? = entries.firstOrNull { it.value == value }
    }

}
