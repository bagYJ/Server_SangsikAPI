package com.doinglab.sangsik.define

import org.springframework.context.annotation.Configuration
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Configuration
class CoreTime {
    companion object {
        val YYYY_MM_DD_HH_MM_SS: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val YYYY_MM_DD: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val HH_MM_SS: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        val YYYYMMDDHHMMSS: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
        val YYYYMMDD: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd")
        val HHMMSS: DateTimeFormatter = DateTimeFormatter.ofPattern("HHmmss")
        val YYYY_MM_DD_T_HH_MM_SS_XXX: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")

        val ZONE_UTC = ZoneId.of("UTC")
        val ZONE_SEOUL = ZoneId.of("Asia/Seoul")
    }
}
