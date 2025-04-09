package com.doinglab.sangsik.api.domains.meal.controller

import com.doinglab.sangsik.annotations.Auth
import com.doinglab.sangsik.api.domains.common.dto.CustomDto
import com.doinglab.sangsik.api.domains.meal.dto.request.RequestPostRepeatDto
import com.doinglab.sangsik.api.domains.meal.dto.response.ResponseGetRepeatDto
import com.doinglab.sangsik.api.domains.meal.dto.response.ResponseGetRepeatIdDto
import com.doinglab.sangsik.api.domains.meal.service.RepeatService
import com.doinglab.sangsik.api.domains.user.entity.User
import com.doinglab.sangsik.define.CoreDefine.Companion.PAGE_LIMIT
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Tag(name = "repeat", description = "반복기록")
@RequestMapping(value = ["/meal/repeat"])
class RepeatController(
    private val repeatService: RepeatService
) {
    @Operation(summary = "반복기록 리스트")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "")]
    )
    @GetMapping("")
    fun doGetRepeat(
        @Parameter(description = "마지막 반복기록 ID") @RequestParam lastId: Long?,
        @Parameter(description = "페이지당 리스트") @RequestParam(defaultValue = "$PAGE_LIMIT") limit: Long = PAGE_LIMIT,
        @Auth auth: User.Dto
    ): ResponseEntity<ResponseGetRepeatDto> =
        ResponseEntity(repeatService.doGetRepeat(auth.id, lastId, limit), HttpStatus.OK)

    @Operation(summary = "반복기록 상세")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />not.found.repeat: 반복기록 미존재")]
    )
    @GetMapping("/{id}")
    fun doGetIdRepeat(
        @Parameter(description = "반복기록 ID") @PathVariable id: Long,
        @Auth auth: User.Dto
    ): ResponseEntity<ResponseGetRepeatIdDto> =
        ResponseEntity(repeatService.doGetRepeatId(auth, id), HttpStatus.OK)

    @Operation(summary = "반복기록 등록")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />already.repeat: 반복기록 기존재" +
                "<br />not.found.meal: 식사 기록 미존재" +
                "<br />not.found.food: 식사 음식 미존재" +
                "<br />fail.insert: 등록 실패")]
    )
    @PostMapping("")
    fun doPostRepeat(
        @RequestBody request: RequestPostRepeatDto,
        @Auth auth: User.Dto
    ): ResponseEntity<CustomDto> =
        ResponseEntity(repeatService.doPostRepeat(auth.id, request), HttpStatus.OK)

    @Operation(summary = "반복기록 삭제")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />not.found.repeat: 반복기록 미존재")]
    )
    @DeleteMapping("/{id}")
    fun doDeleteRepeatId(
        @Parameter(description = "반복기록 ID") @PathVariable id: Long,
        @Auth auth: User.Dto
    ): ResponseEntity<CustomDto> =
        ResponseEntity(repeatService.doDeleteRepeatId(auth.id, id), HttpStatus.OK)
}
