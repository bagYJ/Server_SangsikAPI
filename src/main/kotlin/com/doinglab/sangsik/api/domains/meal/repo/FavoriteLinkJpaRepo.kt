package com.doinglab.sangsik.api.domains.meal.repo

import com.doinglab.sangsik.api.domains.meal.entity.FavoriteLink
import org.springframework.data.jpa.repository.JpaRepository

interface FavoriteLinkJpaRepo: JpaRepository<FavoriteLink, Long>
