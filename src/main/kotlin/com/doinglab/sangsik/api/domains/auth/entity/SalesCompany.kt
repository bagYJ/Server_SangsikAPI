package com.doinglab.sangsik.api.domains.auth.entity

import com.doinglab.sangsik.config.converter.NumericBooleanConverter
import com.doinglab.sangsik.enums.PaymentType
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(name = "Sales_Company")
class SalesCompany(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val userId: Int,
    val name: String,
    val email: String,
    val phone: String,
    val companyToken: String,
    @Convert(converter = NumericBooleanConverter::class)
    val tokenCheck: Boolean,
    @Convert(converter = PaymentType.Converter::class)
    val paymentType: PaymentType,
    val nextPaymentAt: LocalDate,
    @Convert(converter = NumericBooleanConverter::class)
    val isUse: Boolean,
    @Convert(converter = NumericBooleanConverter::class)
    val isDel: Boolean,
    val updatedAt: LocalDateTime,
    val createdAt: LocalDateTime
) {
    data class Dto(
        val id: Long,
        val userId: Int,
        val name: String,
        val email: String,
        val phone: String,
        val companyToken: String,
        val tokenCheck: Boolean,
        val paymentType: PaymentType,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        val nextPaymentAt: LocalDate,
        val isUse: Boolean,
        val isDel: Boolean,
        val updatedAt: LocalDateTime,
        val createdAt: LocalDateTime
    )

    fun toDto() = Dto(id, userId, name, email, phone, companyToken, tokenCheck, paymentType, nextPaymentAt, isUse, isDel, updatedAt, createdAt)
}
