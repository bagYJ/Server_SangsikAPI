package com.doinglab.sangsik.enums

import jakarta.persistence.AttributeConverter

enum class AppointmentType(
    val value: String,
    val desc: String
) {
    TEXT("Text", "채팅 상담"), PHONE("Phone", "전화 상담"), INTERVIEW("Interview", "인터뷰");

    @jakarta.persistence.Converter
    class Converter: AttributeConverter<AppointmentType, String> {
        override fun convertToDatabaseColumn(appointmentType: AppointmentType?) = appointmentType?.value

        override fun convertToEntityAttribute(value: String?): AppointmentType? = entries.firstOrNull { it.value == value }
    }
}
