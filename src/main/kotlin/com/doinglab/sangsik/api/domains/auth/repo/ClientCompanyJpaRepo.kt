package com.doinglab.sangsik.api.domains.auth.repo

import com.doinglab.sangsik.api.domains.auth.entity.ClientCompany
import org.springframework.data.jpa.repository.JpaRepository

interface ClientCompanyJpaRepo: JpaRepository<ClientCompany, Long> {
    fun getClientCompanyById(id: Long): ClientCompany?
    fun getClientCompaniesByHttpAuthIDAndHttpAuthPass(authId: String, authPass: String): List<ClientCompany>
    fun getClientCompanyByHttpAuthIDAndHttpAuthPass(authId: String, authPass: String): ClientCompany?
}
