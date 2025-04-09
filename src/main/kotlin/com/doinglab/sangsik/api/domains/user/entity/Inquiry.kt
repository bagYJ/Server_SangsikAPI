package com.doinglab.sangsik.api.domains.user.entity

import com.doinglab.sangsik.enums.InquiryType
import jakarta.persistence.*

@Entity(name = "Inquiry")
class Inquiry(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val userId: Long,
    @Convert(converter = InquiryType.Converter::class)
    val inquiryType: InquiryType,
    val content: String,
    val files: String?
) {
    data class Dto(
        val id: Long,
        val userId: Long,
        val inquiryType: InquiryType,
        val content: String,
        val files: String?
    ) {
        fun toEntity() = Inquiry(id, userId, inquiryType, content, files)
    }

    fun toDto() = Dto(id, userId, inquiryType, content, files)
}
