package com.doinglab.sangsik.api.domains.common.controller

import io.swagger.v3.oas.annotations.Hidden
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CommonController {
    @Hidden
    @RequestMapping
    fun index(): ResponseEntity<String> =
        ResponseEntity("", HttpStatus.OK)

    @Hidden
    @RequestMapping("/favicon.ico")
    fun favicon(): ResponseEntity<String> =
        ResponseEntity("", HttpStatus.OK)
}
