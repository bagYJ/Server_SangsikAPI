package com.doinglab.sangsik.api.domains.meal.dto.request

import com.doinglab.sangsik.Exception.CustomException
import com.doinglab.sangsik.enums.EatType
import com.doinglab.sangsik.enums.StatusCode
import com.doinglab.sangsik.enums.Week
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
import java.time.LocalTime

data class RequestPostRepeatDto(
    @Schema(description = "식단 ID")
    val eatHistoryId: Long,
    @Schema(description = "반복기록명")
    val title: String,
    @Schema(description = "기록시작일")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    val startDate: LocalDate,
    @Schema(description = "기록종료일")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    val endDate: LocalDate?,
    @Schema(description = "기록요일")
    val days: List<Week>,
    @Schema(description = "섭취타입 (BREAKFAST: 아침, LUNCH: 점심, DINNER: 저녁, SNACK: 간식, MORNINGSNACK: 간식(오전), AFTERNOONSNACK: 간식(오후), NIGHTSNACK: 야식)")
    val eatType: EatType,
    @Schema(description = "기록시간")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    val repeatTime: LocalTime,
) {
    init {
        endDate?.let {
            if (endDate.isBefore(startDate)) throw CustomException(StatusCode.DATE_LESS_THEN_START)
        }
    }
}
