package com.doinglab.sangsik.api.domains.user.entity

import com.doinglab.sangsik.api.domains.dietProgram.entity.DietProgramEnrolledUser
import com.doinglab.sangsik.api.domains.staff.entity.Staff
import com.doinglab.sangsik.api.domains.user.dto.request.RequestPostUserDto
import com.doinglab.sangsik.api.domains.user.dto.request.RequestPutUserDto
import com.doinglab.sangsik.config.converter.NumericBooleanConverter
import com.doinglab.sangsik.define.CoreDefine.Companion.APP_NAME
import com.doinglab.sangsik.define.CoreDefine.Companion.DEFAULT_CALORIE
import com.doinglab.sangsik.define.CoreDefine.Companion.LOW_CALORIE
import com.doinglab.sangsik.enums.*
import com.doinglab.sangsik.utils.getAge
import com.doinglab.sangsik.utils.height
import com.doinglab.sangsik.utils.weight
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.UUID.randomUUID
import kotlin.math.pow

@Entity(name = "User")
@DynamicUpdate
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val appName: String,
    val subAppName: String,
    val name: String,
    val email: String,
    val phoneNumber: String,
    @Column(name = "facebook_id")
    val facebookId: String?,
    val password: String,
    val age: Int,
    val point: Int,
    @Column(name = "premium_start")
    val premiumStart: LocalDateTime,
    @Column(name = "premium_end")
    val premiumEnd: LocalDateTime,
    val locale: String?,
    @Convert(converter = UnitHeight.Converter::class)
    val unit: UnitHeight,
    @Convert(converter = UnitWeight.Converter::class)
    val unitWeight: UnitWeight,
    @Convert(converter = UnitHeight.Converter::class)
    val calcUnit: UnitHeight = UnitHeight.CM,
    @Convert(converter = UnitWeight.Converter::class)
    val calcUnitWeight: UnitWeight = UnitWeight.KG,
    val height: Float,
    val weight: Float,
    @Column(name = "weight_goal")
    val weightGoal: Float,
    val targetcalorie: Int,
    val customcalorie: Int,
    val customcalorieDate: LocalDateTime? = null,
    val customcalorieIndex: Int,
    @Convert(converter = TargetAct.Converter::class)
    val targetAct: TargetAct,
    val targetWater: Int,
    @Column(name = "login_source")
    @Convert(converter = LoginSource.Converter::class)
    val loginSource: LoginSource,
    @Column(name = "access_token")
    val accessToken: String,
    @Column(name = "resetpassword_code")
    val resetpasswordCode: String? = "",
    @Column(name = "resetpassword_limit")
    val resetpasswordLimit: LocalDateTime? = null,
    @Column(name = "activity_level")
    @Convert(converter = ActivityLevel.Converter::class)
    val activityLevel: ActivityLevel,
    val lastLoginDate: LocalDateTime,
    @Convert(converter = NumericBooleanConverter::class)
    val firstlogin: Boolean,
    val joinDate: LocalDateTime,
    val essentialAgreementDate: LocalDateTime,
    val selectableAgreementDate: LocalDateTime,
    @Column(name = "diabetes_type")
    val diabetesType: Int,
    val companyId: Int,
    val birthDate: LocalDateTime,
    @Convert(converter = NumericBooleanConverter::class)
    val useRecommendEatTime: Boolean,
    val breakfastStartTime: LocalTime,
    val breakfastEndTime: LocalTime,
    val lunchStartTime: LocalTime,
    val lunchEndTime: LocalTime,
    val dinnerStartTime: LocalTime,
    val dinnerEndTime: LocalTime,
    val targetHistoryCalorie: Int,
    @Convert(converter = Gender.Converter::class)
    val gender: Gender? = null
) {
    data class Dto(
        val id: Long = 0L,
        val appName: String,
        val subAppName: String = "",
        val email: String,
        val phoneNumber: String = "",
        val facebookId: String? = null,
        val point: Int = 0,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        val premiumStart: LocalDateTime = LocalDateTime.of(1900, 1, 1, 0, 0, 0),
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        val premiumEnd: LocalDateTime = LocalDateTime.of(1900, 1, 1, 0, 0, 0),
        val locale: String? = "",
        val weightGoal: Float = 0F,
        val targetcalorie: Int = 0,
        val customcalorieIndex: Int = -1,
        val targetAct: TargetAct = TargetAct.NONE,
        val targetWater: Int = 2000,
        val loginSource: LoginSource,
        val accessToken: String,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        val joinDate: LocalDateTime = LocalDateTime.now(),
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        val essentialAgreementDate: LocalDateTime = LocalDateTime.of(1900, 1, 1, 0, 0, 0),
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        val selectableAgreementDate: LocalDateTime = LocalDateTime.of(1900, 1, 1, 0, 0, 0),
        val diabetesType: Int = 0,
        val companyId: Int = 1,
        val useRecommendEatTime: Boolean = false,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
        val breakfastStartTime: LocalTime = LocalTime.of(6, 0, 0),
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
        val breakfastEndTime: LocalTime = LocalTime.of(10, 0, 0),
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
        val lunchStartTime: LocalTime = LocalTime.of(11, 0, 0),
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
        val lunchEndTime: LocalTime = LocalTime.of(15, 0, 0),
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
        val dinnerStartTime: LocalTime = LocalTime.of(17, 0, 0),
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
        val dinnerEndTime: LocalTime = LocalTime.of(21, 0, 0),
        val targetHistoryCalorie: Int = 0,

        var customcalorie: Int = 0,
        var unit: UnitHeight = UnitHeight.CM,
        var unitWeight: UnitWeight = UnitWeight.KG,
        var calcUnit: UnitHeight = UnitHeight.CM,
        var calcUnitWeight: UnitWeight = UnitWeight.KG,
        var gender: Gender? = null,
        var height: Float = 0F,
        var weight: Float = 0F,
        var activityLevel: ActivityLevel = ActivityLevel.NONE,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        var lastLoginDate: LocalDateTime = LocalDateTime.now(),
        var firstlogin: Boolean = true,
        var resetpasswordCode: String? = null,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        var resetpasswordLimit: LocalDateTime? = null,
        @JsonIgnore
        var password: String = "",
        var name: String = "",
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        var birthDate: LocalDateTime = LocalDateTime.of(1900, 1, 1, 0, 0, 0),
        var age: Int = 0,
        var customcalorieDate: LocalDateTime? = null,
        var recommendWeight: Double = 0.0,
        var customCalorieType: CustomCalorieType = CustomCalorieType.AUTO,
        var dietProgram: DietProgram? = null,
        var agreement: Agreement? = null,
        var calcCustomCalorie: Int = 0,
        var cgmConnect: Boolean = false,
        var cgmConnectDate: LocalDate? = null,
        var cgmCalledAt: LocalDateTime? = null
    ) {
        val uuid = randomUUID().toString()
        var calcWeight = weight.weight(calcUnitWeight, UnitWeight.KG)
        var calcHeight = height.height(calcUnit, UnitHeight.CM)

        data class DietProgram(
            val dietProgramId: Long?,
            val chatRoomId: Long?,
            val status: EnrolledStatus?,
            val recommendCalorie: Float?,
            val recommendCalorieDate: LocalDateTime?,
            val calorieIntegrate: Boolean,
            var foodlensVersion: FoodlensVersion?,
        ) {
            constructor(dietProgram: Pair<DietProgramEnrolledUser.Dto?, Staff.Dto?>?): this(
                dietProgramId = dietProgram?.first?.id,
                chatRoomId = dietProgram?.first?.roomId,
                status = dietProgram?.first?.status,
                recommendCalorie = dietProgram?.first?.recommendCalorie,
                recommendCalorieDate = dietProgram?.first?.recommendCalorieDate,
                calorieIntegrate = dietProgram?.first?.calorieIntegrate == true,
                foodlensVersion = dietProgram?.second?.foodlensVersion ?: FoodlensVersion.CALOAI,
            )
        }

        data class Agreement(
            val isEssentialTermsOfService: Boolean,
            val isEssentialPaidServiceTerms: Boolean,
            val isEssentialPersonalInformation: Boolean,
            val isEssentialCollectionPersonalInformation: Boolean,
            val isEssentialSensitiveInformation: Boolean,
//            val isEssentialOverAge14: Boolean,
            val isSelectablePersonalInformation: Boolean,
            val isSelectablePersonalMarketing: Boolean,
            val isSelectableEmailMarketing: Boolean
        ) {
            constructor(agreement: UserAgreement.Dto?): this(
                isEssentialTermsOfService = true,
                isEssentialPaidServiceTerms = true,
                isEssentialPersonalInformation = true,
                isEssentialCollectionPersonalInformation = true,
                isEssentialSensitiveInformation = true,
//                isEssentialOverAge14 = agreement?.essentialOverAge14?.isEqual(LocalDateTime.of(1970, 1, 1, 0, 0, 0)) == false,
                isSelectablePersonalInformation = agreement?.selectablePersonalInformation?.isEqual(LocalDateTime.of(1970, 1, 1, 0, 0, 0)) == false,
                isSelectablePersonalMarketing = agreement?.selectablePersonalMarketing?.isEqual(LocalDateTime.of(1970, 1, 1, 0, 0, 0)) == false,
                isSelectableEmailMarketing = agreement?.selectableEmailMarketing?.isEqual(LocalDateTime.of(1970, 1, 1, 0, 0, 0)) == false
            )
        }

        constructor(token: String, request: RequestPostUserDto, companyId: Int): this(
            accessToken = token,
            appName = APP_NAME,
            email = request.emailId!!,
            facebookId = request.socialId?.takeIf { it.isNotEmpty() },
            loginSource = request.loginSource,
            companyId = companyId,
            locale = request.locale
        )

        fun toEntity() = User(id, appName, subAppName, name, email, phoneNumber, facebookId, password, age, point, premiumStart, premiumEnd, locale, unit, unitWeight, calcUnit, calcUnitWeight, height, weight, weightGoal, targetcalorie, customcalorie, customcalorieDate, customcalorieIndex, targetAct, targetWater, loginSource, accessToken, resetpasswordCode, resetpasswordLimit, activityLevel, lastLoginDate, firstlogin, joinDate, essentialAgreementDate, selectableAgreementDate, diabetesType, companyId, birthDate, useRecommendEatTime, breakfastStartTime, breakfastEndTime, lunchStartTime, lunchEndTime, dinnerStartTime, dinnerEndTime, targetHistoryCalorie, gender)

        fun toApply(request: RequestPutUserDto) = this.apply {
            gender = request.gender
            birthDate = request.birthDate
            height = request.height
            weight = request.weight
            activityLevel = request.activityLevel
            calcUnit = unit
            calcUnitWeight = unitWeight
            calcWeight = weight.weight(calcUnitWeight, UnitWeight.KG)
            calcHeight = height.height(calcUnit, UnitHeight.CM)
            recommendWeight = when {
                calcWeight > 0 && calcHeight > 0 && gender != null -> ((calcHeight * 0.01).pow(2) * gender!!.mass).weight(unitWeight)
                else -> 0.0
            }
            customCalorieType = this.customCalorieType(calcWeight, calcHeight, gender)
            customcalorieDate = when {
                customCalorieType == CustomCalorieType.PERSONAL -> LocalDateTime.now()
                else -> null
            }
        }

        fun toApply(changeUnit: UnitHeight) = this.apply {
            unit = changeUnit
        }

        fun toApply(changeUnit: UnitWeight) = this.apply {
            unitWeight = changeUnit
        }

        fun customCalorie(calcWeight: Float, calcHeight: Float, gender: Gender?, recommendWeight: Double, activityLevel: ActivityLevel): Int =
            when {
                calcWeight > 0 && calcHeight > 0 && gender != null -> activityLevel.calc * (9.99 * when {
                    calcWeight / recommendWeight * 100 < 130 -> calcWeight
                    else -> (recommendWeight + (calcWeight - recommendWeight) * 0.25)
                }.toDouble() + (6.25 * calcHeight) - (4.92 * age) + gender.calc)
                else -> DEFAULT_CALORIE
            }.toInt()

        fun customCalorieType (calcWeight: Float, calcHeight: Float, gender: Gender?): CustomCalorieType =
            when {
                calcWeight > 0 && calcHeight > 0 && gender != null -> CustomCalorieType.PERSONAL
                else -> CustomCalorieType.AUTO
            }
    }

    fun toDto() = Dto(id, appName, subAppName, email, phoneNumber, facebookId, point, premiumStart, premiumEnd, locale, weightGoal, targetcalorie, customcalorieIndex, targetAct, targetWater, loginSource, accessToken, joinDate, essentialAgreementDate, selectableAgreementDate, diabetesType, companyId, useRecommendEatTime, breakfastStartTime, breakfastEndTime, lunchStartTime, lunchEndTime, dinnerStartTime, dinnerEndTime, targetHistoryCalorie, customcalorie, unit, unitWeight, calcUnit, calcUnitWeight, gender, height, weight, activityLevel, lastLoginDate, firstlogin, resetpasswordCode, resetpasswordLimit, password, name, birthDate, age).let {
        it.age = getAge(it.birthDate.toLocalDate())
        it.recommendWeight = when {
            it.calcWeight > 0 && it.calcHeight > 0 && it.gender != null -> ((it.calcHeight * 0.01).pow(2) * it.gender!!.mass).weight(it.unitWeight)
            else -> 0.0
        }
        it.customcalorie = it.customCalorie(it.calcWeight, it.calcHeight, it.gender, it.recommendWeight.weight(it.unitWeight, UnitWeight.KG), it.activityLevel).let { calorie ->
            if (calorie > LOW_CALORIE) calorie else LOW_CALORIE
        }
        it.customCalorieType = it.customCalorieType(it.calcWeight, it.calcHeight, it.gender)
        it.calcCustomCalorie = it.customCalorie(it.calcWeight, it.calcHeight, it.gender, it.recommendWeight.weight(it.unitWeight, UnitWeight.KG), it.activityLevel)
        it.customcalorieDate = customcalorieDate
        it
    }
}
