package com.doinglab.sangsik.api.domains.meal.entity

import com.doinglab.sangsik.config.converter.NumericBooleanConverter
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "EatHistoryV2")
class EatHistoryV2(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val userId: Long,
    val eatTime: LocalDateTime,
    val eatAmount: Int,
    val status: String,
    val clientId: String,
    val imgPath: String,
    val imgThumbPath: String,
    @Convert(converter = NumericBooleanConverter::class)
    val isDeleted: Boolean,
    @Convert(converter = NumericBooleanConverter::class)
    val isNew: Boolean,
    val updatedAt: LocalDateTime,
    val createdAt: LocalDateTime
)
