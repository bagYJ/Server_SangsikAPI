package com.doinglab.sangsik.api.domains.user.entity

import com.doinglab.sangsik.api.domains.user.dto.request.RequestDeleteUserDto
import com.doinglab.sangsik.enums.Device
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "UserWithdrawalAnswer")
class UserWithdrawalAnswer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Convert(converter = Device.Converter::class)
    val device: Device,
    val questionId: Int,
    val answerId: Int,
    val answerContents: String?,
    val updatedAt: LocalDateTime,
    val createdAt: LocalDateTime
) {
    data class Dto(
        val id: Long,
        val device: Device = Device.IOS,
        val questionId: Int,
        val answerId: Int,
        val answerContents: String?,
        val updatedAt: LocalDateTime = LocalDateTime.now(),
        val createdAt: LocalDateTime = LocalDateTime.now()
    ) {
        fun toEntity() = UserWithdrawalAnswer(id, device, questionId, answerId, answerContents, updatedAt, createdAt)

        constructor(answerId: Int, request: RequestDeleteUserDto): this(
            id = 0L,
            questionId = 1,
            answerId = answerId,
            answerContents = request.etcContents
        )
    }

    fun toDto() = Dto(id, device, questionId, answerId, answerContents, updatedAt, createdAt)
}
