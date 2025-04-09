package com.doinglab.sangsik.api.domains.user.repo

import com.doinglab.sangsik.api.domains.user.entity.UserPushToken
import org.springframework.data.jpa.repository.JpaRepository

interface UserPushTokenJpaRepo: JpaRepository<UserPushToken, Long>
