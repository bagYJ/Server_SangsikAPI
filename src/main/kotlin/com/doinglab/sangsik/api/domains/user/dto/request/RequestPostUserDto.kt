package com.doinglab.sangsik.api.domains.user.dto.request

import com.doinglab.sangsik.Exception.CustomException
import com.doinglab.sangsik.enums.LoginSource
import com.doinglab.sangsik.enums.StatusCode
import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.Hidden
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

data class RequestPostUserDto(
    @Schema(description = "이메일 아이디")
    var emailId: String? = "",
    @Schema(description = "이메일 패스워드")
    val password: String?,
    @Schema(description = "SNS 아이디")
    val socialId: String?,
    @Schema(description = "유저가 사용할 로그인/회원가입 방법 (LOCAL: 이메일, NAVER: 네이버, KAKAO: 카카오, APPLE: 애플, GOOGLE: 구글)")
    val loginSource: LoginSource,
    @Schema(description = "(필수) 서비스 이용 약관", type = "boolean")
    val isEssentialTermsOfService: Boolean,
    @Schema(description = "(필수) 개인정보 수집 및 이용", type = "boolean")
    val isEssentialCollectionPersonalInformation: Boolean,
    @Schema(description = "(필수) 개인정보 처리방침", type = "boolean")
    val isEssentialPersonalInformation: Boolean,
//    @Schema(description = "(필수) 만 14세 이상", type = "boolean")
//    val isEssentialOverAge14: Boolean,
    @Schema(description = "(선택) 마케팅 정보 수신", type = "boolean", required = false)
    val isSelectablePersonalMarketing: Boolean? = false,
    @Schema(description = "로케일정보 (ko, en...)", defaultValue = "ko")
    val locale: String? = "ko",
) {
    init {
        if (!isEssentialTermsOfService) throw CustomException(StatusCode.CHECK_TERMS_OF_SERVICE_AGREE)
        if (!isEssentialCollectionPersonalInformation) throw CustomException(StatusCode.CHECK_COLLECTION_PERSONAL_INFORMATION_AGREE)
        if (!isEssentialPersonalInformation) throw CustomException(StatusCode.CHECK_PERSONAL_INFORMATION_AGREE)
        if (loginSource != LoginSource.LOCAL && emailId.isNullOrBlank()) emailId = socialId
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
