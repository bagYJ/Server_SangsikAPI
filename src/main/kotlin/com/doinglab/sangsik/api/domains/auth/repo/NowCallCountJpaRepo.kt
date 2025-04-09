package com.doinglab.sangsik.api.domains.auth.repo

import com.doinglab.sangsik.api.domains.auth.entity.NowCallCount
import org.springframework.data.jpa.repository.JpaRepository

interface NowCallCountJpaRepo: JpaRepository<NowCallCount, Long> {
    fun getNowCallCountByIdAndUri(id: Long, uri: String): NowCallCount
}
