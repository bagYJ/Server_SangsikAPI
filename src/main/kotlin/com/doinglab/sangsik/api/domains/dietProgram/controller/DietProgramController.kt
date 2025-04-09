package com.doinglab.sangsik.api.domains.dietProgram.controller

import com.doinglab.sangsik.annotations.Auth
import com.doinglab.sangsik.api.domains.common.dto.CustomDto
import com.doinglab.sangsik.api.domains.dietProgram.dto.request.RequestPostProgramDto
import com.doinglab.sangsik.api.domains.dietProgram.dto.response.ResponseGetProgramCodeDto
import com.doinglab.sangsik.api.domains.dietProgram.dto.response.ResponseGetProgramDto
import com.doinglab.sangsik.api.domains.dietProgram.dto.response.ResponsePostProgramDto
import com.doinglab.sangsik.api.domains.dietProgram.service.DietProgramService
import com.doinglab.sangsik.api.domains.user.entity.User
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "program", description = "다이어트 프로그램")
@RequestMapping(value = ["/program"])
class DietProgramController(
    private val programService: DietProgramService
) {
    @Operation(summary = "다이어트 프로그램 조회 (다이어트 프로그램 등록 가능 조회는 등록에서?)")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />no.match.diet.program.code: 기관코드 미존재" +
                "<br />not.found.diet.program: 다이어트 프로그램 미존재" +
                "<br />not.operated: 미운영" +
                "<br />no.apply.code: 코드로 등록 불가")]
    )
    @GetMapping("/{code}")
    fun doGetProgramCode(
        @Parameter(description = "기관코드") @PathVariable code: String
    ): ResponseEntity<ResponseGetProgramCodeDto> =
        ResponseEntity(programService.doGetProgramCode(code), HttpStatus.OK)

    @Operation(summary = "다이어트 프로그램 등록 (기관 연결)")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />no.match.diet.program.code: 기관코드 미존재" +
                "<br />not.found.diet.program: 다이어트 프로그램 미존재" +
                "<br />under.14.years: 만14세 이상 가입 가능" +
                "<br />not.operated: 미운영" +
                "<br />no.apply.code: 코드로 등록 불가" +
                "<br />already.enrolled.program: 다이어트 프로그램 기등록" +
                "<br />cannot.regist.program: 프로그램 등록 불가 (과거 동일 프로그램 등록)" +
                "<br />fail.insert: 등록 실패")]
    )
    @PostMapping("")
    fun doPostProgram(
        @RequestBody request: RequestPostProgramDto,
        @Auth auth: User.Dto
    ): ResponseEntity<ResponsePostProgramDto> =
        ResponseEntity(programService.doPostProgram(auth, request), HttpStatus.OK)

    @Operation(summary = "기관 연결 해제")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />not.found.diet.program: 다이어트 프로그램 미존재" +
                "<br />fail.update: 수정 실패")]
    )
    @DeleteMapping("/{programId}")
    fun doDeleteProgram(
        @Parameter(description = "프로그램 진행 ID (다이어트 프로그램 조회시 id값)") @PathVariable programId: Long,
        @Auth auth: User.Dto
    ): ResponseEntity<CustomDto> =
        ResponseEntity(programService.doDeleteProgram(auth, programId), HttpStatus.OK)

    @Operation(summary = "등록한 다이어트 프로그램 조회")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "")]
    )
    @GetMapping("")
    fun doGetProgram(
        @Auth auth: User.Dto
    ): ResponseEntity<ResponseGetProgramDto?> =
        ResponseEntity(programService.doGetProgram(auth.id), HttpStatus.OK)
}
