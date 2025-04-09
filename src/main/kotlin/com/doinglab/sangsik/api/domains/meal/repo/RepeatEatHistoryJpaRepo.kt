package com.doinglab.sangsik.api.domains.meal.repo

import com.doinglab.sangsik.api.domains.meal.entity.RepeatEatHistory
import org.springframework.data.jpa.repository.JpaRepository

interface RepeatEatHistoryJpaRepo: JpaRepository<RepeatEatHistory, Long>
