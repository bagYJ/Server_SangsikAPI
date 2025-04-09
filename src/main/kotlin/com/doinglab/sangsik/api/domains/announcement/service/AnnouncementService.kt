package com.doinglab.sangsik.api.domains.announcement.service

import com.doinglab.sangsik.Exception.CustomException
import com.doinglab.sangsik.api.domains.announcement.dto.response.GetAnnouncementUnreadDto
import com.doinglab.sangsik.api.domains.announcement.dto.response.ResponseGetAnnouncementDto
import com.doinglab.sangsik.api.domains.announcement.dto.response.ResponseGetAnnouncementIdDto
import com.doinglab.sangsik.api.domains.announcement.entity.AnnouncementUserRead
import com.doinglab.sangsik.api.domains.announcement.repo.AnnouncementRepo
import com.doinglab.sangsik.define.CoreDefine.Companion.PAGE_LIMIT
import com.doinglab.sangsik.enums.Locale
import com.doinglab.sangsik.enums.StatusCode
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AnnouncementService(
    private val announcementRepo: AnnouncementRepo
) {
    fun doGetAnnouncement(userId: Long?, lastId: Long?, locale: String?): ResponseGetAnnouncementDto =
        announcementRepo.findAnnouncements(lastId, Locale.getValue(locale, Locale.announceLocaleList), PAGE_LIMIT).let { announcements ->
            val userReads = userId?.let {
                announcementRepo.findAnnouncementUserReads(userId, announcements.map { it.id })
            }

            ResponseGetAnnouncementDto(announcements, userId, userReads)
        }

    fun doGetAnnouncementId(userId: Long?, announcementId: Long): ResponseGetAnnouncementIdDto =
        announcementRepo.findAnnouncement(announcementId)?.let { announcement ->
            val userRead = userId?.let {
                announcementRepo.findAnnouncementUserRead(userId, announcement.id) ?: announcementRepo.saveAnnouncementUserRead(AnnouncementUserRead.Dto(userId, announcement.id))?.apply {
                    if (id < 1) throw CustomException(StatusCode.FAIL_INSERT)
                }
            }

            ResponseGetAnnouncementIdDto(announcement, userId, userRead)
        } ?: throw CustomException(StatusCode.NOT_FOUND_ANNOUNCEMENT)

    fun doGetAnnouncementUnread(userId: Long?, locale: String?): GetAnnouncementUnreadDto =
        userId?.let {
            GetAnnouncementUnreadDto(announcementRepo.findUnreadAnnouncement(userId, Locale.getValue(locale, Locale.announceLocaleList)))
        } ?: GetAnnouncementUnreadDto(0)
}
