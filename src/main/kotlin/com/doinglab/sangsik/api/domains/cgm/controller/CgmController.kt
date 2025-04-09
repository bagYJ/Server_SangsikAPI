package com.doinglab.sangsik.api.domains.cgm.controller

import com.doinglab.sangsik.annotations.Auth
import com.doinglab.sangsik.api.domains.cgm.dto.response.ResponseGetAuthorizeDto
import com.doinglab.sangsik.api.domains.cgm.service.CgmService
import com.doinglab.sangsik.api.domains.common.dto.BaseDto
import com.doinglab.sangsik.api.domains.user.entity.User
import io.swagger.v3.oas.annotations.Hidden
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "cgm", description = "cgm")
@RequestMapping(value = ["/cgm"])
class CgmController(
    private val cgmService: CgmService
) {
    @Operation(summary = "cgm 로그인 uri 전달")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />already.record: 기등록된 사용자")]
    )
    @GetMapping("/authorize")
    fun doGetCgmAuthorize(
        @Auth auth: User.Dto
    ): ResponseEntity<ResponseGetAuthorizeDto> =
        ResponseEntity(cgmService.doGetCgmAuthorize(auth), HttpStatus.OK)

    @Operation(summary = "cgm 연동 해제")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />not.found.user: 사용자 없음" +
                "<br />fail.delete: 삭제 실패")]
    )
    @DeleteMapping("/disconnect")
    fun doDeleteCgmDisconnect(
        @Auth auth: User.Dto
    ): ResponseEntity<BaseDto> =
        ResponseEntity(cgmService.doDeleteCgmDisconnect(auth), HttpStatus.OK)

    @Operation(summary = "cgm 데이터 연동")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />not.found.user: 사용자 없음")]
    )
    @PostMapping("")
    fun doPostCgm(
        @Auth auth: User.Dto
    ): ResponseEntity<BaseDto> =
        ResponseEntity(cgmService.doPostCgm(auth), HttpStatus.OK)

    @Hidden
    @PostMapping("/refresh")
    fun doPostCgmRefresh(): ResponseEntity<Any> =
        ResponseEntity(cgmService.doPostCgmRefresh(), HttpStatus.OK)
}
