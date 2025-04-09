package com.doinglab.sangsik.api.domains.announcement.entity

import com.doinglab.sangsik.config.converter.NumericBooleanConverter
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "Announcement")
class Announcement(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val appName: String,
    val title: String,
    val lang: String,
    val url: String,
    @Convert(converter = NumericBooleanConverter::class)
    val isPinned: Boolean,
    @Convert(converter = NumericBooleanConverter::class)
    val checkRead: Boolean,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val createdAt: LocalDateTime,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val updatedAt: LocalDateTime
) {
    data class Dto(
        val id: Long,
        val appName: String,
        val title: String,
        val lang: String,
        val url: String,
        val isPinned: Boolean,
        val checkRead: Boolean,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime
    )

    fun toDto() = Dto(id, appName, title, lang, url, isPinned, checkRead, createdAt, updatedAt)
}
