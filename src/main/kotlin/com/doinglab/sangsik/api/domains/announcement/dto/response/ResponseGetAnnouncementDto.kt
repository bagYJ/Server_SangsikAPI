package com.doinglab.sangsik.api.domains.announcement.dto.response

import com.doinglab.sangsik.api.domains.announcement.entity.Announcement
import com.doinglab.sangsik.api.domains.announcement.entity.AnnouncementUserRead
import com.doinglab.sangsik.api.domains.common.dto.BaseDto
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

data class ResponseGetAnnouncementDto(
    override val body: List<GetAnnouncementBody>
): BaseDto() {
    data class GetAnnouncementBody(
        @Schema(description = "공지사항 ID")
        val id: Long,
        @Schema(description = "공지사항 제목")
        val title: String,
        @Schema(description = "공지사항 언어 (ko, en)")
        val lang: String,
        @Schema(description = "공지사항 url")
        val url: String,
        @Schema(description = "공지사항 핀여부")
        val isPinned: Boolean,
        @Schema(description = "공지사항 읽음 여부")
        val isRead: Boolean,
        @Schema(description = "공지사항 등록일")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        val createdAt: LocalDateTime
    ) {
        constructor(announcement: Announcement.Dto, userId: Long?, userRead: AnnouncementUserRead.Dto?): this(
            id = announcement.id,
            title = announcement.title,
            lang = announcement.lang,
            url = announcement.url,
            isPinned = announcement.isPinned,
            isRead = userId?.let {
                userRead != null
            } ?: true,
            createdAt = announcement.createdAt
        )
    }

    constructor(announcements: List<Announcement.Dto>, userId: Long?, userReads: List<AnnouncementUserRead.Dto>?): this(announcements.map { announcement ->
        GetAnnouncementBody(announcement, userId, userReads?.firstOrNull { announcement.id == it.announcementId })
    })
}
