package com.doinglab.sangsik.api.domains.user.controller

import com.doinglab.sangsik.api.domains.user.dto.response.ResponseGetTermsDto
import com.doinglab.sangsik.api.domains.user.service.TermsService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "terms", description = "약관")
@RequestMapping(value = ["/terms"])
class TermsController(
    private val termsService: TermsService
) {
    @Operation(summary = "약관 링크 리스트")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]")]
    )
    @GetMapping("")
    fun doGetTerms(
        @Parameter(description = "로케일 (ko, en)") @RequestParam(defaultValue = "ko") locale: String? = "ko"
    ): ResponseEntity<ResponseGetTermsDto> =
        ResponseEntity(termsService.doGetTerms(locale), HttpStatus.OK)
}
