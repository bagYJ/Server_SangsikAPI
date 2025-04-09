package com.doinglab.sangsik.api.domains.chat.entity

import com.doinglab.sangsik.config.converter.NumericBooleanConverter
import com.doinglab.sangsik.enums.MessageType
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "ChatMessage")
class ChatMessage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val roomId: Long,
    val date: LocalDateTime,
    @Convert(converter = NumericBooleanConverter::class)
    val isStaff: Boolean,
    val writerId: Long,
    @Convert(converter = MessageType.Converter::class)
    val messageType: MessageType,
    val message: String,
    @Convert(converter = NumericBooleanConverter::class)
    val isAuto: Boolean,
    val originalId: Int
) {
    data class Dto(
        val id: Long,
        val roomId: Long,
        val date: LocalDateTime = LocalDateTime.now(),
        val isStaff: Boolean = false,
        val writerId: Long,
        val messageType: MessageType,
        val message: String,
        val isAuto: Boolean = false,
        val originalId: Int = 0
    ) {
        fun toEntity() = ChatMessage(id, roomId, date, isStaff, writerId, messageType, message, isAuto, originalId)

        constructor(roomId: Long, userId: Long, messageType: MessageType, message: String): this(
            id = 0L,
            roomId = roomId,
            writerId = userId,
            messageType = messageType,
            message = message
        )
    }

    fun toDto() = Dto(id, roomId, date, isStaff, writerId, messageType, message, isAuto, originalId)
}
