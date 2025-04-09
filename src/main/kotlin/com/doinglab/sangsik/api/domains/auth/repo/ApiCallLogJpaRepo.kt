package com.doinglab.sangsik.api.domains.auth.repo

import com.doinglab.sangsik.api.domains.auth.entity.ApiCallLog
import org.springframework.data.jpa.repository.JpaRepository

interface ApiCallLogJpaRepo: JpaRepository<ApiCallLog, Long>
