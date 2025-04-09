package com.doinglab.sangsik.api.domains.user.dto.request

import com.doinglab.sangsik.Exception.CustomException
import com.doinglab.sangsik.define.CoreTime.Companion.YYYYMMDD
import com.doinglab.sangsik.enums.StatusCode
import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.Hidden
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

data class RequestPostWeightDto(
    @Schema(description = "등록 날짜 (yyyyMMdd)")
    val date: String,
    @Schema(description = "체중")
    val weight: Float
) {
    @JsonIgnore
    @Hidden
    val inputDate: LocalDate = LocalDate.parse(date, YYYYMMDD)

    init {
        if (inputDate.isAfter(LocalDate.now())) throw CustomException(StatusCode.DATE_GREATER_THEN_NOW)
    }
}
