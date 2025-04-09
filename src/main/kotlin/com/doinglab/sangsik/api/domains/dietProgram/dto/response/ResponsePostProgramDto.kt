package com.doinglab.sangsik.api.domains.dietProgram.dto.response

import com.doinglab.sangsik.api.domains.common.dto.BaseDto
import io.swagger.v3.oas.annotations.media.Schema

data class ResponsePostProgramDto(
    override val body: PostProgramBodyDto
): BaseDto() {
    data class PostProgramBodyDto(
        @Schema(description = "다이어트 프로그램 아이디")
        val dietProgramId: Long,
        @Schema(description = "채팅방 아이디")
        val chatRoomId: Long
    )

    constructor(dietProgramId: Long, chatRoomId: Long) : this(PostProgramBodyDto(
        dietProgramId = dietProgramId,
        chatRoomId = chatRoomId
    ))
}
