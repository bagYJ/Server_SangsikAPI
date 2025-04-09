package com.doinglab.sangsik.api.domains.announcement.mock

import com.doinglab.sangsik.api.domains.announcement.entity.Announcement
import com.doinglab.sangsik.api.domains.announcement.entity.AnnouncementUserRead
import java.time.LocalDateTime

val announcement = Announcement.Dto(
    id = 1L,
    appName = "sangsik",
    title = "TC",
    lang = "ko",
    url = "sangsik.doinglab.dev",
    isPinned = false,
    checkRead = false,
    createdAt = LocalDateTime.now(),
    updatedAt = LocalDateTime.now()
)

val announcementUserRead = AnnouncementUserRead.Dto(1L, 1L)
val announcementUserReadResult = AnnouncementUserRead.Dto(1L, 1L, 1L, LocalDateTime.now())
