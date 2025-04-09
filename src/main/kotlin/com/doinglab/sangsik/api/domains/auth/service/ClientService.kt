package com.doinglab.sangsik.api.domains.auth.service

import com.doinglab.sangsik.api.domains.auth.entity.ClientCompany
import com.doinglab.sangsik.api.domains.auth.repo.ClientCompanyRepo
import org.springframework.transaction.annotation.Transactional
import org.springframework.stereotype.Service

@Service
class ClientService(
    private val clientCompanyRepo: ClientCompanyRepo
) {
    @Transactional
    fun getClient(id: Long): ClientCompany.Dto? = clientCompanyRepo
        .getClientCompanyById(id)
        ?.toDto()
}
