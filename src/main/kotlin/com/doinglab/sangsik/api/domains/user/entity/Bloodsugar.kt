package com.doinglab.sangsik.api.domains.user.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity(name = "Bloodsugar")
class Bloodsugar(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val index: Long,
    @Column(name = "User_id")
    val userId: Long,
    val date: LocalDate,
    @Column(name = "EatHistory_id")
    val eatHistoryId: Int,
    @Column(name = "eat_timing_type")
    val eatTimingType: Int,
    val bloodsugar: Float,
    val forDate: LocalDate,
    val position: Int
) {
    data class Dto(
        val index: Long,
        @Column(name = "User_id")
        val userId: Long,
        val date: LocalDate,
        @Column(name = "EatHistory_id")
        val eatHistoryId: Int,
        @Column(name = "eat_timing_type")
        val eatTimingType: Int,
        val bloodsugar: Float,
        val forDate: LocalDate,
        val position: Int
    ) {
        fun toEntity() = Bloodsugar(index, userId, date, eatHistoryId, eatTimingType, bloodsugar, forDate, position)
    }

    fun toDto() = Dto(index, userId, date, eatHistoryId, eatTimingType, bloodsugar, forDate, position)
}
