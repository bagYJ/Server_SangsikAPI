package com.doinglab.sangsik.api.domains.cgm.entity

import com.doinglab.sangsik.api.cgm.dto.response.ResponseCgmAccountPostTokenDto
import com.doinglab.sangsik.api.domains.user.entity.User
import com.doinglab.sangsik.utils.encrypt
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(name = "UserCgmToken")
class UserCgmToken(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val userId: Long,
    val cgmId: String,
    val accessToken: String,
    val refreshToken: String,
    val expiresIn: Long,
    val disconnectedAt: LocalDateTime? = null,
    val cgmCalledAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime,
    val createdAt: LocalDateTime,
) {
    data class Dto(
        val id: Long = 0L,
        val userId: Long,
        val cgmId: String,
        val accessToken: String,
        val refreshToken: String,
        val expiresIn: Long,
        val disconnectedAt: LocalDateTime? = null,
        val cgmCalledAt: LocalDateTime? = null,
        val updatedAt: LocalDateTime = LocalDateTime.now(),
        val createdAt: LocalDateTime = LocalDateTime.now(),
    ) {
        fun toEntity() = UserCgmToken(id, userId, cgmId, accessToken, refreshToken, expiresIn, disconnectedAt, cgmCalledAt, updatedAt, createdAt)

        constructor(user: User.Dto, res: ResponseCgmAccountPostTokenDto, aesKey: String): this(
            id = 0L,
            userId = user.id,
            cgmId = res.userId,
            accessToken = res.accessToken.encrypt(aesKey),
            refreshToken = res.refreshToken.encrypt(aesKey),
            expiresIn = res.expiresIn
        )
    }

    data class ConnectDate(
        val sdt: LocalDate,
        val edt: LocalDate
    )

    fun toDto() = Dto(id, userId, cgmId, accessToken, refreshToken, expiresIn, disconnectedAt, cgmCalledAt, updatedAt, createdAt)
}
