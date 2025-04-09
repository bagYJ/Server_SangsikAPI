package com.doinglab.sangsik.utils

import com.ibm.icu.util.ChineseCalendar
import org.apache.commons.lang3.StringUtils
import org.apache.commons.lang3.time.DateFormatUtils
import java.time.Duration
import java.time.Instant.ofEpochMilli
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId.systemDefault
import java.util.*
import java.util.concurrent.TimeUnit.DAYS
import java.util.concurrent.TimeUnit.MILLISECONDS

object HolidayChecker

private val SOLAR_HOLIDAYS = listOf("0101", "0301", "0505", "0606", "0815", "1009", "1225")
private val LUNAR_HOLIDAYS = listOf("0101", "0102", "0408", "0814", "0815", "0816", "1230")

/**
 * 공휴일 여부
 */
fun isHoliday(timeMillis: Long): Boolean {
    return isLegalHoliday(timeMillis) || isWeekend(timeMillis) || isTemporaryHoliday(timeMillis)
}

/**
 * 주말을 제외한 공휴일 여부
 */
fun isHolidayWithoutWeekend(timeMillis: Long): Boolean {
    return isLegalHoliday(timeMillis) || isTemporaryHoliday(timeMillis)
}

/**
 * 음력날짜 구하기
 */
fun getLunarDate(tileMillis: Long): String {
    val cc = ChineseCalendar(Date(tileMillis))

    var mm = (cc[ChineseCalendar.MONTH] + 1).toString()
    mm = StringUtils.leftPad(mm, 2, "0")

    var dd = cc[ChineseCalendar.DAY_OF_MONTH].toString()
    dd = StringUtils.leftPad(dd, 2, "0")

    return mm + dd
}

/**
 * 법정휴일
 */
fun isLegalHoliday(timeMillis: Long): Boolean {
    val solarDate = DateFormatUtils.format(timeMillis, "MMdd")
    val lunarDate = getLunarDate(timeMillis)

    return SOLAR_HOLIDAYS.contains(solarDate) || LUNAR_HOLIDAYS.contains(lunarDate)
}

/**
 * 주말 (토,일)
 */
fun isWeekend(timeMillis: Long): Boolean {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = timeMillis

    // SUNDAY:1 SATURDAY:7
    val dayOfWeek = calendar[Calendar.DAY_OF_WEEK]
    return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY
}

/**
 * 임시공휴일 (매해 업데이트 해야 함). 2021년은 없음.
 */
fun isTemporaryHoliday(timeMillis: Long): Boolean = false

fun getNearestWorkingDay(
    inputDateTime: LocalDateTime,
    baseTimeForProcessingProgram: LocalTime = LocalTime.of(10, 0) // 프로그램 시작 판단 기준 시간. 오전 10시.
): LocalDate {
    var inputTimeMillis = inputDateTime.atZone(systemDefault()).toInstant().toEpochMilli()

    if (inputDateTime.toLocalTime().isAfter(baseTimeForProcessingProgram)) {
        inputTimeMillis += DAY_MILLIS
    }

    while (isHoliday(inputTimeMillis)) {
        inputTimeMillis += DAY_MILLIS
    }

    return LocalDateTime.ofInstant(ofEpochMilli(inputTimeMillis), systemDefault()).toLocalDate()
}

fun countHolidayBetweenDays(
    from: LocalDateTime,
    to: LocalDateTime,
): Int =
    if (to >= from) {
        Duration.between(from, to).toDays().toInt().plus(1)
    } else {
        0
    }

private val DAY_MILLIS = MILLISECONDS.convert(1L, DAYS)
