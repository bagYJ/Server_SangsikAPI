package com.doinglab.sangsik.api.domains.meal.controller

import com.doinglab.sangsik.annotations.Auth
import com.doinglab.sangsik.api.domains.common.dto.CustomDto
import com.doinglab.sangsik.api.domains.meal.dto.request.RequestPostMealDto
import com.doinglab.sangsik.api.domains.meal.dto.response.ResponseGetMealDto
import com.doinglab.sangsik.api.domains.meal.dto.response.ResponseGetMealIdDto
import com.doinglab.sangsik.api.domains.meal.service.MealService
import com.doinglab.sangsik.api.domains.user.entity.User
import com.doinglab.sangsik.enums.Nutrient
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate

@RestController
@Tag(name = "meal", description = "식사")
@RequestMapping(value = ["/meal"])
class MealController(
    private val mealService: MealService
) {
    @Operation(summary = "식사 기록 조회")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "")]
    )
    @GetMapping("")
    fun doGetMeal(
        @Parameter(description = "등록 날짜 (yyyyMMdd)")
        @RequestParam @DateTimeFormat(pattern = "yyyyMMdd") date: LocalDate,
        @RequestParam @Parameter(description = "로케일 (ko, en)") locale: String? = "ko",
        @RequestParam @Parameter(description = "식사기록 메인 서머리 영양소", example = "CARBOHYDRATE,PROTEIN,FAT,TOTALDIETARYFIBER") nutrients: List<Nutrient>?,
        @Auth auth: User.Dto
    ): ResponseEntity<ResponseGetMealDto> =
        ResponseEntity(mealService.doGetMeal(auth, date, locale, nutrients), HttpStatus.OK)

    @Operation(summary = "식사 기록 등록")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />bad.request: 잘못된 요청" +
                "<br />fail.insert: 등록 실패" +
                "<br />not.found.favorite: 즐겨찾기 미존재")]
    )
    @PostMapping("")
    fun doPostMeal(
        @Parameter(schema = Schema(ref = "#/components/schemas/RequestPostMealDto", implementation = RequestPostMealDto::class), description = "Schemas RequestPostMealDto 참조") request: String,
        @RequestPart(value = "file", required = false) file: MultipartFile?,
        @RequestPart(value = "thumbFile", required = false) thumbFile: MultipartFile?,
        @Auth auth: User.Dto
    ): ResponseEntity<CustomDto> =
        ResponseEntity(mealService.doPostMeal(auth.id, request, file, thumbFile), HttpStatus.OK)

    @Operation(summary = "식사 기록 조회(id)")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />not.found.meal: 식사 기록 미존재" +
                "<br />not.found.food: 식사 음식 미존재")]
    )
    @GetMapping("/{id}")
    fun doGetMealId(
        @Parameter(description = "식사 ID") @PathVariable id: Long,
        @Auth auth: User.Dto
    ): ResponseEntity<ResponseGetMealIdDto> =
        ResponseEntity(mealService.doGetMealId(auth.id, id), HttpStatus.OK)

    @Operation(summary = "식사 기록 삭제")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />not.found.meal: 식사 기록 미존재" +
                "<br />fail.delete: 삭제 실패")]
    )
    @DeleteMapping("/{id}")
    fun doDeleteMealId(
        @Parameter(description = "식사 ID") @PathVariable id: Long,
        @Auth auth: User.Dto
    ): ResponseEntity<CustomDto> =
        ResponseEntity(mealService.doDeleteMealId(auth.id, id), HttpStatus.OK)

    @Operation(summary = "식사 기록 수정")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />bad.request: 잘못된 요청" +
                "<br />fail.update: 수정 실패" +
                "<br />not.found.meal: 식사 기록 미존재")]
    )
    @PutMapping("/{id}")
    fun doPutMeal(
        @Parameter(description = "식사 ID") @PathVariable id: Long,
        @RequestBody request: RequestPostMealDto,
        @Auth auth: User.Dto
    ): ResponseEntity<CustomDto> =
        ResponseEntity(mealService.doPutMeal(auth.id, id, request), HttpStatus.OK)
}
