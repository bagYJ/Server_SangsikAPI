package com.doinglab.sangsik.api.domains.announcement.repo

import com.doinglab.sangsik.api.domains.announcement.entity.AnnouncementUserRead
import org.springframework.data.jpa.repository.JpaRepository

interface AnnouncementUserReadJpaRepo: JpaRepository<AnnouncementUserRead, Long>
