package com.doinglab.sangsik.enums

import com.doinglab.sangsik.interfaces.EnumConvert
import jakarta.persistence.AttributeConverter

enum class PaymentType(
    override val value: Int,
    override val desc: String
): EnumConvert {
    CP(0, "회사별"), AP(1, "앱별");

    @jakarta.persistence.Converter
    class Converter: AttributeConverter<PaymentType, Int> {
        override fun convertToDatabaseColumn(paymentType: PaymentType?) = paymentType?.value

        override fun convertToEntityAttribute(value: Int?): PaymentType? = entries.firstOrNull { it.value == value }
    }
}
