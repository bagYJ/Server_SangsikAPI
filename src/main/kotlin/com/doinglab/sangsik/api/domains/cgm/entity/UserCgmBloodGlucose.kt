package com.doinglab.sangsik.api.domains.cgm.entity

import com.doinglab.sangsik.api.cgm.dto.response.ResponseDoGetCgmsDto
import com.doinglab.sangsik.enums.CgmStage
import com.doinglab.sangsik.enums.CgmTrend
import com.doinglab.sangsik.utils.encrypt
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "UserCgmBloodGlucose")
class UserCgmBloodGlucose(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val userId: Long,
    val serialNumber: String,
    val seqNumber: Long,
    val eventAt: LocalDateTime,
    @Convert(converter = CgmStage.Converter::class)
    val stage: CgmStage,
    val initialValue: String,
    val value: String,
    @Convert(converter = CgmTrend.Converter::class)
    val trend: CgmTrend,
    val trendRate: Float
) {
    data class Dto(
        val id: Long,
        val userId: Long,
        val serialNumber: String,
        val seqNumber: Long,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        val eventAt: LocalDateTime,
        val stage: CgmStage,
        val initialValue: String,
        val value: String,
        val trend: CgmTrend,
        val trendRate: Float
    ) {
        fun toEntity() = UserCgmBloodGlucose(id, userId, serialNumber, seqNumber, eventAt, stage, initialValue, value, trend, trendRate)

        constructor(userId: Long, cgm: ResponseDoGetCgmsDto, aesKey: String): this(
            id = 0L,
            userId = userId,
            serialNumber = cgm.serialNumber,
            seqNumber = cgm.seqNumber,
            eventAt = cgm.eventAt,
            stage = CgmStage.of(cgm.stage),
            initialValue = cgm.initialValue.encrypt(aesKey),
            value = cgm.value.encrypt(aesKey),
            trend = CgmTrend.of(cgm.trend),
            trendRate = cgm.trendRate
        )
    }

    fun toDto() = Dto(id, userId, serialNumber, seqNumber, eventAt, stage, initialValue, value, trend, trendRate)
}
