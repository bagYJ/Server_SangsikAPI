package com.doinglab.sangsik.api.domains.user.dto.response

import com.doinglab.sangsik.api.domains.common.dto.BaseDto
import com.doinglab.sangsik.api.domains.user.entity.User
import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "로그인/회원가입")
data class ResponseUserDto(
    @Schema(description = "결과")
    override val body: UserBodyDto
): BaseDto() {
    data class UserBodyDto(
        @Schema(description = "회원고유값")
        val id: Long,
        @Schema(description = "엑세스토큰")
        val accessToken: String,
        @JsonIgnore
        val firstLogin: Boolean
    )

    constructor(id: Long, token: String, firstLogin: Boolean): this(UserBodyDto(
        id = id,
        accessToken = token,
        firstLogin = firstLogin,
    ))

    constructor(user: User.Dto): this(UserBodyDto(
        id = user.id,
        accessToken = user.accessToken,
        firstLogin = user.firstlogin,
    ))
}
