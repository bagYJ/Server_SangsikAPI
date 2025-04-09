package com.doinglab.sangsik.api.domains.meal.repo

import com.doinglab.sangsik.api.domains.meal.entity.EatHistoryFood
import org.springframework.data.jpa.repository.JpaRepository

interface EatHistoryFoodJpaRepo: JpaRepository<EatHistoryFood, Long>
