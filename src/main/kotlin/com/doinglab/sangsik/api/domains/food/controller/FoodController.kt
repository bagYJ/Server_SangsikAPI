package com.doinglab.sangsik.api.domains.food.controller

import com.doinglab.sangsik.annotations.Auth
import com.doinglab.sangsik.api.domains.common.dto.CustomDto
import com.doinglab.sangsik.api.domains.food.dto.request.RequestPostFoodCustomDto
import com.doinglab.sangsik.api.domains.food.dto.response.ResponseGetFoodAutocompleteDto
import com.doinglab.sangsik.api.domains.food.dto.response.ResponseGetFoodCustomDto
import com.doinglab.sangsik.api.domains.food.dto.response.ResponseGetFoodCustomIdDto
import com.doinglab.sangsik.api.domains.food.dto.response.ResponseGetFoodIdDto
import com.doinglab.sangsik.api.domains.food.service.FoodService
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
@Tag(name = "food", description = "음식")
@RequestMapping(value = ["/food"])
class FoodController(
    private val foodService: FoodService
) {
    @Operation(summary = "음식 이름 자동완성")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]")]
    )
    @GetMapping("/autocomplete")
    fun doGetFoodAutocomplete(
        @Parameter(description = "음식이름") @RequestParam key: String,
        @Parameter(description = "검색 개수") @RequestParam(defaultValue = "50") count: Int = 50,
    ): ResponseEntity<ResponseGetFoodAutocompleteDto> =
        ResponseEntity(foodService.doGetFoodAutocomplete(key, count), HttpStatus.OK)

    @Operation(summary = "음식 정보 조회")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />not.found.food: 식사 음식 미존재")]
    )
    @GetMapping("/{id}")
    fun doGetFoodId(
        @Parameter(description = "음식 ID") @PathVariable id: Long,
        @Parameter(description = "언어 로케일") @RequestParam(defaultValue = "ko") locale: String = "ko",
    ): ResponseEntity<ResponseGetFoodIdDto> =
        ResponseEntity(foodService.doGetFoodId(id, locale), HttpStatus.OK)

    @Operation(summary = "내음식 리스트")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]")]
    )
    @GetMapping("/custom")
    fun doGetFoodCustom(
        @Auth auth: User.Dto
    ): ResponseEntity<ResponseGetFoodCustomDto> =
        ResponseEntity(foodService.doGetFoodCustom(auth.id), HttpStatus.OK)

    @Operation(summary = "내음식 상세")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]")]
    )
    @GetMapping("/custom/{id}")
    fun doGetFoodCustomId(
        @Parameter(description = "음식 ID") @PathVariable id: Long,
        @Auth auth: User.Dto
    ): ResponseEntity<ResponseGetFoodCustomIdDto> =
        ResponseEntity(foodService.doGetFoodCustomId(auth.id, id), HttpStatus.OK)

    @Operation(summary = "내음식 등록")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />fail.insert: 등록 실패")]
    )
    @PostMapping("/custom")
    fun doPostFoodCustom(
        @RequestBody request: RequestPostFoodCustomDto,
        @Auth auth: User.Dto
    ): ResponseEntity<CustomDto> =
        ResponseEntity(foodService.doPostFoodCustom(auth.id, request), HttpStatus.CREATED)

    @Operation(summary = "내음식 수정")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />not.found.food: 식사 음식 미존재" +
                "<br />fail.update: 수정 실패")]
    )
    @PutMapping("/custom/{id}")
    fun doPutFoodCustomId(
        @Parameter(description = "음식 ID") @PathVariable id: Long,
        @RequestBody request: RequestPostFoodCustomDto,
        @Auth auth: User.Dto
    ): ResponseEntity<CustomDto> =
        ResponseEntity(foodService.doPutFoodCustomId(auth.id, id, request), HttpStatus.OK)

    @Operation(summary = "내음식 삭제")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />not.found.food: 식사 음식 미존재" +
                "<br />fail.delete: 삭제 실패")]
    )
    @DeleteMapping("/custom/{id}")
    fun doDeleteFoodCustomId(
        @Parameter(description = "음식 ID") @PathVariable id: Long,
        @Auth auth: User.Dto
    ): ResponseEntity<CustomDto> =
        ResponseEntity(foodService.doDeleteFoodCustomId(auth.id, id), HttpStatus.OK)
}
