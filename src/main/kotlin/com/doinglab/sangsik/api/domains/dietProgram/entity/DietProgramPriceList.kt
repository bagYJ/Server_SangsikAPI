package com.doinglab.sangsik.api.domains.dietProgram.entity

import com.doinglab.sangsik.config.converter.NumericBooleanConverter
import com.doinglab.sangsik.enums.PeriodType
import jakarta.persistence.*

@Entity(name = "DietProgramPriceList")
class DietProgramPriceList(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "programID")
    val programId: Long,
    @Convert(converter = PeriodType.Converter::class)
    val periodType: PeriodType,
    val period: Int,
    val originPrice: Int,
    val discountRate: Int,
    val finalPrice: Int,
    @Convert(converter = NumericBooleanConverter::class)
    val isDeleted: Boolean
) {
    data class Dto(
        val id: Long,
        val programId: Long,
        val periodType: PeriodType,
        val period: Int,
        val originPrice: Int,
        val discountRate: Int,
        val finalPrice: Int,
        val isDeleted: Boolean
    )

    fun toDto() = Dto(id, programId, periodType, period, originPrice, discountRate, finalPrice, isDeleted)
}
