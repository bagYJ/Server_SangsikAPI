package com.doinglab.sangsik.api.domains.user.repo

import com.doinglab.sangsik.api.domains.user.entity.UserHistory
import org.springframework.data.jpa.repository.JpaRepository

interface UserHistoryJpaRepo: JpaRepository<UserHistory, Long>
