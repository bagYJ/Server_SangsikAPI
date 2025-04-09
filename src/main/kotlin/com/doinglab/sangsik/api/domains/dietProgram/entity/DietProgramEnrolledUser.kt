package com.doinglab.sangsik.api.domains.dietProgram.entity

import com.doinglab.sangsik.config.converter.NumericBooleanConverter
import com.doinglab.sangsik.enums.EnrolledStatus
import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime

@Entity(name = "DietProgramEnrolledUser")
@DynamicUpdate
class DietProgramEnrolledUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val userId: Long,
    val programId: Long,
    val staffId: Long,
    val roomId: Long,
    @Convert(converter = NumericBooleanConverter::class)
    val isFinished: Boolean,
    val enrollDate: LocalDateTime,
    val startDate: LocalDateTime,
    val finishDate: LocalDateTime,
    @Convert(converter = EnrolledStatus.Converter::class)
    val status: EnrolledStatus,
    val appointmentDate: LocalDateTime,
    @Convert(converter = NumericBooleanConverter::class)
    val hadAppointment: Boolean,
    val initFinishDate: LocalDateTime,
    val memberNo: String?,
    val essentialCollectionPersonalInformation: LocalDateTime?,
    val essentialSensitiveInformation: LocalDateTime?,
    val selectableCollectionPersonalInformation: LocalDateTime?,
    val recommendCalorie: Float?,
    val recommendCalorieDate: LocalDateTime?,
    @Convert(converter = NumericBooleanConverter::class)
    val calorieIntegrate: Boolean,
    @Convert(converter = NumericBooleanConverter::class)
    val useChatting: Boolean? = null,
) {
    data class Dto(
        val id: Long,
        val userId: Long,
        val programId: Long,
        val staffId: Long,
        val roomId: Long,
        val isFinished: Boolean = false,
        val enrollDate: LocalDateTime = LocalDateTime.now(),
        val startDate: LocalDateTime,
        val appointmentDate: LocalDateTime = LocalDateTime.of(2000, 1, 1, 0, 0),
        val hadAppointment: Boolean = false,
        val initFinishDate: LocalDateTime,
        val memberNo: String? = "",
        val essentialCollectionPersonalInformation: LocalDateTime? = LocalDateTime.of(2000, 1, 1, 0, 0),
        val essentialSensitiveInformation: LocalDateTime? = LocalDateTime.of(2000, 1, 1, 0, 0),
        val selectableCollectionPersonalInformation: LocalDateTime? = LocalDateTime.of(2000, 1, 1, 0, 0),
        val recommendCalorie: Float? = null,
        val recommendCalorieDate: LocalDateTime? = null,
        val calorieIntegrate: Boolean = false,
        val useChatting: Boolean? = null,

        var finishDate: LocalDateTime,
        var status: EnrolledStatus
    ) {
        fun toEntity() = DietProgramEnrolledUser(id, userId, programId, staffId, roomId, isFinished, enrollDate, startDate, finishDate, status, appointmentDate, hadAppointment, initFinishDate, memberNo, essentialCollectionPersonalInformation, essentialSensitiveInformation, selectableCollectionPersonalInformation, recommendCalorie, recommendCalorieDate, calorieIntegrate, useChatting)
    }

    fun toDto() = Dto(id, userId, programId, staffId, roomId, isFinished, enrollDate, startDate, appointmentDate, hadAppointment, initFinishDate, memberNo, essentialCollectionPersonalInformation, essentialSensitiveInformation, selectableCollectionPersonalInformation, recommendCalorie, recommendCalorieDate, calorieIntegrate, useChatting, finishDate, status)
}
