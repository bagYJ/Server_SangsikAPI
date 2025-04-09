package com.doinglab.sangsik.api.domains.food.repo

import com.doinglab.sangsik.api.domains.food.entity.FoodInfo
import org.springframework.data.jpa.repository.JpaRepository

interface FoodInfoJpaRepo: JpaRepository<FoodInfo, Long>
