package com.doinglab.sangsik.api.domains.user.repo

import com.doinglab.sangsik.api.domains.user.entity.UserAgreement
import org.springframework.data.jpa.repository.JpaRepository

interface UserAgreementJpaRepo: JpaRepository<UserAgreement, Long>
