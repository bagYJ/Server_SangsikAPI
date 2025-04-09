package com.doinglab.sangsik.api.domains.meal.repo

import com.doinglab.sangsik.api.domains.meal.entity.MultiPredictPositions
import org.springframework.data.jpa.repository.JpaRepository

interface MultiPredictPositionsJpaRepo: JpaRepository<MultiPredictPositions, Long>
