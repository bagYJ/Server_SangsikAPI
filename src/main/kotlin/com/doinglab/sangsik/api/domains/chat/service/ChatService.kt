package com.doinglab.sangsik.api.domains.chat.service

import com.doinglab.sangsik.Exception.CustomException
import com.doinglab.sangsik.api.amazon.mq.ActiveMQService
import com.doinglab.sangsik.api.amazon.mq.dto.ChatNotificationDto
import com.doinglab.sangsik.api.amazon.s3.AmazonS3Service
import com.doinglab.sangsik.api.domains.chat.dto.response.ResponseGetChatRoomIdDto
import com.doinglab.sangsik.api.domains.chat.dto.response.ResponseGetChatUnreadDto
import com.doinglab.sangsik.api.domains.chat.dto.response.ResponsePostChatRoomIdDto
import com.doinglab.sangsik.api.domains.chat.entity.ChatMessage
import com.doinglab.sangsik.api.domains.chat.repo.ChatRepo
import com.doinglab.sangsik.api.domains.dietProgram.repo.DietProgramRepo
import com.doinglab.sangsik.api.domains.staff.repo.StaffRepo
import com.doinglab.sangsik.define.CoreDefine.Companion.PAGE_LIMIT
import com.doinglab.sangsik.enums.MessageType
import com.doinglab.sangsik.enums.StatusCode
import com.doinglab.sangsik.utils.toJsonString
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
@Transactional
class ChatService(
    private val chatRepo: ChatRepo,
    private val staffRepo: StaffRepo,
    private val dietProgramRepo: DietProgramRepo,
    private val amazonS3Service: AmazonS3Service,
    private val activeMQService: ActiveMQService
) {
    @Value("\${activeMQ.queueName}")
    private lateinit var queueName: String

    fun doGetChatUnread(userId: Long): ResponseGetChatUnreadDto =
        chatRepo.unreadMessage(userId).let { chat ->
            chat.third?.let { chatReadIndex ->
                chat.second?.let { chatMessage ->
                    if (chatMessage.id > chatReadIndex.readIndex) {
                        val dietProgram = dietProgramRepo.findDietProgram(chatMessage.writerId)
                        ResponseGetChatUnreadDto(chatMessage, dietProgram?.title)
                    } else {
                        ResponseGetChatUnreadDto()
                    }
                } ?: ResponseGetChatUnreadDto()
            } ?: ResponseGetChatUnreadDto()
        }

    fun doGetChatRoomId(userId: Long, roomId: Long, lastId: Long?) =
        chatRepo.findChatRoom(roomId)?.let { chatRoom ->
            if (chatRoom.userId != userId) throw CustomException(StatusCode.NOT_FOUND_CHAT_ROOM)

            chatRepo.findChatMessage(roomId, lastId, PAGE_LIMIT)?.let { messages ->
                messages.firstOrNull()?.let { message ->
                    activeMQService.sendMessage(message.id.toString(), roomId.toInt(), "chatRead", queueName)
                }
                if (lastId == null) {
                    messages.maxByOrNull { it.id }?.let { message ->
                        chatRepo.findChatReadIndex(roomId, false).let { chatReadIndex ->
                            chatRepo.updateChatReadIndex(chatReadIndex.copy(readIndex = message.id))
                        }
                    }
                }
                ResponseGetChatRoomIdDto(messages, amazonS3Service)
            }
        } ?: throw CustomException(StatusCode.NOT_FOUND_CHAT_ROOM)

    fun doPostChatRoomId(userId: Long, roomId: Long, messageType: MessageType, message: String?, file: MultipartFile?): ResponsePostChatRoomIdDto {
        if (messageType == MessageType.TEXT && message == null) throw CustomException(StatusCode.BAD_REQUEST)
        if (messageType == MessageType.IMAGE && file == null) throw CustomException(StatusCode.BAD_REQUEST)

        return chatRepo.findChatRoom(roomId)?.let { room ->
            if (room.userId != userId) throw CustomException(StatusCode.NOT_FOUND_CHAT_ROOM)

            val filename = if (messageType == MessageType.IMAGE) {
                amazonS3Service.uploadChatImageFileAndGetName(file!!)
            } else ""

            chatRepo.saveChatMessage(ChatMessage.Dto(room.id, userId, messageType, when (messageType) {
                MessageType.TEXT -> message!!
                MessageType.IMAGE -> filename
            })).let { chat ->
                if (chat.id <= 0L) throw CustomException(StatusCode.FAIL_INSERT)
//                chatRepo.findChatReadIndex(room.id, false).let { chatReadIndex ->
//                    chatRepo.updateChatReadIndex(chatReadIndex.copy(readIndex = chat.id))
//                }

                val programHistoryId = dietProgramRepo.findDietProgramEnrolledUserByRoomId(room.id)?.id ?: 0L
                val unreadCount = chatRepo.findUnreadMessage(room.id, true)

                activeMQService.sendMessage(
                    message = ChatNotificationDto(chat, programHistoryId, unreadCount).toJsonString(),
                    channelId = room.staffId.toInt(),
                    groupId = "chat",
                    queue = queueName
                )

                ResponsePostChatRoomIdDto(chat, amazonS3Service)
            }
        } ?: throw CustomException(StatusCode.NOT_FOUND_CHAT_ROOM)
    }
}
