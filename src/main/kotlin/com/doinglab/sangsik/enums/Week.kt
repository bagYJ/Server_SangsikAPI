package com.doinglab.sangsik.enums


import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.time.DayOfWeek

enum class Week(
    val value: Int,
    val desc: String,
    val week: DayOfWeek
) {
    SUN(0, "일", DayOfWeek.SUNDAY), MON(1, "월", DayOfWeek.MONDAY), TUE(2, "화", DayOfWeek.TUESDAY), WED(3, "수", DayOfWeek.WEDNESDAY), THU(4, "목", DayOfWeek.THURSDAY), FRI(5, "금", DayOfWeek.FRIDAY), SAT(6, "토", DayOfWeek.SATURDAY);

    companion object {
        fun convertToWeeks(value: String): List<Week> = jacksonObjectMapper().readValue(value, object : TypeReference<List<Week>>() {})

        fun convertToString(weeks: List<Week>): String = weeks.map { it.value }.joinToString(",")
    }
}
