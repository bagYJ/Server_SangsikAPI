package com.doinglab.sangsik.enums

import com.doinglab.sangsik.interfaces.EnumConvert
import jakarta.persistence.AttributeConverter

enum class TargetAct(
    override val value: Int,
    override val desc: String
): EnumConvert {
    NONE(-1, "미설정"), WEIGHT_LOSS(10, "체중감량"), MUSCLE_WEIGHT(20, "근육중량"), WEIGHT_MAINTENANCE(30, "체중유지");

    @jakarta.persistence.Converter
    class Converter: AttributeConverter<TargetAct, Int> {
        override fun convertToDatabaseColumn(targetAct: TargetAct?) = targetAct?.value

        override fun convertToEntityAttribute(value: Int?): TargetAct? = entries.firstOrNull { it.value == value }
    }

}
