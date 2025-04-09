package com.doinglab.sangsik.api.domains.chat.mock

import com.doinglab.sangsik.api.domains.chat.entity.ChatMessage
import com.doinglab.sangsik.api.domains.chat.entity.ChatReadIndex
import com.doinglab.sangsik.api.domains.chat.entity.ChatRoom
import com.doinglab.sangsik.api.domains.dietProgram.entity.DietProgram
import com.doinglab.sangsik.api.domains.staff.entity.PartnerCompany
import com.doinglab.sangsik.api.domains.staff.entity.Staff
import com.doinglab.sangsik.enums.*
import java.time.LocalDateTime

val chatRoom = ChatRoom.Dto(1L, "chat room test case", 1L, 1L)
//val chatMessage = ChatMessage.Dto(1L, 1L, MessageType.TEXT, "message test case")
val chatMessage = ChatMessage.Dto(2L, 1L, LocalDateTime.now(), false, 1L, MessageType.TEXT, "message test case")
val chatReadIndex = ChatReadIndex.Dto(1L, 1L, false, 1L, 1L)

val dietProgram = DietProgram.Dto(1L, 100, "normal", 1, "Sangsik", "", "", "", AppointmentType.PHONE, "", 1L, 1L, 0, LocalDateTime.now(), LocalDateTime.now(), EnrollType.ALL, 999, 10, 0, 0, "", 12, true, false, DietProgramStatus.OPERATED, "", "000000", listOf())
val staff = Staff.Dto(1L, "Sangsik", "", "", "staff", "doinglab@doinglab.com", 1, "", 1L, "", "", "", true, 1, "", true, true, "", true, FoodlensVersion.FOODLENS, Status.OPERATE)

val partnerCompany = PartnerCompany.Dto(1L, "partner company test case", "B0010")
