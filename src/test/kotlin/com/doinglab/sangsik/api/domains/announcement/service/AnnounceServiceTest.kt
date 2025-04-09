package com.doinglab.sangsik.api.domains.announcement.service

import com.doinglab.sangsik.api.domains.announcement.mock.announcement
import com.doinglab.sangsik.api.domains.announcement.mock.announcementUserReadResult
import com.doinglab.sangsik.api.domains.announcement.repo.AnnouncementRepo
import com.doinglab.sangsik.enums.Locale
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class AnnounceServiceTest {
    private val announcementRepo: AnnouncementRepo = mockk()
    private val announcementService = AnnouncementService(announcementRepo)

    @Test
    internal fun announce_list() {
        every { announcementRepo.findAnnouncements(0L, Locale.KO.value, 20) } returns listOf(announcement)
        every { announcementRepo.findAnnouncementUserReads(0L, listOf(1L)) } returns null
        announcementService.doGetAnnouncement(0L, 0L, Locale.KO.value).run {
            assertThat(body[0].id).isEqualTo(1L)
        }
    }

    @Test
    internal fun announce_detail() {
        every { announcementRepo.findAnnouncement(1L) } returns announcement
        every { announcementRepo.findAnnouncementUserRead(1L, 1L) } returns announcementUserReadResult
        announcementService.doGetAnnouncementId(1L, 1L).run {
            assertThat(body.id).isEqualTo(1L)
        }
    }

    @Test
    internal fun announce_unread() {
        every { announcementRepo.findUnreadAnnouncement(1L, Locale.KO.value) } returns 0L
        announcementService.doGetAnnouncementUnread(1L, Locale.KO.value).run {
            assertEquals(body, 0L)
        }
    }
}
