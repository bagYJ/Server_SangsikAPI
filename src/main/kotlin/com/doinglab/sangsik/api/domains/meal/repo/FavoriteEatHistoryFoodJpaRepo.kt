package com.doinglab.sangsik.api.domains.meal.repo

import com.doinglab.sangsik.api.domains.meal.entity.FavoriteEatHistoryFood
import org.springframework.data.jpa.repository.JpaRepository

interface FavoriteEatHistoryFoodJpaRepo: JpaRepository<FavoriteEatHistoryFood, Long>
