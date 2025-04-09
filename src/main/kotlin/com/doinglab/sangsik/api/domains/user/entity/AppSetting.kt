package com.doinglab.sangsik.api.domains.user.entity

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name = "AppSetting")
class AppSetting(
    @Id
    val appName: String,
    @Convert(converter = Converter::class)
    val urlTermOfService: Term,
    @Convert(converter = Converter::class)
    val urlPrivacyPolicy: Term,
    @Convert(converter = Converter::class)
    val urlCollectionPersonalInformation: Term,
    @Convert(converter = Converter::class)
    val urlProgramConnect: Term
) {
    data class Dto(
        val appName: String,
        val urlTermOfService: Term,
        val urlPrivacyPolicy: Term,
        val urlCollectionPersonalInformation: Term,
        val urlProgramConnect: Term
    )

    data class Term(
        val ko: String,
        val en: String
    )

    fun toDto() = Dto(appName, urlTermOfService, urlPrivacyPolicy, urlCollectionPersonalInformation, urlProgramConnect)

    @jakarta.persistence.Converter
    class Converter: AttributeConverter<Term, String> {
        private val objectMapper: ObjectMapper = jacksonObjectMapper()

        override fun convertToDatabaseColumn(attribute: Term?): String {
            return attribute?.let { objectMapper.writeValueAsString(it) } ?: ""
        }

        override fun convertToEntityAttribute(dbData: String?): Term? {
            return dbData?.let { objectMapper.readValue(it, object: TypeReference<Term>() {}) }
        }
    }
}
