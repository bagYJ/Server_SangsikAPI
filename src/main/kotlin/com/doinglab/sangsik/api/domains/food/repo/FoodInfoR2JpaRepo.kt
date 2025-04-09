package com.doinglab.sangsik.api.domains.food.repo

import com.doinglab.sangsik.api.domains.food.entity.FoodInfoR2
import org.springframework.data.jpa.repository.JpaRepository

interface FoodInfoR2JpaRepo: JpaRepository<FoodInfoR2, Long>
