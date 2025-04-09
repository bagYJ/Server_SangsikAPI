package com.doinglab.sangsik.enums

import com.doinglab.sangsik.interfaces.EnumConvert
import jakarta.persistence.AttributeConverter

enum class Status(
    override val value: Int,
    override val desc: String
): EnumConvert {
    READY(0, "준비중"), OPERATE(0, "운영"), OVERBOOKED(1, "예약초과"), DEADLINE(2, "예약마감");

    @jakarta.persistence.Converter
    class Converter: AttributeConverter<Status, Int> {
        override fun convertToDatabaseColumn(status: Status?) = status?.value

        override fun convertToEntityAttribute(value: Int?): Status? = entries.firstOrNull { it.value == value }
    }
}
