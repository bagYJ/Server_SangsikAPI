package com.doinglab.sangsik.api.domains.dietProgram.repo

import com.doinglab.sangsik.api.domains.dietProgram.entity.DietProgramEnrolledUser
import org.springframework.data.jpa.repository.JpaRepository

interface DietProgramEnrolledUserJpaRepo: JpaRepository<DietProgramEnrolledUser, Long>
