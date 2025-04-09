package com.doinglab.sangsik.api.cgm.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class ResponseDoGetSensorsDto(
    @JsonProperty("serial_number")
    val serialNumber: String,
    @JsonProperty("started_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    val startedAt: LocalDateTime,
    @JsonProperty("ended_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    val endedAt: LocalDateTime,
)
