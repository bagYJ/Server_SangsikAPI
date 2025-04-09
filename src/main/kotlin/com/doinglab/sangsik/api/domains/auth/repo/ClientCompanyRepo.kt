package com.doinglab.sangsik.api.domains.auth.repo

import com.doinglab.sangsik.api.domains.auth.entity.ClientCompany
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Repository

@Repository
class ClientCompanyRepo(
    @Qualifier("authJpaQueryFactory") private val jpaQueryFactory: JPAQueryFactory,
    private val clientCompanyJpaRepo: ClientCompanyJpaRepo
): ClientCompanyJpaRepo by clientCompanyJpaRepo {

    @Cacheable(key = "#authId", value = ["sangsikCompany"], unless = "#result == null")
    fun findClientCompany(authId: String, authPass: String): ClientCompany.Dto? =
        clientCompanyJpaRepo.getClientCompanyByHttpAuthIDAndHttpAuthPass(authId, authPass)?.toDto()

    @Cacheable(key = "#companyId", value = ["partnerCompany"])
    fun findClientCompany(companyId: Long): ClientCompany.Dto? =
        clientCompanyJpaRepo.getClientCompanyById(companyId)?.toDto()
}
