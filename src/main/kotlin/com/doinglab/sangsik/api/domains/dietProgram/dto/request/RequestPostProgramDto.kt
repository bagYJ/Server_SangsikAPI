package com.doinglab.sangsik.api.domains.dietProgram.dto.request

import com.doinglab.sangsik.Exception.CustomException
import com.doinglab.sangsik.enums.StatusCode
import com.doinglab.sangsik.utils.getAge
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.Hidden
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
import java.time.LocalDateTime

@Schema(description = "다이어트 프로그램 등록")
data class RequestPostProgramDto(
    @Schema(description = "기관 코드")
    val code: String,
    @Schema(description = "다이어트 프로그램 아이디")
    val dietProgramId: Long,
    @Schema(description = "개인정보 수집 및 활용 동의", type = "boolean")
    val isEssentialCollectionPersonalInformation: Boolean,
    @Schema(description = "민감정보 수집 및 이용 동의", type = "boolean")
    val isEssentialSensitiveInformation: Boolean,
    @Schema(description = "개인정보 수집 및 이용 동의", type = "boolean", required = false)
    val isSelectableCollectionPersonalInformation: Boolean? = false,
    @Schema(description = "이름")
    val name: String,
    @Schema(description = "생년월일")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    val birthDate: LocalDate,
    @Schema(description = "회원번호")
    val memberNo: String?
) {
    init {
        if (getAge(birthDate) < 14) throw CustomException(StatusCode.UNDER_14_YEARS)
    }

    @Hidden
    @JsonIgnore
    val essentialCollectionPersonalInformation: LocalDateTime = LocalDateTime.now()

    @Hidden
    @JsonIgnore
    val essentialSensitiveInformation: LocalDateTime = LocalDateTime.now()

    @Hidden
    @JsonIgnore
    val selectableCollectionPersonalInformation: LocalDateTime? = isSelectableCollectionPersonalInformation?.let {
        if (it) LocalDateTime.now()
        else LocalDateTime.of(1970, 1, 1, 0, 0)
    } ?: LocalDateTime.of(1970, 1, 1, 0, 0)
}
