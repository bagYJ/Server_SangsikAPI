package com.doinglab.sangsik.api.domains.cgm.dto

data class UserLocaleDto(
    val userId: Long,
    val locale: String? = "en"
)
