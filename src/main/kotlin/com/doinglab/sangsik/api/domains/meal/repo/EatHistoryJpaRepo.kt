package com.doinglab.sangsik.api.domains.meal.repo

import com.doinglab.sangsik.api.domains.meal.entity.EatHistory
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface EatHistoryJpaRepo: JpaRepository<EatHistory, Long> {
    fun getEatHistoriesByDateBetweenOrderByDateAsc(before: LocalDateTime, after: LocalDateTime): List<EatHistory>?
}
