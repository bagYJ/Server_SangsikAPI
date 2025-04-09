package com.doinglab.sangsik.api.domains.user.repo

import com.doinglab.sangsik.api.domains.user.entity.UserWithdrawalAnswer
import org.springframework.data.jpa.repository.JpaRepository

interface UserWithdrawalAnswerJpaRepo: JpaRepository<UserWithdrawalAnswer, Long>
