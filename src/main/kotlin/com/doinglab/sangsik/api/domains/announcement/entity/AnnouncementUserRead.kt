package com.doinglab.sangsik.api.domains.announcement.entity

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity(name = "AnnouncementUserRead")
class AnnouncementUserRead(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val userId: Long,
    val announcementId: Long,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val createdAt: LocalDateTime
) {
    data class Dto(
        val id: Long,
        val userId: Long,
        val announcementId: Long,
        val createdAt: LocalDateTime
    ) {
        fun toEntity() = AnnouncementUserRead(id, userId, announcementId, createdAt)

        constructor(userId: Long, announcementId: Long) : this(
            id = 0L,
            userId = userId,
            announcementId = announcementId,
            createdAt = LocalDateTime.now()
        )
    }

    fun toDto(): Dto = Dto(id, userId, announcementId, createdAt)
}
