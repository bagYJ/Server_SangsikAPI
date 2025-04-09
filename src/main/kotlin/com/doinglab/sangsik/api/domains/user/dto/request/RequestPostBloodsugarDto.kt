package com.doinglab.sangsik.api.domains.user.dto.request

import com.doinglab.sangsik.Exception.CustomException
import com.doinglab.sangsik.define.CoreTime.Companion.HHMMSS
import com.doinglab.sangsik.define.CoreTime.Companion.YYYYMMDD
import com.doinglab.sangsik.enums.BloodSugarInputType
import com.doinglab.sangsik.enums.MealTime
import com.doinglab.sangsik.enums.MealType
import com.doinglab.sangsik.enums.StatusCode
import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.Hidden
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
import java.time.LocalTime

data class RequestPostBloodsugarDto(
    @Schema(description = "등록 날짜 (yyyyMMdd)")
    val date: String,
    @Schema(description = "등록 시간 (HHmmss)")
    val time: String,
    @Schema(description = "혈당 입력 타입 (MORNING: 아침, AFTERNOON: 점심, DINNER: 저녁, BEFOREBED: 취침전, MORNINGSNACK: 오전간식, AFTERNOONSNACK: 오후간식, NIGHTSNACK: 야식)")
    val mealType: MealType,
    @Schema(description = "혈당 입력 시간 타입 (BEFORE: 식전, AFTER_1: 식후 1시간, AFTER: 식후 2시간)")
    val mealtime: MealTime?,
    @Schema(description = "혈당")
    val bloodsugar: Int
) {
    @Hidden
    @JsonIgnore
    val inputType: BloodSugarInputType = BloodSugarInputType.findByMealAndTimes(mealType, mealtime)!!
    @Hidden
    @JsonIgnore
    val inputDate: LocalDate = LocalDate.parse(date, YYYYMMDD)
    @Hidden
    @JsonIgnore
    val inputTime: LocalTime = LocalTime.parse(time, HHMMSS)

    init {
        if (bloodsugar < 1) throw CustomException(StatusCode.LESS_THEN_1)
        if (bloodsugar > 999) throw CustomException(StatusCode.GREATER_THEN_999)
    }
}
