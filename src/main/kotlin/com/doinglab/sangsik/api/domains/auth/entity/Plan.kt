package com.doinglab.sangsik.api.domains.auth.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "Plan")
class Plan(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val blockWhenExcess: Int,
    val blockWhenDailyExcess: Int
) {
    data class Dto(
        val id: Long,
        val blockWhenExcess: Int,
        val blockWhenDailyExcess: Int,
        var planUrls: List<PlanUrl.Dto>? = null
    )

    fun toDto() = Dto(id, blockWhenExcess, blockWhenDailyExcess)
}
