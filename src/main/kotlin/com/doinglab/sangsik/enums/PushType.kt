package com.doinglab.sangsik.enums

import com.doinglab.sangsik.interfaces.EnumConvert
import jakarta.persistence.AttributeConverter

enum class PushType(
    override val value: Int,
    override val desc: String
): EnumConvert {
    MESSAGE(0, "message");

    @jakarta.persistence.Converter
    class Converter: AttributeConverter<PushType, String> {
        override fun convertToDatabaseColumn(pushType: PushType?) = pushType?.desc

        override fun convertToEntityAttribute(desc: String?): PushType? = entries.firstOrNull { it.desc == desc }
    }
}

