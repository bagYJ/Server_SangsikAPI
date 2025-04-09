package com.doinglab.sangsik.api.domains.user.entity

import com.doinglab.sangsik.api.domains.user.dto.request.RequestPostUserAgreementDto
import com.doinglab.sangsik.api.domains.user.dto.request.RequestPostUserDto
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity(name = "UserAgreement")
class UserAgreement(
    @Id
    val userId: Long,
    val essentialTermsOfService: LocalDateTime?,
    val essentialPaidServiceTerms: LocalDateTime?,
    val essentialPersonalInformation: LocalDateTime?,
    val essentialCollectionPersonalInformation: LocalDateTime?,
    val essentialSensitiveInformation: LocalDateTime?,
    val essentialOverAge14: LocalDateTime?,
    val selectablePersonalInformation: LocalDateTime?,
    val selectablePersonalMarketing: LocalDateTime?,
    val selectableEmailMarketing: LocalDateTime?
) {

    data class Dto(
        val userId: Long,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        var essentialTermsOfService: LocalDateTime? = LocalDateTime.of(1970, 1, 1, 0, 0),
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        var essentialPaidServiceTerms: LocalDateTime? = LocalDateTime.of(1970, 1, 1, 0, 0),
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        var essentialPersonalInformation: LocalDateTime? = LocalDateTime.of(1970, 1, 1, 0, 0),
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        var essentialCollectionPersonalInformation: LocalDateTime? = LocalDateTime.of(1970, 1, 1, 0, 0),
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        var essentialSensitiveInformation: LocalDateTime? = LocalDateTime.of(1970, 1, 1, 0, 0),
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        var essentialOverAge14: LocalDateTime? = LocalDateTime.of(1970, 1, 1, 0, 0),
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        var selectablePersonalInformation: LocalDateTime? = LocalDateTime.of(1970, 1, 1, 0, 0),
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        var selectablePersonalMarketing: LocalDateTime? = LocalDateTime.of(1970, 1, 1, 0, 0),
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        var selectableEmailMarketing: LocalDateTime? = LocalDateTime.of(1970, 1, 1, 0, 0)
    ) {
        fun toEntity() =
            UserAgreement(userId, essentialTermsOfService, essentialPaidServiceTerms, essentialPersonalInformation, essentialCollectionPersonalInformation, essentialSensitiveInformation, essentialOverAge14, selectablePersonalInformation, selectablePersonalMarketing, selectableEmailMarketing)

        constructor(id: Long, request: RequestPostUserDto): this(
            userId = id,
            essentialTermsOfService = request.essentialTermsOfService,
            essentialCollectionPersonalInformation = request.essentialCollectionPersonalInformation,
            essentialPersonalInformation = request.essentialPersonalInformation,
            essentialOverAge14 = request.essentialOverAge14,
            selectablePersonalMarketing = request.selectablePersonalMarketing
        )

        fun toApply(request: RequestPostUserAgreementDto) = this.apply {
            essentialTermsOfService = request.essentialTermsOfService
            essentialCollectionPersonalInformation = request.essentialCollectionPersonalInformation
            essentialPersonalInformation = request.essentialPersonalInformation
            essentialOverAge14 = request.essentialOverAge14
            selectablePersonalMarketing = request.selectablePersonalMarketing
        }

        fun toApply(isSelectablePersonalMarketing: Boolean) = this.apply {
            selectablePersonalMarketing = if (isSelectablePersonalMarketing) LocalDateTime.now() else LocalDateTime.of(1970, 1, 1, 0, 0)
        }
    }

    fun toDto() = Dto(userId, essentialTermsOfService, essentialPaidServiceTerms, essentialPersonalInformation, essentialCollectionPersonalInformation, essentialSensitiveInformation, essentialOverAge14, selectablePersonalInformation, selectablePersonalMarketing, selectableEmailMarketing)
}
