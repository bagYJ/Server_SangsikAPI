package com.doinglab.sangsik.enums

import jakarta.persistence.AttributeConverter

enum class BloodSugarStatus(val value: String) {
    REGIST("등록"), DELETE("삭제");

    @jakarta.persistence.Converter
    class Converter: AttributeConverter<BloodSugarStatus, String> {
        override fun convertToDatabaseColumn(bloodSugarStatus: BloodSugarStatus?) = bloodSugarStatus?.value

        override fun convertToEntityAttribute(value: String?): BloodSugarStatus? = entries.firstOrNull { it.value == value }
    }
}
