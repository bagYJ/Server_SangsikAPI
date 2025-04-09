package com.doinglab.sangsik.api.domains.user.entity

import com.doinglab.sangsik.enums.BloodSugarInputType
import com.doinglab.sangsik.enums.BloodSugarStatus
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "UserBloodSugar")
class UserBloodSugar(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val userId: Long,
    val inputDate: String,
    val inputTime: String,
    @Enumerated(EnumType.STRING)
    val inputType: BloodSugarInputType,
    val bloodSugar: Int,
    val memo: String? = "",
    @Convert(converter = BloodSugarStatus.Converter::class)
    val status: BloodSugarStatus,
    val updatedAt: LocalDateTime,
    val createdAt: LocalDateTime
) {
    data class Dto(
        val id: Long,
        val userId: Long,
        val status: BloodSugarStatus = BloodSugarStatus.REGIST,
        val createdAt: LocalDateTime = LocalDateTime.now(),

        var inputDate: String,
        var inputType: BloodSugarInputType,
        var memo: String? = "",
        var inputTime: String = "",
        var bloodSugar: Int = 0,
        var updatedAt: LocalDateTime = LocalDateTime.now()
    ) {
        fun toEntity() = UserBloodSugar(id, userId, inputDate, inputTime, inputType, bloodSugar, memo, status, updatedAt, createdAt)
    }

    fun toDto() = Dto(id, userId, status, createdAt, inputDate, inputType, memo, inputTime, bloodSugar, updatedAt)
}
