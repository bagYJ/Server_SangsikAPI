package com.doinglab.sangsik.api.domains.cgm.repo

import com.doinglab.sangsik.api.domains.cgm.entity.UserCgmBloodGlucose
import org.springframework.data.jpa.repository.JpaRepository

interface UserCgmBloodGlucoseJpaRepo: JpaRepository<UserCgmBloodGlucose, Long>
