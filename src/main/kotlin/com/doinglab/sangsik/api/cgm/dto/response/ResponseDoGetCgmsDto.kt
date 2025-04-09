package com.doinglab.sangsik.api.cgm.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class ResponseDoGetCgmsDto(
    @JsonProperty("serial_number")
    val serialNumber: String,
    @JsonProperty("seq_number")
    val seqNumber: Long,
    @JsonProperty("event_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    val eventAt: LocalDateTime,
    val stage: Int,
    @JsonProperty("initial_value")
    val initialValue: Int,
    val value: Int,
    @JsonProperty("trend_rate")
    val trendRate: Float,
    val trend: Int,
    @JsonProperty("error_code")
    val errorCode: Int,
    @JsonProperty("min_max_flag")
    val minMaxFlag: Int
)
