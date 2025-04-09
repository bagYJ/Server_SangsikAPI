package com.doinglab.sangsik.api.domains.meal.controller

import com.doinglab.sangsik.annotations.Auth
import com.doinglab.sangsik.api.domains.common.dto.CustomDto
import com.doinglab.sangsik.api.domains.meal.dto.request.RequestPostFavoriteDto
import com.doinglab.sangsik.api.domains.meal.dto.response.ResponseGetFavoriteDto
import com.doinglab.sangsik.api.domains.meal.dto.response.ResponseGetFavoriteIdDto
import com.doinglab.sangsik.api.domains.meal.service.FavoriteService
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
@Tag(name = "favorite", description = "즐겨찾기")
@RequestMapping(value = ["/meal/favorite"])
class FavoriteController(
    private val favoriteService: FavoriteService
) {
    @Operation(summary = "즐겨찾기 리스트")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "")]
    )
    @GetMapping("")
    fun doGetFavorite(
        @Parameter(description = "마지막 즐겨찾기 ID") @RequestParam lastId: Long?,
        @Parameter(description = "페이지당 리스트") @RequestParam(defaultValue = "$PAGE_LIMIT") limit: Long = PAGE_LIMIT,
        @Auth auth: User.Dto
    ): ResponseEntity<ResponseGetFavoriteDto> =
        ResponseEntity(favoriteService.doGetFavorite(auth.id, lastId, limit), HttpStatus.OK)

    @Operation(summary = "즐겨찾기 상세")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />not.found.favorite: 즐겨찾기 미존재")]
    )
    @GetMapping("/{id}")
    fun doGetIdFavorite(
        @Parameter(description = "즐겨찾기 ID") @PathVariable id: Long,
        @Auth auth: User.Dto
    ): ResponseEntity<ResponseGetFavoriteIdDto> =
        ResponseEntity(favoriteService.doGetFavoriteId(auth, id), HttpStatus.OK)

    @Operation(summary = "즐겨찾기 등록")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />already.favorite: 즐겨찾기 기존재" +
                "<br />not.found.meal: 식사 기록 미존재" +
                "<br />not.found.food: 식사 음식 미존재" +
                "<br />fail.insert: 등록 실패")]
    )
    @PostMapping("")
    fun doPostFavorite(
        @RequestBody request: RequestPostFavoriteDto,
        @Auth auth: User.Dto
    ): ResponseEntity<CustomDto> =
        ResponseEntity(favoriteService.doPostFavorite(auth.id, request), HttpStatus.OK)

    @Operation(summary = "즐겨찾기 삭제")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />not.found.favorite: 즐겨찾기 미존재")]
    )
    @DeleteMapping("/{id}")
    fun doDeleteFavoriteId(
        @Parameter(description = "즐겨찾기 ID") @PathVariable id: Long,
        @Auth auth: User.Dto
    ): ResponseEntity<CustomDto> =
        ResponseEntity(favoriteService.doDeleteFavoriteId(auth.id, id), HttpStatus.OK)
}
