package com.doinglab.sangsik.api.domains.user.dto.response

import com.doinglab.sangsik.api.domains.common.dto.BaseDto
import com.doinglab.sangsik.api.domains.user.entity.AppSetting
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "약관")
data class ResponseGetTermsDto(
    override val body: GetTermsBody
): BaseDto() {
    data class GetTermsBody(
        @Schema(description = "서비스이용약관")
        val urlTermOfService: String,
        @Schema(description = "개인정보처리방침")
        val urlPrivacyPolicy: String,
        @Schema(description = "개인정보수집 및 이용동의")
        val urlCollectionPersonalInformation: String,
        @Schema(description = "기관연결")
        val urlProgramConnect: String
    )

    constructor(terms: AppSetting.Dto, locale: String?): this(
        when (locale) {
            "en" -> GetTermsBody(terms.urlTermOfService.en, terms.urlPrivacyPolicy.en, terms.urlCollectionPersonalInformation.en, terms.urlProgramConnect.en)
            else -> GetTermsBody(terms.urlTermOfService.ko, terms.urlPrivacyPolicy.ko, terms.urlCollectionPersonalInformation.ko, terms.urlProgramConnect.ko)
        }
    )
}
