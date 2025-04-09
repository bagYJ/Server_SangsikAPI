package com.doinglab.sangsik.api.domains.announcement.repo

import com.doinglab.sangsik.api.domains.announcement.entity.Announcement
import com.doinglab.sangsik.api.domains.announcement.entity.AnnouncementUserRead
import com.doinglab.sangsik.define.CoreDefine
import com.doinglab.sangsik.define.CoreDefine.Companion.ANNOUNCEMENT
import com.doinglab.sangsik.define.CoreDefine.Companion.ANNOUNCEMENT_USER_READ
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository

@Repository
class AnnouncementRepo(
    @Qualifier("dietJpaQueryFactory") private val jpaQueryFactory: JPAQueryFactory,
    private val announcementJpaRepo: AnnouncementJpaRepo,
    private val announcementUserReadJpaRepo: AnnouncementUserReadJpaRepo
) {
    fun findAnnouncements(lastId: Long?, locale: String, limit:Long): List<Announcement.Dto> =
        jpaQueryFactory.selectFrom(ANNOUNCEMENT)
            .where(
                ANNOUNCEMENT.appName.eq(CoreDefine.APP_NAME)
                    .and(ANNOUNCEMENT.lang.eq(locale))
            )
            .apply {
                lastId?.let {
                    where(ANNOUNCEMENT.id.lt(it))
                }
            }
            .orderBy(ANNOUNCEMENT.id.desc()).limit(limit).fetch().map { it.toDto() }

    fun findAnnouncement(id: Long): Announcement.Dto? =
        jpaQueryFactory.selectFrom(ANNOUNCEMENT)
            .where(
                ANNOUNCEMENT.appName.eq(CoreDefine.APP_NAME)
                    .and(ANNOUNCEMENT.id.eq(id))
            )
            .fetchOne()?.toDto()

    fun findAnnouncementUserReads(userId: Long, ids: List<Long>): List<AnnouncementUserRead.Dto>? =
        jpaQueryFactory.selectFrom(ANNOUNCEMENT_USER_READ)
            .where(
                ANNOUNCEMENT_USER_READ.userId.eq(userId)
                    .and(ANNOUNCEMENT_USER_READ.announcementId.`in`(ids))
            )
            .fetch().map { it.toDto() }

    fun findAnnouncementUserRead(userId: Long, id: Long): AnnouncementUserRead.Dto? =
        jpaQueryFactory.selectFrom(ANNOUNCEMENT_USER_READ)
            .where(
                ANNOUNCEMENT_USER_READ.userId.eq(userId)
                    .and(ANNOUNCEMENT_USER_READ.announcementId.eq(id))
            )
            .fetchOne()?.toDto()

    fun findUnreadAnnouncement(userId: Long, locale: String): Long =
        jpaQueryFactory.select(ANNOUNCEMENT.count())
            .from(ANNOUNCEMENT)
            .leftJoin(ANNOUNCEMENT_USER_READ)
            .on(
                ANNOUNCEMENT.id.eq(ANNOUNCEMENT_USER_READ.announcementId)
                    .and(ANNOUNCEMENT_USER_READ.userId.eq(userId))
            )
            .where(
                ANNOUNCEMENT.appName.eq(CoreDefine.APP_NAME)
                    .and(ANNOUNCEMENT.lang.eq(locale))
                    .and(ANNOUNCEMENT_USER_READ.id.isNull)
            )
            .fetchFirst()

    fun saveAnnouncementUserRead(dto: AnnouncementUserRead.Dto): AnnouncementUserRead.Dto? =
        announcementUserReadJpaRepo.save(dto.toEntity()).toDto()
}
