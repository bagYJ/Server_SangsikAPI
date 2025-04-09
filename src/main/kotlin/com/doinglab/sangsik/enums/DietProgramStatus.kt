package com.doinglab.sangsik.enums

import com.doinglab.sangsik.interfaces.EnumConvert
import jakarta.persistence.AttributeConverter

enum class DietProgramStatus(
    override val value: Int,
    override val desc: String
): EnumConvert {
    NOTOPERATED(0, "미운영"), OPERATED(1, "운영"), FIFO(2, "선착순 마감");

    @jakarta.persistence.Converter
    class Converter: AttributeConverter<DietProgramStatus, Int> {
        override fun convertToDatabaseColumn(dietProgramStatus: DietProgramStatus?) = dietProgramStatus?.value

        override fun convertToEntityAttribute(value: Int?): DietProgramStatus? = entries.firstOrNull { it.value == value }
    }
}
