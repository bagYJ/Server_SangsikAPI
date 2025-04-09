package com.doinglab.sangsik.api.cgm.dto.response

import com.doinglab.sangsik.api.domains.cgm.entity.UserCgmToken
import com.doinglab.sangsik.utils.decrypt
import com.fasterxml.jackson.annotation.JsonProperty

data class ResponseCgmAccountPostTokenDto(
    @JsonProperty("access_token")
    val accessToken: String,
    @JsonProperty("refresh_token")
    val refreshToken: String,
    @JsonProperty("token_type")
    val tokenType: String,
    @JsonProperty("user_id")
    val userId: String,
    @JsonProperty("expires_in")
    val expiresIn: Long,
) {
    constructor(token: UserCgmToken.Dto, aesKey: String): this(
        accessToken = token.accessToken.decrypt(aesKey),
        refreshToken = token.refreshToken.decrypt(aesKey),
        tokenType = "",
        userId = token.cgmId,
        expiresIn = token.expiresIn
    )
}
