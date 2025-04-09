package com.doinglab.sangsik.api.domains.chat.service

import com.doinglab.sangsik.api.amazon.mq.ActiveMQService
import com.doinglab.sangsik.api.amazon.s3.AmazonS3Service
import com.doinglab.sangsik.api.domains.chat.mock.chatMessage
import com.doinglab.sangsik.api.domains.chat.mock.chatReadIndex
import com.doinglab.sangsik.api.domains.chat.mock.chatRoom
import com.doinglab.sangsik.api.domains.chat.mock.dietProgram
import com.doinglab.sangsik.api.domains.chat.repo.ChatRepo
import com.doinglab.sangsik.api.domains.dietProgram.repo.DietProgramRepo
import com.doinglab.sangsik.api.domains.staff.repo.StaffRepo
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ChatServiceTest {
    private val chatRepo: ChatRepo = mockk()
    private val staffRepo: StaffRepo = mockk()
    private val dietProgramRepo: DietProgramRepo = mockk()
    private val amazonS3Service: AmazonS3Service = mockk()
    private val activeMQService: ActiveMQService = mockk()
    private val chatService = ChatService(chatRepo, staffRepo, dietProgramRepo, amazonS3Service, activeMQService)

    @Test
    internal fun last_unread_message() {
        every { chatRepo.unreadMessage(1L) } returns Triple(chatRoom, chatMessage, chatReadIndex)
        every { dietProgramRepo.findDietProgram(1L) } returns dietProgram

        chatService.doGetChatUnread(1L).run {
            assertEquals(body?.roomId, chatRoom.id)
        }
    }
}
