package com.doinglab.sangsik.api.domains.meal.entity

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "EatHistoryComment")
class EatHistoryComment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "eat_history_id")
    val eatHistoryId: Long,
    @Column(name = "partner_company_id")
    val partnerCompanyId: Long,
    @Column(name = "staff_id")
    val staffId: Long,
    val comment: String,
    @Column(name = "reg_date")
    val regDate: LocalDateTime,
    @Column(name = "modify_date")
    val modifyDate: LocalDateTime
) {
    class Dto(
        val id: Long,
        val eatHistoryId: Long,
        val partnerCompanyId: Long,
        val staffId: Long,
        val comment: String,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        val regDate: LocalDateTime,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        val modifyDate: LocalDateTime
    )
}
