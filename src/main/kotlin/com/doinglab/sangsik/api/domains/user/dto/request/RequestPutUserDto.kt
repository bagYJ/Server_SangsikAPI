package com.doinglab.sangsik.api.domains.user.dto.request

import com.doinglab.sangsik.Exception.CustomException
import com.doinglab.sangsik.enums.ActivityLevel
import com.doinglab.sangsik.enums.Gender
import com.doinglab.sangsik.enums.StatusCode
import com.doinglab.sangsik.utils.getAge
import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.Hidden
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
import java.time.LocalDateTime

data class RequestPutUserDto(
    @Schema(defaultValue = "성별 (MALE: 남성, FEMALE: 여성)")
    val gender: Gender,
    @Schema(defaultValue = "생년월일")
    val birthday: LocalDate,
    @Schema(defaultValue = "키")
    val height: Float,
    @Schema(defaultValue = "몸무게")
    val weight: Float,
    @Schema(description = "평소활동량 (NONE: 미설정, INACTIVE: 주로 앉아있어요, LOWACTIVE: 가볍게 활동해요, ACTIVE: 꾸준히 운동해요, VERYACTIVE: 매우 활동적이에요)")
    val activityLevel: ActivityLevel
) {
    @Hidden
    @JsonIgnore
    val birthDate: LocalDateTime = birthday.atStartOfDay()

    init {
        if (getAge(birthday) < 14) throw CustomException(StatusCode.UNDER_14_YEARS)
//        if (height > 300 || height < 50) throw CustomException(StatusCode.BAD_REQUEST)
//        if (weight > 300 || weight < 10) throw CustomException(StatusCode.BAD_REQUEST)
    }
}
