package com.doinglab.sangsik.api.domains.user.entity

import com.doinglab.sangsik.enums.Device
import com.doinglab.sangsik.enums.Gender
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(name = "UserWithdrawal")
class UserWithdrawal(
    @Id
    val userId: Long,
    val birthDate: LocalDate,
    val age: Int,
    @Convert(converter = Gender.Converter::class)
    val gender: Gender?,
    @Convert(converter = Device.Converter::class)
    val device: Device,
    val createdAt: LocalDateTime
) {
    data class Dto(
        val userId: Long,
        val birthDate: LocalDate,
        val age: Int,
        val gender: Gender?,
        val device: Device = Device.IOS,
        val createdAt: LocalDateTime = LocalDateTime.now()
    ) {
        fun toEntity() = UserWithdrawal(userId, birthDate, age, gender, device, createdAt)

        constructor(user: User.Dto): this(
            userId = user.id,
            birthDate = user.birthDate.toLocalDate(),
            age = user.age,
            gender = user.gender,
        )
    }

    fun toDto() = Dto(userId, birthDate, age, gender, device, createdAt)
}
