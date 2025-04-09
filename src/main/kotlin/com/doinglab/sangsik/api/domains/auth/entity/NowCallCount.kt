package com.doinglab.sangsik.api.domains.auth.entity

import com.doinglab.sangsik.config.converter.NumericBooleanConverter
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "NowCallCount")
class NowCallCount(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "company_id")
    val companyId: Long,
    val uri: String,
    val lastRefreshDate: LocalDateTime,
    @Convert(converter = NumericBooleanConverter::class)
    val notifyFlag: Boolean = false,

) {
    var callCount: Int = 0
    var dailyCallCount: Int = 0

    data class Dto(
        val id: Long,
        val companyId: Long,
        val uri: String,
        val callCount: Int,
        val dailyCallCount: Int,
        val lastRefreshDate: LocalDateTime,
        val notifyFlag: Boolean
    )

    fun toDto() = Dto(id, companyId, uri, callCount, dailyCallCount, lastRefreshDate, notifyFlag)
}
