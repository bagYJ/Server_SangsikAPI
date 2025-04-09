package com.doinglab.sangsik.enums

import com.doinglab.sangsik.interfaces.EnumConvert
import jakarta.persistence.AttributeConverter

enum class LoginSource(
    override val value: Int,
    override val desc: String
): EnumConvert {
    LOCAL(0, "local"), NAVER(1, "naver"), KAKAO(2, "kakao"), FACEBOOK(3, "facebook"), APPLE(4, "apple"), GOOGLE(5, "google");

    @jakarta.persistence.Converter
    class Converter: AttributeConverter<LoginSource, String> {
        override fun convertToDatabaseColumn(loginSource: LoginSource?) = loginSource?.desc

        override fun convertToEntityAttribute(desc: String?): LoginSource? = entries.firstOrNull { it.desc == desc }
    }
}

