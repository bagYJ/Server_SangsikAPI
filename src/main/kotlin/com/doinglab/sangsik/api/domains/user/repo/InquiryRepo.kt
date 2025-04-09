package com.doinglab.sangsik.api.domains.user.repo

import com.doinglab.sangsik.api.domains.user.entity.Inquiry
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository

@Repository
class InquiryRepo(
    @Qualifier("dietJpaQueryFactory") private val jpaQueryFactory: JPAQueryFactory,
    private val inquiryJpaRepo: InquiryJpaRepo
) {
    fun saveInquiry(inquiry: Inquiry.Dto): Inquiry.Dto? =
        inquiryJpaRepo.save(inquiry.toEntity()).toDto()
}
