package com.doinglab.sangsik.api.domains.user.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "UserContactPoint")
class UserContactPoint(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String,
    val userId: Long,
    val contactPoint: String,
    val type: String,
    val contactType: String
) {
    data class Dto(
        val id: Long,
        val name: String,
        val userId: Long,
        val contactPoint: String,
        val type: String,
        val contactType: String
    ) {
        fun toEntity() = UserContactPoint(id, name, userId, contactPoint, type, contactType)
    }

    fun toDto() = Dto(id, name, userId, contactPoint, type, contactType)
}
