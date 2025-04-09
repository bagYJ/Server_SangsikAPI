package com.doinglab.sangsik.api.domains.auth.controller

import com.doinglab.sangsik.api.domains.auth.service.ClientService
import org.springframework.web.bind.annotation.RestController

@RestController
class ClientController(
    private val clientService: ClientService
) {
//    @GetMapping("/client/{clientId}")
//    fun getClient(
//        @Parameter(required = true)
//        @PathVariable clientId: Long,
//        @Auth auth: User.UserDto
//    ): ResponseEntity<ClientCompany.ClientCompanyDto> {
//        return ResponseEntity(clientService.getClient(clientId), HttpStatus.OK)
//    }
}
