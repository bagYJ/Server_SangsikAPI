package com.doinglab.sangsik.api.domains.user.repo

import com.doinglab.sangsik.api.domains.user.entity.UserWithdrawal
import org.springframework.data.jpa.repository.JpaRepository

interface UserWithdrawalJpaRepo: JpaRepository<UserWithdrawal, Long>
