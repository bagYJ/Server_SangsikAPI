package com.doinglab.sangsik.api.domains.meal.repo

import com.doinglab.sangsik.api.domains.meal.entity.RepeatEatHistoryFood
import org.springframework.data.jpa.repository.JpaRepository

interface RepeatEatHistoryFoodJpaRepo: JpaRepository<RepeatEatHistoryFood, Long>
