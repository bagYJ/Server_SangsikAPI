package com.doinglab.sangsik.api.domains.auth.entity

import jakarta.persistence.*

@Entity(name = "PlanUrl")
class PlanUrl(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "plan_id")
    val planId: Long,
    val uri: String,
    val limitCount: Int,
    val dailyLimitCount: Int,
    val costPerCount: Int
) {
    data class Dto(
        val id: Long,
        val planId: Long,
        val uri: String,
        val limitCount: Int,
        val dailyLimitCount: Int,
        val costPerCount: Int
    )

    fun toDto() = Dto(id, planId, uri, limitCount, dailyLimitCount, costPerCount)
}
