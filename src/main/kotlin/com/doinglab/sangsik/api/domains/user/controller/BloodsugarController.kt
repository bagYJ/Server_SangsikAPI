package com.doinglab.sangsik.api.domains.user.controller

import com.doinglab.sangsik.annotations.Auth
import com.doinglab.sangsik.api.domains.common.dto.CustomDto
import com.doinglab.sangsik.api.domains.user.dto.request.RequestPostBloodsugarDto
import com.doinglab.sangsik.api.domains.user.dto.response.ResponseGetBloodsugarDto
import com.doinglab.sangsik.api.domains.user.entity.User
import com.doinglab.sangsik.api.domains.user.service.BloodsugarService
import com.doinglab.sangsik.enums.MealTime
import com.doinglab.sangsik.enums.MealType
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
@Tag(name = "bloodsugar", description = "혈당")
@RequestMapping(value = ["/bloodsugar"])
class BloodsugarController(
    private val bloodsugarService: BloodsugarService
) {
    @Operation(summary = "혈당 정보 조회")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "")]
    )
    @GetMapping("")
    fun doGetBloodsugar(
        @DateTimeFormat(pattern = "yyyyMMdd") @Parameter(description = "등록 날짜 (yyyyMMdd)") @RequestParam date: LocalDate,
        @Parameter(description = "혈당 입력 타입 (MORNING: 아침, AFTERNOON: 점심, DINNER: 저녁, BEFOREBED: 취침전, MORNINGSNACK: 오전간식, AFTERNOONSNACK: 오후간식, NIGHTSNACK: 야식)", required = false) @RequestParam mealType: MealType?,
        @Parameter(description = "혈당 입력 시간 타입 (BEFORE: 식전, AFTER_1: 식후 1시간, AFTER: 식후 2시간)", required = false) @RequestParam mealtime: MealTime?,
        @Auth auth: User.Dto
    ): ResponseEntity<ResponseGetBloodsugarDto> =
        ResponseEntity(bloodsugarService.doGetBloodsugar(auth, date, mealType, mealtime), HttpStatus.OK)

    @Operation(summary = "혈당 정보 입력")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />date.greater.then.now: 오늘 이후 등록 불가" +
                "<br />less.then.1: 혈당 최소값 체크" +
                "<br />greater.then.999: 혈당 최대값 체크" +
                "<br />fail.insert: 등록 실패" +
                "<br />fail.update: 수정 실패")]
    )
    @PostMapping("")
    fun doPostBloodsugar(
        @RequestBody request: RequestPostBloodsugarDto,
        @Auth auth: User.Dto
    ): ResponseEntity<CustomDto> =
        ResponseEntity(bloodsugarService.doPostBloodsugar(auth, request), HttpStatus.OK)

    @Operation(summary = "혈당 정보 수정")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />date.greater.then.now: 오늘 이후 등록 불가" +
                "<br />less.then.1: 혈당 최소값 체크" +
                "<br />greater.then.999: 혈당 최대값 체크" +
                "<br />not.found.record: 혈당 기록 미존재" +
                "<br />fail.update: 수정 실패")]
    )
    @PutMapping("/{id}")
    fun doPutBloodsugar(
        @Parameter(description = "혈당 ID") @PathVariable id: Long,
        @RequestBody request: RequestPostBloodsugarDto,
        @Auth auth: User.Dto
    ): ResponseEntity<CustomDto> =
        ResponseEntity(bloodsugarService.doPutBloodsugar(auth, id, request), HttpStatus.OK)

    @Operation(summary = "혈당 정보 삭제")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />not.found.record: 혈당 기록 미존재" +
                "<br />fail.delete: 삭제 실패")]
    )
    @DeleteMapping("/{id}")
    fun doDeleteBloodsugar(
        @Parameter(description = "혈당 ID") @PathVariable id: Long,
        @Auth auth: User.Dto
    ): ResponseEntity<CustomDto> =
        ResponseEntity(bloodsugarService.doDeleteBloodsugar(auth.id, id), HttpStatus.OK)
}
