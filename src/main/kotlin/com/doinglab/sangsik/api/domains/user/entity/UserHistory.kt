package com.doinglab.sangsik.api.domains.user.entity

import com.doinglab.sangsik.enums.UnitWeight
import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDate

@Entity(name = "UserHistory")
@DynamicUpdate
class UserHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "user_id")
    val userId: Long,
    val date: LocalDate,
    @Convert(converter = UnitWeight.Converter::class)
    val unitWeight: UnitWeight,
    val weight: Float,
    val bloodsugar: Float,
    val bloodpressure: Float,
    val cholesterol: Float,
    val targetcalorie: Int,
    val targetWater: Int,
    val diaryWeight: Int,
) {
    data class Dto(
        val id: Long,
        val userId: Long,
        val bloodsugar: Float = 0F,
        val bloodpressure: Float = 0F,
        val cholesterol: Float = 0F,
        val targetcalorie: Int = 0,
        val targetWater: Int = 0,
        val diaryWeight: Int = 0,

        var unitWeight: UnitWeight = UnitWeight.KG,
        var date: LocalDate = LocalDate.now(),
        var weight: Float = 0F
    ) {
        fun toEntity() = UserHistory(id, userId, date, unitWeight, weight, bloodsugar, bloodpressure, cholesterol, targetcalorie, targetWater, diaryWeight)
    }

    fun toDto() = Dto(id, userId, bloodsugar, bloodpressure, cholesterol, targetcalorie, targetWater, diaryWeight, unitWeight, date, weight)
}
