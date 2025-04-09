package com.doinglab.sangsik.api.cgm.dto.request

import org.springframework.web.bind.annotation.RequestPart

data class RequestCgmAccountPostTokenDto(
    @RequestPart("grant_type")
    val grantType: String = "authorization_code",
    val code: String,
    @RequestPart("client_id")
    val clientId: String,
    @RequestPart("client_secret")
    val clientSecret: String,
    @RequestPart("redirect_uri")
    val redirectUri: String
)
