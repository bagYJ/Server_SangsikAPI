package com.doinglab.sangsik.enums

import jakarta.persistence.AttributeConverter

enum class InquiryType(
    val value: Int,
    val desc: String,
) {
    APP(0, "앱 개선"), FOOD(1, "음식 데이터 추가 요청"), SERVICE(2, "서비스 제안"), BUSINESS(3, "사업 제안"), ETC(4, "기타");

    @jakarta.persistence.Converter
    class Converter: AttributeConverter<InquiryType, Int> {
        override fun convertToDatabaseColumn(inquiryType: InquiryType?) = inquiryType?.value

        override fun convertToEntityAttribute(value: Int?): InquiryType? = entries.firstOrNull { it.value == value }
    }
}
