package com.doinglab.sangsik.api.domains.announcement.dto.response

import com.doinglab.sangsik.api.domains.announcement.dto.response.ResponseGetAnnouncementDto.GetAnnouncementBody
import com.doinglab.sangsik.api.domains.announcement.entity.Announcement
import com.doinglab.sangsik.api.domains.announcement.entity.AnnouncementUserRead
import com.doinglab.sangsik.api.domains.common.dto.BaseDto

data class ResponseGetAnnouncementIdDto(
    override val body: GetAnnouncementBody
): BaseDto() {
    constructor(announcement: Announcement.Dto, userId: Long?, userRead: AnnouncementUserRead.Dto?): this(
        GetAnnouncementBody(announcement, userId, userRead)
    )
}
