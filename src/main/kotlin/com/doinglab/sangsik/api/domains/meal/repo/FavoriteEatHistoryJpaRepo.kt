package com.doinglab.sangsik.api.domains.meal.repo

import com.doinglab.sangsik.api.domains.meal.entity.FavoriteEatHistory
import org.springframework.data.jpa.repository.JpaRepository

interface FavoriteEatHistoryJpaRepo: JpaRepository<FavoriteEatHistory, Long>
