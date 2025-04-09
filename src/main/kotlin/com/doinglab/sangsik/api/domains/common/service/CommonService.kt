package com.doinglab.sangsik.api.domains.common.service

import com.doinglab.sangsik.api.domains.common.dto.CustomDto
import com.doinglab.sangsik.enums.StatusCode
import com.doinglab.sangsik.utils.getLogger
import org.springframework.stereotype.Service

@Service
class CommonService {
    private val logger = getLogger()

    fun responseException(message: String?, stack: String, statusCode: StatusCode): CustomDto {
        logger.error(message, stack)

        return CustomDto().apply {
            success = false
            code = statusCode
        }
    }
}
