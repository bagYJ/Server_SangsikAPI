package com.doinglab.sangsik.api.domains.user.dto.request

import com.doinglab.sangsik.enums.InquiryType
import org.springframework.web.multipart.MultipartFile

data class RequestPostInquiryDto(
    val inqueryType: InquiryType,
    val content: String,
    val file: List<MultipartFile>?
)
