package com.doinglab.sangsik.api.domains.user.repo

import com.doinglab.sangsik.api.domains.user.entity.UserBloodGlucose
import org.springframework.data.jpa.repository.JpaRepository

interface UserBloodGlucoseJpaRepo: JpaRepository<UserBloodGlucose, Long>
