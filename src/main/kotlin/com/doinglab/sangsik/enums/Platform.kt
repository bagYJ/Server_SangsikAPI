package com.doinglab.sangsik.enums

import com.doinglab.sangsik.interfaces.EnumConvert
import jakarta.persistence.AttributeConverter

enum class Platform(
    override val value: Int,
    override val desc: String
): EnumConvert {
    IOS(0, "iOS"), ANDROID(1, "android");

    @jakarta.persistence.Converter
    class Converter: AttributeConverter<Platform, String> {
        override fun convertToDatabaseColumn(platform: Platform?) = platform?.desc

        override fun convertToEntityAttribute(desc: String?): Platform? = entries.firstOrNull { it.desc == desc }
    }
}
