package com.doinglab.sangsik.enums

import com.doinglab.sangsik.api.domains.dietProgram.entity.DietProgram
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.persistence.AttributeConverter

enum class DietProgramOption(@get:JsonValue val code: String) {
    BATCH_MESSAGE("batchMessage"), CHATTING("chatting"), ANALYSIS("analysis");

    @jakarta.persistence.Converter
    class Converter: AttributeConverter<List<DietProgram.Option>, String> {
        override fun convertToDatabaseColumn(option: List<DietProgram.Option>?): String? =
            option?.let { jacksonObjectMapper().registerModules(JavaTimeModule()).writeValueAsString(it) }

        override fun convertToEntityAttribute(dbData: String?): List<DietProgram.Option> =
            dbData?.let { jacksonObjectMapper().registerModules(JavaTimeModule()).readValue(it, object: TypeReference<MutableList<DietProgram.Option>>() {}) }?.let { options ->
                entries.subtract(options.map { option -> option.name }.toSet()).map { optionName ->
                    options.add(DietProgram.Option(
                        name = optionName,
                        isUse = false
                    ))
                }

                options
            } ?: entries.map { optionName ->
                DietProgram.Option(
                    name = optionName,
                    isUse = false
                )
            }
    }
}
