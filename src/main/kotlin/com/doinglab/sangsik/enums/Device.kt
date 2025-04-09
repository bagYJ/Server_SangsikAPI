package com.doinglab.sangsik.enums

import jakarta.persistence.AttributeConverter

enum class Device(
    val value: String
) {
    ANDROID("android"), IOS("iOS"), AOS("aOS"), WEB("web");

    @jakarta.persistence.Converter
    class Converter: AttributeConverter<Device, String> {
        override fun convertToDatabaseColumn(device: Device?) = device?.value

        override fun convertToEntityAttribute(value: String?): Device? = entries.firstOrNull { it.value == value }
    }
}
