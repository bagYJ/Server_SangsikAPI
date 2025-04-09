package com.doinglab.sangsik.api.domains.announcement.dto.response

import com.doinglab.sangsik.api.domains.common.dto.BaseDto
import io.swagger.v3.oas.annotations.media.Schema

data class GetAnnouncementUnreadDto(
    @Schema(description = "읽지 않은 공지사항 수")
    override val body: Long
): BaseDto()
