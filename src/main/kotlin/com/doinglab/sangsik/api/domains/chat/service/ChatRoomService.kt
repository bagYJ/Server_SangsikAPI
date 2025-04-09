package com.doinglab.sangsik.api.domains.chat.service

import com.doinglab.sangsik.api.domains.chat.repo.ChatRepo
import org.springframework.stereotype.Service

@Service
class ChatRoomService(
    private val chatRepo: ChatRepo
) {
    fun createChatRoom(userId: Long, staffId: Long): Long? =
        chatRepo.createChatRoom(userId, staffId)

    fun createChatReadIndex(writerId: Long, isStaff: Boolean, roomId: Long): Long? =
        chatRepo.createChatReadIndex(writerId, isStaff, roomId)
}
