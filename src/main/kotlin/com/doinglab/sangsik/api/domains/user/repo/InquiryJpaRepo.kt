package com.doinglab.sangsik.api.domains.user.repo

import com.doinglab.sangsik.api.domains.user.entity.Inquiry
import org.springframework.data.jpa.repository.JpaRepository

interface InquiryJpaRepo: JpaRepository<Inquiry, Long>
