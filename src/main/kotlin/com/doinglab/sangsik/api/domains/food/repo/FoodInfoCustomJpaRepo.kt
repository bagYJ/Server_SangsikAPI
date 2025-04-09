package com.doinglab.sangsik.api.domains.food.repo

import com.doinglab.sangsik.api.domains.food.entity.FoodInfoCustom
import org.springframework.data.jpa.repository.JpaRepository

interface FoodInfoCustomJpaRepo: JpaRepository<FoodInfoCustom, Long>
