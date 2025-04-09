package com.doinglab.sangsik.api.domains.cgm.dto.response

import com.doinglab.sangsik.api.domains.common.dto.BaseDto
import io.swagger.v3.oas.annotations.media.Schema

data class ResponseGetAuthorizeDto(
    override val body: GetAuthorizeBody
) : BaseDto() {
    data class GetAuthorizeBody(
        @Schema(description = "i-sens 로그인 uri")
        val authorizeUri: String,
        @Schema(description = "케어센스 상품 구매 uri")
        val goodsUri: String,
    )
}
