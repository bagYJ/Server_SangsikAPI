package com.doinglab.sangsik.enums

import jakarta.persistence.AttributeConverter

enum class EnrollType(
    val value: Int,
    val desc: String
) {
    FIRST(0, "선착순"), SELECT(1, "선택"), ALL(2, "모두"), CODE(3, "Code 로 신청"), PHONE(4, "신청 시 전화번호 입력");

    companion object {
        val applyCode = listOf(ALL, CODE)
    }

    @jakarta.persistence.Converter
    class Converter: AttributeConverter<EnrollType, Int> {
        override fun convertToDatabaseColumn(enrollType: EnrollType?) = enrollType?.value

        override fun convertToEntityAttribute(value: Int?): EnrollType? = entries.firstOrNull { it.value == value }
    }
}
