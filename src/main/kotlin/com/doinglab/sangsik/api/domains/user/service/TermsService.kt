package com.doinglab.sangsik.api.domains.user.service

import com.doinglab.sangsik.api.domains.user.dto.response.ResponseGetTermsDto
import com.doinglab.sangsik.api.domains.user.repo.TermsRepo
import com.doinglab.sangsik.enums.Locale
import org.springframework.stereotype.Service

@Service
class TermsService(
    private val termsRepo: TermsRepo
) {
    fun doGetTerms(locale: String?): ResponseGetTermsDto =
        ResponseGetTermsDto(termsRepo.findAppSetting(), Locale.getValue(locale, Locale.announceLocaleList))
}
