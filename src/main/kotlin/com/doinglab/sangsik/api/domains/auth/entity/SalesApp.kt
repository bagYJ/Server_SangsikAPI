package com.doinglab.sangsik.api.domains.auth.entity

import com.doinglab.sangsik.config.converter.NumericBooleanConverter
import com.doinglab.sangsik.enums.Role
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(name = "Sales_App")
class SalesApp(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val companyId: Int,
    val name: String,
    val packageIos: String,
    val packageAndroid: String,
    @Convert(converter = Role.Converter::class)
    val roleFoodLens: Role,
    @Convert(converter = Role.Converter::class)
    val roleCaloAI: Role,
    val appToken: String,
    val tokenCheck: Int,
    val authId: String,
    val authPassword: String,
    val nextPaymentAt: LocalDate,
    @Convert(converter = NumericBooleanConverter::class)
    val isLogging: Boolean,
    @Convert(converter = NumericBooleanConverter::class)
    val isUse: Boolean,
    @Convert(converter = NumericBooleanConverter::class)
    val isDel: Boolean,
    val updatedAt: LocalDateTime,
    val createdAt: LocalDateTime
)
