package com.doinglab.sangsik.api.domains.cgm.repo

import com.doinglab.sangsik.api.domains.cgm.entity.UserCgmToken
import org.springframework.data.jpa.repository.JpaRepository

interface UserCgmTokenJpaRepo: JpaRepository<UserCgmToken, Long>
