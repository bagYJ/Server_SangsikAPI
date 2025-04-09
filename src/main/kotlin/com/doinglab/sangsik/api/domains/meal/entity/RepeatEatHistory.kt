package com.doinglab.sangsik.api.domains.meal.entity

import com.doinglab.sangsik.api.domains.meal.dto.request.RequestPostRepeatDto
import com.doinglab.sangsik.enums.EatType
import com.doinglab.sangsik.enums.Week
import com.doinglab.sangsik.utils.toJsonString
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate
import java.time.LocalTime

@Entity(name = "RepeatEatHistory")
class RepeatEatHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val userId: Long,
    val eatHistoryId: Long,
    val title: String,
    val imagePath: String?,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    val startDate: LocalDate,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    val endDate: LocalDate? = null,
    val days: String,
    val eatType: EatType,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    val repeatTime: LocalTime,
    val calorie: Double = -1.0
) {
    data class Dto(
        val id: Long,
        val userId: Long,
        val eatHistoryId: Long,
        val title: String,
        val imagePath: String?,
        val startDate: LocalDate,
        val endDate: LocalDate? = null,
        val days: List<Week>,
        val eatType: EatType,
        val repeatTime: LocalTime,
        val calorie: Double = -1.0
    ) {
        fun toEntity() = RepeatEatHistory(id, userId, eatHistoryId, title, imagePath, startDate, endDate, days.toJsonString(), eatType, repeatTime, calorie)

        constructor(meal: EatHistory.Dto, request: RequestPostRepeatDto, calorie: Double) : this(
            id = 0L,
            userId = meal.userId,
            eatHistoryId = meal.id,
            title = request.title,
            imagePath = meal.imgThumbPath,
            startDate = request.startDate,
            endDate = request.endDate,
            eatType = request.eatType,
            repeatTime = request.repeatTime,
            days = request.days,
            calorie = calorie
        )
    }

    fun toDto() = Dto(id, userId, eatHistoryId, title, imagePath, startDate, endDate, Week.convertToWeeks(days), eatType, repeatTime, calorie)
}
