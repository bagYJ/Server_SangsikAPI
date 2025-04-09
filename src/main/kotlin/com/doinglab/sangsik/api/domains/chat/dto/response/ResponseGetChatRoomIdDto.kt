package com.doinglab.sangsik.api.domains.chat.dto.response

import com.doinglab.sangsik.api.amazon.s3.AmazonS3Service
import com.doinglab.sangsik.api.domains.chat.entity.ChatMessage
import com.doinglab.sangsik.api.domains.common.dto.BaseDto
import com.doinglab.sangsik.enums.MessageType
import com.doinglab.sangsik.utils.toUtcTime
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

data class ResponseGetChatRoomIdDto(
    override val body: List<GetChatRoomIdBody>
): BaseDto() {
    data class GetChatRoomIdBody(
        @Schema(description = "채팅 ID")
        val id: Long,
        @Schema(description = "작성자 ID")
        val writerId: Long,
        @Schema(description = "스태프 여부")
        val isStaff: Boolean,
        @Schema(description = "메시지 타입 (TEXT: 텍스트, IMAGE: 이미지)")
        val messageType: MessageType,
        @Schema(description = "메시지")
        val message: String,
//        @Schema(description = "메시지 확인 여부")
//        val isRead: Boolean,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        @Schema(description = "등록일")
        val date: LocalDateTime
    )

    constructor(messages: List<ChatMessage.Dto>, amazonS3Service: AmazonS3Service): this(messages.map { message ->
        GetChatRoomIdBody(
            id = message.id,
            writerId = message.writerId,
            isStaff = message.isStaff,
            messageType = message.messageType,
            message = when(message.messageType) {
                MessageType.TEXT -> message.message
                MessageType.IMAGE -> amazonS3Service.getChatImageFileURL(message.message, true)
            },
            date = message.date.toUtcTime()
        )
    })
}
