package com.doinglab.sangsik.api.domains.user.dto.request

import com.doinglab.sangsik.Exception.CustomException
import com.doinglab.sangsik.enums.StatusCode
import io.swagger.v3.oas.annotations.media.Schema

data class RequestDeleteUserDto(
    @Schema(description = "탈퇴 답변")
    val answerId: List<Int>,
    @Schema(description = "기타 질문시 답변 내용")
    val etcContents: String? = null
) {
    init {
        if (answerId.minOrNull()!! < 1 /*|| answerId.maxOrNull()!! > 7*/) throw CustomException(StatusCode.CHECK_ANSWER_ID)
    }
}
