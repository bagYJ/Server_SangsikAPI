package com.doinglab.sangsik.api.domains.user.dto.response

import com.doinglab.sangsik.api.domains.common.dto.BaseDto
import com.doinglab.sangsik.api.domains.user.entity.User
import com.doinglab.sangsik.enums.*
import com.doinglab.sangsik.utils.height
import com.doinglab.sangsik.utils.weight
import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

data class ResponseGetUserDto(
    override val body: GetUserBody
): BaseDto() {
    data class GetUserBody(
        @Schema(description = "회원 ID")
        val id: Long,
        @Schema(description = "회원명")
        val name: String?,
        @Schema(description = "이메일")
        val email: String,
        @Schema(description = "전화번호")
        val phoneNumber: String,
        @Schema(description = "소셜 로그인 ID")
        val facebookId: String?,
        @Schema(description = "성별 (MALE: 남성, FEMALE: 여성)")
        val gender: Gender?,
        @Schema(description = "로케일정보 (ko, en...)")
        val locale: String,
        @Schema(description = "키 단위 (CM: cm, FT: feet)")
        val unit: UnitHeight,
        @Schema(description = "몸무게 단위 (KG: kg, LBS: pound)")
        val unitWeight: UnitWeight,
        @Schema(description = "키")
        val height: Float,
        @Schema(description = "몸무게")
        val weight: Float,
        @Schema(description = "권장섭취량")
        val customcalorie: Int,
        @Schema(description = "권장섭취량 업데이트 일자")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        val customcalorieDate: LocalDateTime?,
        @Schema(description = "유저가 사용할 로그인/회원가입 방법 (LOCAL: 이메일, NAVER: 네이버, KAKAO: 카카오, APPLE: 애플, GOOGLE: 구글)")
        val loginSource: LoginSource,
        @Schema(description = "토큰")
        val accessToken: String,
        @Schema(description = "평소활동량 (NONE: 미설정, INACTIVE: 주로 앉아있어요, LOWACTIVE: 가볍게 활동해요, ACTIVE: 꾸준히 운동해요, VERYACTIVE: 매우 활동적이에요)")
        val activityLevel: ActivityLevel,
        @Schema(description = "최종 로그인 일시")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        val lastLoginDate: LocalDateTime,
        @Schema(description = "회원 가입 일시")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        val joinDate: LocalDateTime,
//        val diabetesType: Int,
        @Schema(description = "등록 기관 ID")
        val companyId: Int,
        @Schema(description = "생년월일")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        val birthDate: LocalDateTime,
        @Schema(description = "기관연결정보")
        val dietProgram: DietProgram? = null,
        @Schema(description = "회원약관")
        val agreement: Agreement? = null,
        @Schema(description = "cgm 연동여부")
        val cgmConnect: Boolean = false,
        @Schema(description = "권장몸무게")
        var recommendWeight: Double = 0.0,
        @Schema(description = "권장칼로리 타입 (AUTO: 기본, PERSONAL: 개인정보 설정, PARTNER: 관리자 설정)")
        var customCalorieType: CustomCalorieType,
    ) {
        data class DietProgram(
            @Schema(description = "다이어트 프로그램 ID")
            val dietProgramId: Long?,
            @Schema(description = "채팅룸 ID")
            val chatRoomId: Long?,
            @Schema(description = "푸드렌즈 버전")
            var foodlensVersion: FoodlensVersion? = FoodlensVersion.FOODLENS,
        ) {
            constructor(dietProgram: User.Dto.DietProgram): this(dietProgram.dietProgramId, dietProgram.chatRoomId, dietProgram.foodlensVersion)
        }

        data class Agreement(
            @Schema(description = "필수 서비스 이용 약관 동의")
            val isEssentialTermsOfService: Boolean,
            @Schema(description = "필수 유료 서비스 약관 동의")
            @Deprecated("2023.04.04 사용하지 않음")
            val isEssentialPaidServiceTerms: Boolean,
            @Schema(description = "필수 개인정보 처리방침 이용 약관 동의")
            val isEssentialPersonalInformation: Boolean,
            @Schema(description = "개인정보 수집 및 이용 동의")
            val isEssentialCollectionPersonalInformation: Boolean,
            @Schema(description = "필수 민감 정보 동의")
            val isEssentialSensitiveInformation: Boolean,
//            @Schema(description = "만 14세 이상 동의")
//            val isEssentialOverAge14: Boolean,
            @Schema(description = "선택 가능한 개인정보 처리 동의")
            @Deprecated("2023.04.04 사용하지 않음")
            val isSelectablePersonalInformation: Boolean,
            @Schema(description = "푸시 마케팅 동의")
            val isSelectablePersonalMarketing: Boolean,
            @Schema(description = "이메일 마케팅 동의")
            val isSelectableEmailMarketing: Boolean,
        ) {
            constructor(agreement: User.Dto.Agreement): this(
                isEssentialTermsOfService = agreement.isEssentialTermsOfService,
                isEssentialPaidServiceTerms = agreement.isEssentialPaidServiceTerms,
                isEssentialPersonalInformation = agreement.isEssentialPersonalInformation,
                isEssentialCollectionPersonalInformation = agreement.isEssentialCollectionPersonalInformation,
                isEssentialSensitiveInformation = agreement.isEssentialSensitiveInformation,
//                isEssentialOverAge14 = agreement.isEssentialOverAge14,
                isSelectablePersonalInformation = agreement.isSelectablePersonalInformation,
                isSelectablePersonalMarketing = agreement.isSelectablePersonalMarketing,
                isSelectableEmailMarketing = agreement.isSelectableEmailMarketing
            )
        }
    }

    constructor(user: User.Dto, dietProgram: User.Dto.DietProgram?, agreement: User.Dto.Agreement?) : this(GetUserBody(
        id = user.id,
        name = user.name,
        email = user.email,
        phoneNumber = user.phoneNumber,
        facebookId = user.facebookId,
        gender = user.gender,
        locale = user.locale ?: Locale.EN.value,
        unit = user.unit,
        unitWeight = user.unitWeight,
        height = user.height.height(user.calcUnit, user.unit),
        weight = user.weight.weight(user.calcUnitWeight, user.unitWeight),
        customcalorie = when (dietProgram?.calorieIntegrate) {
            true -> dietProgram.recommendCalorie?.toInt() ?: 0
            else -> user.customcalorie
        },
        customcalorieDate = when (dietProgram?.calorieIntegrate) {
            true -> dietProgram.recommendCalorieDate
            else -> user.customcalorieDate
        },
        loginSource = user.loginSource,
        accessToken = user.accessToken,
        activityLevel = user.activityLevel,
        lastLoginDate = user.lastLoginDate,
        joinDate = user.joinDate,
        companyId = user.companyId,
        birthDate = user.birthDate,
        dietProgram = dietProgram?.let { GetUserBody.DietProgram(it) },
        agreement = agreement?.let { GetUserBody.Agreement(agreement) },
        cgmConnect = user.cgmConnect,
        recommendWeight = user.recommendWeight,
        customCalorieType = when (dietProgram?.calorieIntegrate) {
            true -> CustomCalorieType.PARTNER
            else -> user.customCalorieType
        },
    ))
}
