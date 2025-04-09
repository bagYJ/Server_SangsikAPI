package com.doinglab.sangsik.api.domains.chat.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "ChatRoom")
class ChatRoom(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val title: String,
    val userId: Long,
    val staffId: Long
) {
    data class Dto(
        val id: Long,
        val title: String = "",
        val userId: Long,
        val staffId: Long
    ) {
        fun toEntity() = ChatRoom(id, title, userId, staffId)
    }

    fun toDto() = Dto(id, title, userId, staffId)
}
