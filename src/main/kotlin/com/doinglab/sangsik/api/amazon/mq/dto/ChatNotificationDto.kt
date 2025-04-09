package com.doinglab.sangsik.api.amazon.mq.dto

import com.doinglab.sangsik.api.domains.chat.entity.ChatMessage
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class ChatNotificationDto(
    val id: Long,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    val date: LocalDateTime,
    val writerId: Long,
    val message: String,
    val isStaff: Boolean,
    val messageType: Int,
    val roomId: Long,
    val programHistoryId: Long,
    val unreadCount: Long
) {
    constructor(chatMessage: ChatMessage.Dto, programHistoryId: Long, unreadCount: Long) : this(chatMessage.id, chatMessage.date, chatMessage.writerId, chatMessage.message, chatMessage.isStaff, chatMessage.messageType.value, chatMessage.roomId, programHistoryId, unreadCount)
}
