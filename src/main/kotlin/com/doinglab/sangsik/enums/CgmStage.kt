package com.doinglab.sangsik.enums

import jakarta.persistence.AttributeConverter

enum class CgmStage(val value: Int, val desc: String) {
    BEFORE(0, "보정 전"), DURING(1, "보정중"), COMPLETE(2, "보정완료");

    @jakarta.persistence.Converter
    class Converter: AttributeConverter<CgmStage, Int> {
        override fun convertToDatabaseColumn(stage: CgmStage?) = stage?.value

        override fun convertToEntityAttribute(value: Int?): CgmStage? = CgmStage.entries.firstOrNull { it.value == value }
    }

    companion object {
        val notComplete = listOf(BEFORE, DURING)

        fun of(value: Int): CgmStage = entries.first { it.value == value }
    }
}
