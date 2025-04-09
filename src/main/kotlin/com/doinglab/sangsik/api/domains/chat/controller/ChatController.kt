package com.doinglab.sangsik.api.domains.chat.controller

import com.doinglab.sangsik.annotations.Auth
import com.doinglab.sangsik.api.domains.chat.dto.response.ResponseGetChatRoomIdDto
import com.doinglab.sangsik.api.domains.chat.dto.response.ResponseGetChatUnreadDto
import com.doinglab.sangsik.api.domains.chat.dto.response.ResponsePostChatRoomIdDto
import com.doinglab.sangsik.api.domains.chat.service.ChatService
import com.doinglab.sangsik.api.domains.user.entity.User
import com.doinglab.sangsik.enums.MessageType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@Tag(name = "chat", description = "채팅")
@RequestMapping(value = ["/chat"])
class ChatController(
    private val chatService: ChatService
) {
    @Operation(summary = "읽지 않은 마지막 채팅 메시지 정보 전달")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]")]
    )
    @GetMapping("/unread")
    fun doGetChatUnread(
        @Auth auth: User.Dto
    ): ResponseEntity<ResponseGetChatUnreadDto> =
        ResponseEntity(chatService.doGetChatUnread(auth.id), HttpStatus.OK)

    @Operation(summary = "채팅 리스트 전달 (최신 메시지(lastId가 있을 경우 lastId)로부터 20개)")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />not.found.chat.room: 채팅방 미존재")]
    )
    @GetMapping("/room/{roomId}")
    fun doGetChatRoomId(
        @Parameter(description = "마지막 채팅 ID")
        @RequestParam lastId: Long?,
        @Parameter(description = "채팅방 ID") @PathVariable roomId: Long,
        @Auth auth: User.Dto
    ): ResponseEntity<ResponseGetChatRoomIdDto> =
        ResponseEntity(chatService.doGetChatRoomId(auth.id, roomId, lastId), HttpStatus.OK)

    @Operation(summary = "채팅 메시지 전송")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />not.found.chat.room: 채팅방 미존재" +
                "<br />bad.request: 잘못된 요청" +
                "<br />fail.insert: 등록 실패")]
    )
    @PostMapping("/room/{roomId}", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun doPostChatRoomId(
        @Parameter(description = "채팅방 ID") @PathVariable roomId: Long,
        @Parameter(description = "메시지 타입 (TEXT: 텍스트, IMAGE: 이미지)") @RequestParam messageType: MessageType,
        @Parameter(description = "TEXT일 경우 메시지") @RequestParam message: String?,
        @Parameter(description = "IMAGE일 경우 파일") @RequestParam file: MultipartFile?,
        @Auth auth: User.Dto
    ): ResponseEntity<ResponsePostChatRoomIdDto> =
        ResponseEntity(chatService.doPostChatRoomId(auth.id, roomId, messageType, message, file), HttpStatus.OK)
}
