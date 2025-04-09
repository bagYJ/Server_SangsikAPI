package com.doinglab.sangsik.api.domains.announcement.repo

import com.doinglab.sangsik.api.domains.announcement.entity.Announcement
import org.springframework.data.jpa.repository.JpaRepository

interface AnnouncementJpaRepo: JpaRepository<Announcement, Long>
