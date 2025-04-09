package com.doinglab.sangsik.api.domains.announcement.controller

import com.doinglab.sangsik.annotations.Auth
import com.doinglab.sangsik.annotations.RequireNotAuth
import com.doinglab.sangsik.api.domains.announcement.dto.response.GetAnnouncementUnreadDto
import com.doinglab.sangsik.api.domains.announcement.dto.response.ResponseGetAnnouncementDto
import com.doinglab.sangsik.api.domains.announcement.dto.response.ResponseGetAnnouncementIdDto
import com.doinglab.sangsik.api.domains.announcement.service.AnnouncementService
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
@Tag(name = "announcement", description = "공지사항")
@RequestMapping(value = ["/announcement"])
class AnnouncementController(
    private val announcementService: AnnouncementService
) {
    @Operation(summary = "공지사항 조회")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]")]
    )
    @GetMapping("")
    fun doGetAnnouncement(
        @Parameter(description = "공지사항 ID (최신 메시지(lastId가 있을 경우 lastId)로부터 20개)") @RequestParam lastId: Long?,
        @Parameter(description = "언어 로케일 (ko, en)") @RequestParam locale: String?,
        @RequireNotAuth @Auth auth: User.Dto?
    ): ResponseEntity<ResponseGetAnnouncementDto> =
        ResponseEntity(announcementService.doGetAnnouncement(auth?.id, lastId, locale), HttpStatus.OK)

    @Operation(summary = "공지사항 상세 조회")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />not.found.announcement: 공지사항 미존재" +
                "<br />fail.insert: 읽음처리 실패")]
    )
    @GetMapping("/{id}")
    fun doGetAnnouncementId(
        @Parameter(description = "공지사항 ID") @PathVariable id: Long,
        @RequireNotAuth @Auth auth: User.Dto?
    ): ResponseEntity<ResponseGetAnnouncementIdDto> =
        ResponseEntity(announcementService.doGetAnnouncementId(auth?.id, id), HttpStatus.OK)

    @Operation(summary = "읽지 않은 공지사항 수 조회")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]")]
    )
    @GetMapping("/unread")
    fun doGetAnnouncementUnread(
        @Parameter(description = "언어 로케일 (ko, en)") @RequestParam locale: String?,
        @RequireNotAuth @Auth auth: User.Dto?
    ): ResponseEntity<GetAnnouncementUnreadDto> =
        ResponseEntity(announcementService.doGetAnnouncementUnread(auth?.id, locale), HttpStatus.OK)
}
