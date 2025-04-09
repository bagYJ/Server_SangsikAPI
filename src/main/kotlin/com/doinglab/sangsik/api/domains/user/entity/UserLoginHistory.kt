package com.doinglab.sangsik.api.domains.user.entity

import com.doinglab.sangsik.config.converter.NumericBooleanConverter
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity(name = "UserLoginHistory")
class UserLoginHistory(
    @Id
    val seq: String,
    val userId: String,
    val appName: String,
    val url: String,
    @Convert(converter = NumericBooleanConverter::class)
    val loginStatus: Boolean,
    val loginComment: String,
    val loginSource: String,
    val email: String,
    val socialId: String,
    val ip: String,
    val userAgent: String,
    val createdAt: LocalDateTime
) {
    data class Dto(
        val seq: String,
        val userId: String,
        val appName: String,
        val url: String,
        val loginStatus: Boolean,
        val loginComment: String,
        val loginSource: String,
        val email: String,
        val socialId: String,
        val ip: String,
        val userAgent: String,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        val createdAt: LocalDateTime
    )

    fun toDto() = Dto(seq, userId, appName, url, loginStatus, loginComment, loginSource, email, socialId, ip, userAgent, createdAt)
}
