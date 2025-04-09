package com.doinglab.sangsik.enums.converter

import com.doinglab.sangsik.interfaces.EnumConvert
import java.util.*

class EnumValueConverter {
    fun <T> ofValue(enumClass: Class<T>?, value: Int): T? where T : Enum<T>?, T : EnumConvert? =
        EnumSet.allOf(enumClass).firstOrNull { it?.value == value }
}
