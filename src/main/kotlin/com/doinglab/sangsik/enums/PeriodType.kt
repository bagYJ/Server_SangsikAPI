package com.doinglab.sangsik.enums

import jakarta.persistence.AttributeConverter

enum class PeriodType(
    val value: String,
    val desc: String,
    val day: Int
) {
    DAILY("Daily", "일간", 1), WEEKLY("Weekly", "주간", 7), MONTHLY("Monthly", "월간", 4 * 7), TIMES("Times", "시간", 7);

    @jakarta.persistence.Converter
    class Converter: AttributeConverter<PeriodType, String> {
        override fun convertToDatabaseColumn(periodType: PeriodType?) = periodType?.value

        override fun convertToEntityAttribute(value: String?): PeriodType? = entries.firstOrNull { it.value == value }
    }
}
