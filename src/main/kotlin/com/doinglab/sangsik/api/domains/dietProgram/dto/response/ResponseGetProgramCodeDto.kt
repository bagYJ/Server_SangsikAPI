package com.doinglab.sangsik.api.domains.dietProgram.dto.response

import com.doinglab.sangsik.api.domains.common.dto.BaseDto
import com.doinglab.sangsik.api.domains.dietProgram.entity.DietProgram
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "다이어트 프로그램 조회")
data class ResponseGetProgramCodeDto(
    override val body: GetProgramCodeBodyDto?
): BaseDto() {
    data class GetProgramCodeBodyDto(
        @Schema(description = "코드")
        val code: String,
        @Schema(description = "다이어트 프로그램 아이디")
        val dietProgramId: Long,
        @Schema(description = "썸네일")
        val thumbnail: String?,
        @Schema(description = "타이틀")
        val title: String
    )

    constructor(dietProgram: DietProgram.Dto, code: String): this(GetProgramCodeBodyDto(
        code = code,
        dietProgramId = dietProgram.id,
        thumbnail = if (dietProgram.thumbnail != "") dietProgram.thumbnail else dietProgram.squareThumbnail,
        title = if (dietProgram.description.isEmpty()) {
            dietProgram.title
        } else {
            "${dietProgram.title} (${dietProgram.description})"
        }
    ))
}
