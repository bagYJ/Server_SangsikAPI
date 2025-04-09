package com.doinglab.sangsik.api.domains.dietProgram.entity

import com.doinglab.sangsik.config.converter.NumericBooleanConverter
import com.doinglab.sangsik.enums.AppointmentType
import com.doinglab.sangsik.enums.DietProgramOption
import com.doinglab.sangsik.enums.DietProgramStatus
import com.doinglab.sangsik.enums.EnrollType
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "DietProgram")
class DietProgram(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val showPriority: Int,
    val type: String,
    val dietProgramCategoryID: Int,
    val appName: String,
    val title: String,
    val thumbnail: String?,
    val squareThumbnail: String?,
    @Convert(converter = AppointmentType.Converter::class)
    val appointmentType: AppointmentType,
    val description: String,
    val staffId: Long,
    val partnerCompanyId: Long,
    val price: Int,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    @Convert(converter = EnrollType.Converter::class)
    val enrollType: EnrollType,
    val maxUsers: Int,
    val dutyDays: Int,
    val registerHour: Int,
    val waitingDays: Int,
    val contents: String,
    val canChatDays: Int,
    @Convert(converter = NumericBooleanConverter::class)
    val canReEntry: Boolean,
    @Convert(converter = NumericBooleanConverter::class)
    val isDeleted: Boolean,
    @Convert(converter = DietProgramStatus.Converter::class)
    val status: DietProgramStatus,
    val shortDescription: String,
    val color: String,
    @Convert(converter = DietProgramOption.Converter::class)
    val options: List<Option>
) {
    data class Dto(
        val id: Long,
        val showPriority: Int,
        val type: String,
        val dietProgramCategoryID: Int,
        val appName: String,
        val title: String,
        val thumbnail: String?,
        val squareThumbnail: String?,
        val appointmentType: AppointmentType,
        val description: String,
        val staffId: Long,
        val partnerCompanyId: Long,
        val price: Int,
        val startDate: LocalDateTime,
        val endDate: LocalDateTime,
        val enrollType: EnrollType,
        val maxUsers: Int,
        val dutyDays: Int,
        val registerHour: Int,
        val waitingDays: Int,
        val contents: String,
        val canChatDays: Int,
        val canReEntry: Boolean,
        val isDeleted: Boolean,
        val status: DietProgramStatus,
        val shortDescription: String,
        val color: String,
        val options: List<Option>
    )

    data class Option(
        val name: DietProgramOption,
        val isUse: Boolean
    )

    fun toDto() = Dto(id, showPriority, type, dietProgramCategoryID, appName, title, thumbnail, squareThumbnail, appointmentType, description, staffId, partnerCompanyId, price, startDate, endDate, enrollType, maxUsers, dutyDays, registerHour, waitingDays, contents, canChatDays, canReEntry, isDeleted, status, shortDescription, color, options)
}
