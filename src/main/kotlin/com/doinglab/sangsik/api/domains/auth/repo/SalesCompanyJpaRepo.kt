package com.doinglab.sangsik.api.domains.auth.repo

import com.doinglab.sangsik.api.domains.auth.entity.SalesCompany
import org.springframework.data.jpa.repository.JpaRepository

interface SalesCompanyJpaRepo: JpaRepository<SalesCompany, Long>
