package com.doinglab.sangsik.enums

import com.doinglab.sangsik.interfaces.EnumConvert
import jakarta.persistence.AttributeConverter

enum class MessageType(
    override val value: Int,
    override val desc: String
): EnumConvert {
    TEXT(0, "텍스트"), IMAGE(1, "이미지");

    @jakarta.persistence.Converter
    class Converter: AttributeConverter<MessageType, Int> {
        override fun convertToDatabaseColumn(messageType: MessageType?) = messageType?.value

        override fun convertToEntityAttribute(value: Int?): MessageType? = entries.firstOrNull { it.value == value }
    }
}
