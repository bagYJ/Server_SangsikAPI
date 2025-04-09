package com.doinglab.sangsik.api.domains.dietProgram.dto.response

import com.doinglab.sangsik.api.domains.common.dto.BaseDto
import com.doinglab.sangsik.api.domains.dietProgram.entity.DietProgram
import com.doinglab.sangsik.api.domains.dietProgram.entity.DietProgramEnrolledUser
import com.doinglab.sangsik.api.domains.staff.entity.Staff
import com.doinglab.sangsik.enums.DietProgramOption
import com.doinglab.sangsik.enums.EnrolledStatus
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

data class ResponseGetProgramDto(
    override val body: List<GetProgramBody>
): BaseDto() {
    data class GetProgramBody(
        @Schema(description = "프로그램 진행 ID")
        val id: Long,
        @Schema(description = "프로그램 ID")
        val programId: Long,
        @Schema(description = "상담사 ID")
        val staffId: Long,
        @Schema(description = "채팅방 ID")
        val roomId: Long,
        @Schema(description = "채팅 가능 여부")
        @Deprecated("")
        val chatBlocked: Boolean,
        @Schema(description = "채팅 가능 여부")
        val canChat: Boolean,
        @Schema(description = "프로그램 진행 시작일")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        val startDate: LocalDateTime,
        @Schema(description = "프로그램 진행 종료일")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        val endDate: LocalDateTime,
        @Schema(description = "프로그램 진행 상태(WAIT: 대기, INPROGRESS: 진행중, FEEDBACKWAITING: 피드백 대기 중, FEEDBACKPROVIDING: 피드백 제공 완료, DONE: 정상완료, DONEABNORMAL: 비정상완료, CANCELUSER: 유저 취소, DONEPAYMENT: 결제완료, DONERESERVATION: 예약완료)")
        val status: EnrolledStatus,
        @Schema(description = "프로그램 진행 신청일")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        val enrollDate: LocalDateTime,
//        val appointment: Boolean,
//        val appointmentDate: LocalDateTime,
        @Schema(description = "프로그램명")
        val programTitle: String,
        @Schema(description = "프로그램 썸네일")
        val programSquareThumbnail: String?,
        @Schema(description = "상담사명")
        val staffName: String,
        @Schema(description = "상담사 썸네일")
        val staffPhotoUrl: String?
    )

    constructor(
        dietProgramEnrolleds: List<DietProgramEnrolledUser.Dto>,
        dietPrograms: List<DietProgram.Dto>?,
        staffs: List<Staff.Dto>?
    ): this(dietProgramEnrolleds.map { dietProgramEnrolled ->
        dietPrograms!!.first { it.id == dietProgramEnrolled.programId }.let { dietProgram ->
            GetProgramBody(
                id = dietProgramEnrolled.id,
                programId = dietProgramEnrolled.programId,
                staffId = dietProgramEnrolled.staffId,
                roomId = dietProgramEnrolled.roomId,
                chatBlocked = false,
                canChat = dietProgramEnrolled.status in EnrolledStatus.active && (dietProgramEnrolled.useChatting ?: dietProgram.options.first { it.name == DietProgramOption.CHATTING }.isUse),
                startDate = dietProgramEnrolled.startDate,
                endDate = dietProgramEnrolled.finishDate,
                status = dietProgramEnrolled.status,
                enrollDate = dietProgramEnrolled.enrollDate,
                programTitle = dietProgram.title,
                programSquareThumbnail = if (dietProgram.thumbnail != "") dietProgram.thumbnail else dietProgram.squareThumbnail,
                staffName = staffs!!.first { it.id == dietProgramEnrolled.staffId }.name,
                staffPhotoUrl = staffs.first { it.id == dietProgramEnrolled.staffId }.imageURL
            )
        }
    })
}
