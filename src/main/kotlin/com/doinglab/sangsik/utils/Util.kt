package com.doinglab.sangsik.utils

import com.doinglab.sangsik.enums.UnitHeight
import com.doinglab.sangsik.enums.UnitWeight
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.net.InetAddress
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.reflect.full.memberProperties

inline fun <reified T> T.getLogger(): Logger = LoggerFactory.getLogger(T::class.java)

object Util

fun getAge(birth: LocalDate): Int {
    val now = LocalDate.now()
    val firstDayOfYear = LocalDate.of(now.year, 1, 1)
    return (now.year - birth.year).let {
        if (firstDayOfYear.until(birth).days > firstDayOfYear.until(now).days) {
            it + 1
        } else {
            it
        }
    }
}

fun <T : Any> T.getPropertyValue(key: String): String {
    val property = this::class.memberProperties.find { it.name.lowercase() == key.lowercase() }
    return property?.getter?.call(this).toString()
}

fun <T> T.serialize(): String = jacksonObjectMapper().registerModules(JavaTimeModule()).writeValueAsString(this)

inline fun <reified T> String.deserialzie(): T = jacksonObjectMapper()
    .registerModules(JavaTimeModule())
    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    .readValue(this, object: TypeReference<T>() {})

fun getRemoteAddress(request: HttpServletRequest): String {
    var ip = request.getHeader("X-Forwarded-For")
    if (ip.isNullOrEmpty() || ip == "unknown") ip = request.getHeader("Proxy-Client-IP")
    if (ip.isNullOrEmpty() || ip == "unknown") ip = request.getHeader("WL-Proxy-Client-IP")
    if (ip.isNullOrEmpty() || ip == "unknown") ip = request.getHeader("HTTP_CLIENT_IP")
    if (ip.isNullOrEmpty() || ip == "unknown") ip = request.getHeader("HTTP_X_FORWARDED_FOR")
    if (ip.isNullOrEmpty() || ip == "unknown") ip = request.remoteAddr

    val ipList = ip.split(",")
    if (ipList.size > 1) {
        for(i in ipList.indices) {
            if (!InetAddress.getByName(ipList[i].trim()).isSiteLocalAddress) {
                ip = ipList[i]
                break
            }
        }
    }

    return ip
}

fun LocalDateTime.toUtcTime(): LocalDateTime =
    this.minusHours(9)

fun LocalDateTime.toSeoulTime(): LocalDateTime =
    this.plusHours(9)

fun LocalDateTime.between(before: LocalDateTime, after: LocalDateTime): Boolean =
    when (before < after) {
        true -> this in before..after
        false -> this in after..before
    }

fun LocalDate.between(before: LocalDate, after: LocalDate): Boolean =
    when (before < after) {
        true -> this in before..after
        false -> this in after..before
    }

fun LocalTime.between(before: LocalTime, after: LocalTime): Boolean =
    when (before < after) {
        true -> this in before..after
        false -> this in after..before
    }

fun Float.weight(unit: UnitWeight): Float =
    when (unit) {
        UnitWeight.LBS -> String.format("%.1f", this.times(unit.calc)).toFloat()
        else -> this
    }

fun Float.height(unit: UnitHeight): Float =
    when (unit) {
        UnitHeight.FT -> String.format("%.1f", this.div(unit.calc)).toFloat()
        else -> this
    }

fun Float.weight(defaultUnit: UnitWeight, unit: UnitWeight): Float =
    when {
        defaultUnit == unit -> this
        defaultUnit != unit && defaultUnit == UnitWeight.LBS -> String.format("%.1f", this.div(unit.calc)).toFloat()
        defaultUnit != unit && defaultUnit == UnitWeight.KG -> String.format("%.1f", this.times(unit.calc)).toFloat()
        else -> this
    }

fun Float.height(defaultUnit: UnitHeight, unit: UnitHeight): Float =

    when {
        defaultUnit == unit -> this
        defaultUnit != unit && defaultUnit == UnitHeight.FT -> String.format("%.1f", this.times(unit.calc)).toFloat()
        defaultUnit != unit && defaultUnit == UnitHeight.CM -> String.format("%.1f", this.div(unit.calc)).toFloat()
        else -> this
    }

fun Double.weight(unit: UnitWeight): Double =
    when (unit) {
        UnitWeight.LBS -> String.format("%.1f", this.times(unit.calc)).toDouble()
        else -> this
    }

fun Double.weight(defaultUnit: UnitWeight, unit: UnitWeight): Double =
    when {
        defaultUnit == unit -> this
        defaultUnit != unit && defaultUnit == UnitWeight.LBS -> String.format("%.1f", this.div(unit.calc)).toDouble()
        defaultUnit != unit && defaultUnit == UnitWeight.KG -> String.format("%.1f", this.times(unit.calc)).toDouble()
        else -> this
    }

fun Double.amount(amount: Double): Double =
    if (this > 0) this * amount
    else this

fun Long.range(num: Long): List<Long> =
    when (this < (this + num)) {
        true -> this..(this + num)
        false -> (this + num)..this
    }.toList()
