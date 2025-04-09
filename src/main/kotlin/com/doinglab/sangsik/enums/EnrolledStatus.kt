package com.doinglab.sangsik.enums

import com.doinglab.sangsik.interfaces.EnumConvert
import jakarta.persistence.AttributeConverter

enum class EnrolledStatus(
    override val value: Int,
    override val desc: String
): EnumConvert {
    WAIT(0, "대기"), INPROGRESS(1, "진행중"), FEEDBACKWAITING(2, "피드백 대기 중"), FEEDBACKPROVIDING(3, "피드백 제공 완료"), DONE(4, "정상완료"), DONEABNORMAL(5, "비정상완료"), CANCELUSER(6, "유저 취소"), DONEPAYMENT(7, "결제완료"), DONERESERVATION(8, "예약완료");

    companion object {
        val active = listOf(INPROGRESS, FEEDBACKWAITING, FEEDBACKPROVIDING, DONEPAYMENT, DONERESERVATION)
        val inactive = listOf(DONE, DONEABNORMAL, CANCELUSER)
    }

    @jakarta.persistence.Converter
    class Converter: AttributeConverter<EnrolledStatus, Int> {
        override fun convertToDatabaseColumn(enrolledStatus: EnrolledStatus?) = enrolledStatus?.value

        override fun convertToEntityAttribute(value: Int?): EnrolledStatus? = entries.firstOrNull { it.value == value }
    }
}
