package com.doinglab.sangsik.api.domains.user.repo

import com.doinglab.sangsik.api.domains.user.entity.UserBloodSugar
import org.springframework.data.jpa.repository.JpaRepository

interface UserBloodSugarJpaRepo: JpaRepository<UserBloodSugar, Long>
