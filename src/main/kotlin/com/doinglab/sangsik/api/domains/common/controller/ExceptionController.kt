package com.doinglab.sangsik.api.domains.common.controller

import com.doinglab.sangsik.Exception.CustomException
import com.doinglab.sangsik.api.domains.common.dto.CustomDto
import com.doinglab.sangsik.api.domains.common.service.CommonService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionController(
    private val commonService: CommonService
) {
    @ExceptionHandler(CustomException::class)
    fun customException(e: CustomException): ResponseEntity<CustomDto> {
        return ResponseEntity(commonService.responseException(e.message, e.stackTraceToString(), e.statusCode), HttpStatus.OK)
    }
}
