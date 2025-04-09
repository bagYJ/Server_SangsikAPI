package com.doinglab.sangsik.api.domains.chat.dto.response

import com.doinglab.sangsik.api.amazon.s3.AmazonS3Service
import com.doinglab.sangsik.api.domains.chat.dto.response.ResponseGetChatRoomIdDto.GetChatRoomIdBody
import com.doinglab.sangsik.api.domains.chat.entity.ChatMessage
import com.doinglab.sangsik.api.domains.common.dto.BaseDto
import com.doinglab.sangsik.enums.MessageType

data class ResponsePostChatRoomIdDto(
    override val body: GetChatRoomIdBody
): BaseDto() {
    constructor(message: ChatMessage.Dto, amazonS3Service: AmazonS3Service) : this(GetChatRoomIdBody(
        id = message.id,
        writerId = message.writerId,
        isStaff = message.isStaff,
        messageType = message.messageType,
        message = when(message.messageType) {
            MessageType.TEXT -> message.message
            MessageType.IMAGE -> amazonS3Service.getChatImageFileURL(message.message, true)
        },
        date = message.date
    )
    )
}
