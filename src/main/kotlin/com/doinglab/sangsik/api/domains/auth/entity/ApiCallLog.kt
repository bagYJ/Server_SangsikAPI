package com.doinglab.sangsik.api.domains.auth.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "APICallLog")
class ApiCallLog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "company_id")
    val companyId: Int,
    val ip: String,
    val date: LocalDateTime = LocalDateTime.now(),
    val uri: String
) {
    data class Dto(
        val id: Long,
        val companyId: Int,
        val ip: String,
        val date: LocalDateTime,
        val uri: String
    )

    fun toDto() = Dto(id, companyId, ip, date, uri)
    fun toEntity(dto: Dto) = dto.let {
        ApiCallLog(id = it.id, companyId = it.companyId, ip = it.ip, uri = it.uri)
    }
}
