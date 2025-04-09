package com.doinglab.sangsik.enums

import com.doinglab.sangsik.interfaces.EnumConvert
import jakarta.persistence.AttributeConverter

enum class EatType(
    override val value: Int,
    override val desc: String,
    val mealType: MealType
): EnumConvert {
    BREAKFAST(0, "아침", MealType.MORNING), LUNCH(1, "점심", MealType.AFTERNOON), DINNER(2, "저녁", MealType.DINNER), MORNINGSNACK(4, "간식(오전)", MealType.MORNINGSNACK), AFTERNOONSNACK(5, "간식(오후)", MealType.AFTERNOONSNACK), NIGHTSNACK(6, "간식(야식)", MealType.NIGHTSNACK);

    @jakarta.persistence.Converter
    class Converter: AttributeConverter<EatType, Int> {
        override fun convertToDatabaseColumn(eatType: EatType?) = eatType?.value

        override fun convertToEntityAttribute(value: Int?): EatType? = entries.firstOrNull { it.value == value }
    }
}
