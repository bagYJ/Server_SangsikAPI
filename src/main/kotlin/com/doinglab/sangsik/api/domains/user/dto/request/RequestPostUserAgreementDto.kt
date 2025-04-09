package com.doinglab.sangsik.api.domains.user.dto.request

import com.doinglab.sangsik.Exception.CustomException
import com.doinglab.sangsik.enums.StatusCode
import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.Hidden
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "약관동의")
data class RequestPostUserAgreementDto(
    @Schema(description = "(필수) 서비스 이용 약관", type = "boolean")
    val isEssentialTermsOfService: Boolean,
    @Schema(description = "(필수) 개인정보 수집 및 이용", type = "boolean")
    val isEssentialCollectionPersonalInformation: Boolean,
    @Schema(description = "(필수) 개인정보 처리방침", type = "boolean")
    val isEssentialPersonalInformation: Boolean,
//    @Schema(description = "(필수) 만 14세 이상", type = "boolean")
//    val isEssentialOverAge14: Boolean,
    @Schema(description = "(선택) 마케팅 정보 수신", type = "boolean", required = false)
    val isSelectablePersonalMarketing: Boolean? = false
) {
    init {
        if (!isEssentialTermsOfService) throw CustomException(StatusCode.CHECK_TERMS_OF_SERVICE_AGREE)
        if (!isEssentialCollectionPersonalInformation) throw CustomException(StatusCode.CHECK_COLLECTION_PERSONAL_INFORMATION_AGREE)
        if (!isEssentialPersonalInformation) throw CustomException(StatusCode.CHECK_PERSONAL_INFORMATION_AGREE)
//        if (!isEssentialOverAge14) throw CustomException(StatusCode.CHECK_OVER_AGE_14_AGREE)
    }

    @Hidden
    @JsonIgnore
    val essentialTermsOfService: LocalDateTime = LocalDateTime.now()
    @Hidden
    @JsonIgnore
    val essentialCollectionPersonalInformation: LocalDateTime = LocalDateTime.now()
    @Hidden
    @JsonIgnore
    val essentialPersonalInformation: LocalDateTime = LocalDateTime.now()
    @Hidden
    @JsonIgnore
    val essentialOverAge14: LocalDateTime = LocalDateTime.now()
    @Hidden
    @JsonIgnore
    val selectablePersonalMarketing: LocalDateTime = isSelectablePersonalMarketing?.let {
        if (it) LocalDateTime.now()
        else LocalDateTime.of(1970, 1, 1, 0, 0)
    } ?: LocalDateTime.of(1970, 1, 1, 0, 0)
}
