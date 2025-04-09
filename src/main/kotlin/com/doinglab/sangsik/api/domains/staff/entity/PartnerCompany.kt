package com.doinglab.sangsik.api.domains.staff.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "PartnerCompany")
class PartnerCompany(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String,
    val code: String
) {
    data class Dto(
        val id: Long,
        val name: String,
        val code: String
    )

    fun toDto() = Dto(id, name, code)
}
