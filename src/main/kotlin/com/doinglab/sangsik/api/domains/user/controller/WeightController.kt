package com.doinglab.sangsik.api.domains.user.controller

import com.doinglab.sangsik.annotations.Auth
import com.doinglab.sangsik.api.domains.common.dto.BaseDto
import com.doinglab.sangsik.api.domains.user.dto.request.RequestPostWeightDto
import com.doinglab.sangsik.api.domains.user.dto.response.ResponseGetWeightDto
import com.doinglab.sangsik.api.domains.user.entity.User
import com.doinglab.sangsik.api.domains.user.service.WeightService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@Tag(name = "weight", description = "체중")
@RequestMapping(value = ["/weight"])
class WeightController(
    private val weightService: WeightService
) {
    @Operation(summary = "체중 정보 전달")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "")]
    )
    @GetMapping("")
    fun doGetWeight(
        @DateTimeFormat(pattern = "yyyyMMdd") @Parameter(description = "등록 날짜 (yyyyMMdd)") @RequestParam date: LocalDate,
        @Auth auth: User.Dto
    ): ResponseEntity<ResponseGetWeightDto> =
        ResponseEntity(weightService.doGetWeight(auth, date), HttpStatus.OK)

    @Operation(summary = "체중 등록")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "")]
    )
    @PostMapping("")
    fun doPostWeight(
        @RequestBody request: RequestPostWeightDto,
        @Auth auth: User.Dto
    ): ResponseEntity<BaseDto> =
        ResponseEntity(weightService.doPostWeight(auth, request), HttpStatus.OK)

    @Operation(summary = "체중 수정")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "")]
    )
    @PutMapping("/{id}")
    fun doPutWeight(
        @Parameter(description = "체중 ID") @PathVariable id: Long,
        @RequestBody request: RequestPostWeightDto,
        @Auth auth: User.Dto
    ): ResponseEntity<BaseDto> =
        ResponseEntity(weightService.doPutWeight(auth, id, request), HttpStatus.OK)
}
