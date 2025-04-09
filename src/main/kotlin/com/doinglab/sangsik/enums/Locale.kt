package com.doinglab.sangsik.enums

import com.fasterxml.jackson.annotation.JsonValue

enum class Locale(
    @get:JsonValue val value: String
) {
    KO("ko"), EN("en"), JA("ja"), ES("es"), DE("de");

    companion object {
        val announceLocaleList: List<String> = listOf(KO.value, EN.value)

        fun of(value: String?): Locale =
            when {
                value == "kr" -> KO
                entries.firstOrNull { it.value == value } in entries -> entries.first { it.value == value }
                else -> EN
            }

        fun getValue(value: String?, locales: List<String>): String =
            locales.firstOrNull { it == value } ?: EN.value
    }
}
