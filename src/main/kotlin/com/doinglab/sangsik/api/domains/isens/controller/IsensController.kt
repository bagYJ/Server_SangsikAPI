package com.doinglab.sangsik.api.domains.isens.controller

import io.swagger.v3.oas.annotations.Hidden
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Hidden
@RestController
@Tag(name = "i-sens", description = "i-sens")
@RequestMapping(value = ["/i-sens"])
class IsensController {
    @Operation(summary = "i-sens 인가 코드 받기")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]")]
    )
    @GetMapping("/code")
    fun doGetISensCode(): ResponseEntity<Any> =
        ResponseEntity("", HttpStatus.OK)

    @Operation(summary = "i-sens 토큰 받기")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]")]
    )
    @GetMapping("/token")
    fun doGetISensToken(): ResponseEntity<Any> =
        ResponseEntity("", HttpStatus.OK)
}
