package com.doinglab.sangsik.api.domains.user.dto.response

import com.doinglab.sangsik.api.domains.common.dto.BaseDto
import com.doinglab.sangsik.api.domains.user.entity.User
import com.doinglab.sangsik.api.domains.user.entity.UserHistory
import com.doinglab.sangsik.utils.weight
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

@Schema(description = "체중 정보")
data class ResponseGetWeightDto(
    override val body: GetWeightBody?
): BaseDto() {
    data class GetWeightBody(
        @Schema(description = "체중 ID")
        var id: Long? = null,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        @Schema(description = "체중 입력 날짜")
        val date: LocalDate,
        @Schema(description = "체중")
        var weight: Float = -1.0F,
        @Schema(description = "체중 그래프")
        val graph: List<Graph>
    ) {
        data class Graph(
            @Schema(description = "체중 ID")
            var id: Long? = null,
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
            @Schema(description = "체중 입력 날짜")
            val date: LocalDate,
            @Schema(description = "체중")
            var weight: Float = -1.0F,
        )
    }

    constructor(
        user: User.Dto,
        dates: List<LocalDate>,
        userHistories: List<UserHistory.Dto>?,
        firstWeight: Float
    ) : this(
        GetWeightBody(
            id = userHistories?.firstOrNull { dates.last().isEqual(it.date) }?.id,
            date = dates.last(),
            weight = userHistories?.filter { dates.last() >= it.date }?.maxByOrNull { it.date }?.let {
                it.weight.weight(it.unitWeight, user.unitWeight)
            } ?: firstWeight,
            graph = dates.map { dt ->
                GetWeightBody.Graph(
                    id = userHistories?.firstOrNull { dt.isEqual(it.date) }?.id,
                    date = dt,
                    weight = userHistories?.firstOrNull { dt.isEqual(it.date) }?.let {
                        it.weight.weight(it.unitWeight, user.unitWeight)
                    } ?: when {
                        dt.isAfter(LocalDate.now()) -> 0F
                        userHistories?.filter { dt >= it.date }?.maxByOrNull {it.date } != null -> userHistories.filter { dt >= it.date }.maxBy { it.date }.let {
                                it.weight.weight(it.unitWeight, user.unitWeight)
                            }
                        firstWeight > 0 && dt <= LocalDate.now() -> firstWeight
                        else -> 0F
                    }
                )
            }
        ))
}
