package com.doinglab.sangsik.api.domains.staff.repo

import com.doinglab.sangsik.api.domains.staff.entity.PartnerCompany
import com.doinglab.sangsik.api.domains.staff.entity.Staff
import com.doinglab.sangsik.define.CoreDefine.Companion.PARTNER_COMPANY
import com.doinglab.sangsik.define.CoreDefine.Companion.STAFF
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository

@Repository
class StaffRepo(
    @Qualifier("dietJpaQueryFactory") private val jpaQueryFactory: JPAQueryFactory
) {
    fun findStaff(staffId: Long): Staff.Dto? =
        jpaQueryFactory.selectFrom(STAFF).where(STAFF.id.eq(staffId)).fetchOne()?.toDto()

    fun findStaff(staffIds: List<Long>): List<Staff.Dto>? =
        jpaQueryFactory.selectFrom(STAFF)
            .where(STAFF.id.`in`(staffIds))
            .fetch().map { it.toDto() }

    fun findPartnerCompany(partnerCompanyId: Long): PartnerCompany.Dto? =
        jpaQueryFactory.selectFrom(PARTNER_COMPANY).where(PARTNER_COMPANY.id.eq(partnerCompanyId)).fetchOne()?.toDto()

    fun getPartnerCompanyByCode(code: String): PartnerCompany.Dto? =
        jpaQueryFactory.selectFrom(PARTNER_COMPANY).where(PARTNER_COMPANY.code.eq(code)).fetchOne()?.toDto()
}
