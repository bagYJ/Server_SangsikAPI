package com.doinglab.sangsik.api.domains.user.dto.response

import com.doinglab.sangsik.api.domains.common.dto.BaseDto

data class ResponsePutUserTokenDto(
    override val body: String? = null
): BaseDto()
