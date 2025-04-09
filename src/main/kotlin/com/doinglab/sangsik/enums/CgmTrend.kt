package com.doinglab.sangsik.enums

import jakarta.persistence.AttributeConverter

enum class CgmTrend(val value: Int, val desc: String) {
    UNKNOWN(0, "Unknown"), DECREASE_QUICKLY(1, "빠르게 감소"), DECREASE(2, "감소"), DECREASE_SLOWLY(3, "서서히 감소"), STABLE(4, "안정적"), INCREASE_SLOWLY(5, "서서히 증가"), INCREASE(6, "증가"), INCREASE_QUICKLY(7, "빠르게 증가");

    @jakarta.persistence.Converter
    class Converter: AttributeConverter<CgmTrend, Int> {
        override fun convertToDatabaseColumn(trend: CgmTrend?) = trend?.value

        override fun convertToEntityAttribute(value: Int?): CgmTrend? = CgmTrend.entries.firstOrNull { it.value == value }
    }

    companion object {
        fun of(value: Int): CgmTrend = entries.first { it.value == value }
    }
}
