package com.doinglab.sangsik.api.domains.chat.dto.response

import com.doinglab.sangsik.api.domains.chat.entity.ChatMessage
import com.doinglab.sangsik.api.domains.common.dto.BaseDto
import com.doinglab.sangsik.enums.MessageType
import com.doinglab.sangsik.utils.toUtcTime
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "읽지 않은 채팅 메시지")
data class ResponseGetChatUnreadDto(
    override val body: GetChatUnreadBody?
): BaseDto() {
    data class GetChatUnreadBody(
        @Schema(description = "채팅룸 아이디")
        val roomId: Long,
        @Schema(description = "프로그램명")
        val programTitle: String?,
        @Schema(description = "담당자 이름")
        val staffName: String? = null,
        @Schema(description = "기관 이름")
        val partnerCompanyName: String? = null,
        @Schema(description = "수신일자")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        val date: LocalDateTime,
        @Schema(description = "메시지 타입 (TEXT: 텍스트, IMAGE: 이미지)")
        val messageType: MessageType,
        @Schema(description = "메시지")
        val message: String
    )

    constructor(): this(null)
    constructor(chatMessage: ChatMessage.Dto, programTitle: String?): this(GetChatUnreadBody(
        roomId = chatMessage.roomId,
        programTitle = programTitle,
        date = chatMessage.date.toUtcTime(),
        messageType = chatMessage.messageType,
        message = chatMessage.message
    ))
}
