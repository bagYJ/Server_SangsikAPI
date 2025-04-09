package com.doinglab.sangsik.api.domains.user.controller

import com.doinglab.sangsik.annotations.Auth
import com.doinglab.sangsik.api.domains.common.dto.CustomDto
import com.doinglab.sangsik.api.domains.user.entity.User
import com.doinglab.sangsik.api.domains.user.service.InquiryService
import com.doinglab.sangsik.enums.InquiryType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@Tag(name = "inquiry", description = "1:1문의")
@RequestMapping(value = ["/inquiry"])
class InquiryController(
    private val inquiryService: InquiryService
) {
    @Operation(summary = "1:1문의 등록")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "[code]<br />fail.insert: 등록 실패" +
                "<br />check.over.file: 파일 등록 제한 초과")]
    )
    @PostMapping("", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun doPostInquiry(
        @Parameter(description = "문의유형 (APP: 앱 개선 요청, FOOD: 음식 데이터 추가 요청, SERVICE: 서비스 제안, BUSINESS: 사업 제안, ETC: 기타)") @RequestParam inqueryType: InquiryType,
        @Parameter(description = "문의 내용") @RequestParam content: String,
        @Parameter(description = "이미지") @RequestParam file: List<MultipartFile>?,
        @Auth auth: User.Dto
    ): ResponseEntity<CustomDto> =
        ResponseEntity(inquiryService.doPostInquiry(auth, inqueryType, content, file), HttpStatus.OK)
}
