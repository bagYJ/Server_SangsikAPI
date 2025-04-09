package com.doinglab.sangsik.enums

import com.doinglab.sangsik.interfaces.EnumConvert
import jakarta.persistence.AttributeConverter

enum class ActivityLevel(
    override val value: Int,
    override val desc: String,
    val calc: Double
): EnumConvert {
    NONE(-1, "미설정", 1.375), INACTIVE(0, "비활동적", 1.2), LOWACTIVE(1, "저활동적", 1.375), ACTIVE(2, "활동적", 1.55), VERYACTIVE(3, "매우활동적", 1.725);

    @jakarta.persistence.Converter
    class Converter: AttributeConverter<ActivityLevel, Int> {
        override fun convertToDatabaseColumn(activityLevel: ActivityLevel?) = activityLevel?.value

        override fun convertToEntityAttribute(value: Int?): ActivityLevel? = entries.firstOrNull { it.value == value }
    }
}
