package com.doinglab.sangsik.api.domains.meal.repo

import com.doinglab.sangsik.api.domains.meal.entity.RepeatLink
import org.springframework.data.jpa.repository.JpaRepository

interface RepeatLinkJpaRepo: JpaRepository<RepeatLink, Long>
