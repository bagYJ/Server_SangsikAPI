package com.doinglab.sangsik.utils


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.ZoneId.systemDefault

internal class HolidayCheckerTest {
    @Test
    internal fun holiday_success() =
        assertTrue(isHoliday(LocalDateTime.of(2024, 5, 15, 0, 0).atZone(systemDefault()).toInstant().toEpochMilli()))

    @Test
    internal fun lunar_success() =
        assertEquals(getLunarDate(LocalDateTime.of(2024, 2, 21, 0, 0).atZone(systemDefault()).toInstant().toEpochMilli()), "0112")

    @Test
    internal fun holiday2_success() =
        assertTrue(isLegalHoliday(LocalDateTime.of(2024, 6, 6, 0, 0).atZone(systemDefault()).toInstant().toEpochMilli()))

    @Test
    internal fun between_date_success() =
        assertEquals(countHolidayBetweenDays(LocalDateTime.of(2024, 5, 30, 0, 0), LocalDateTime.of(2024, 6, 11, 0, 0)), 13)
}


