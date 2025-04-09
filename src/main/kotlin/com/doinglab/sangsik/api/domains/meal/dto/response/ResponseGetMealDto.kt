package com.doinglab.sangsik.api.domains.meal.dto.response

import com.doinglab.sangsik.api.amazon.s3.S3FileUrlGetter
import com.doinglab.sangsik.api.domains.cgm.entity.UserCgmBloodGlucose
import com.doinglab.sangsik.api.domains.common.dto.BaseDto
import com.doinglab.sangsik.api.domains.meal.entity.EatHistoryFood
import com.doinglab.sangsik.api.domains.user.entity.User
import com.doinglab.sangsik.api.domains.user.entity.UserBloodGlucose
import com.doinglab.sangsik.define.CoreTime.Companion.YYYYMMDD
import com.doinglab.sangsik.define.CoreTime.Companion.YYYYMMDDHHMMSS
import com.doinglab.sangsik.enums.*
import com.doinglab.sangsik.utils.amount
import com.doinglab.sangsik.utils.decrypt
import com.doinglab.sangsik.utils.getPropertyValue
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
import java.time.LocalDateTime
import com.doinglab.sangsik.api.domains.meal.entity.EatHistory as EatHistoryEntity

@Schema(description = "식사 기록 조회")
data class ResponseGetMealDto(
    override val body: GetMealBody
): BaseDto() {
    data class GetMealBody(
        var summary: List<Summary>? = listOf(),
        @Schema(description = "권장 영양소")
        val recommend: Nutrients,
        @Schema(description = "총 영양소")
        val total: Nutrients?,
        @Schema(description = "식사 기록")
        val eatHistory: List<EatHistory>?
    ) {
        data class Summary(
            @Schema(description = "영양소")
            val nutrient: Nutrient,
            @Schema(description = "영양소명")
            val name: String,
            @Schema(description = "권장 영양소")
            val recommend: Double? = -1.0,
            @Schema(description = "섭취 영양소")
            val total: Double? = -1.0
        )

        data class EatHistory(
            @Schema(description = "섭취타입 (BREAKFAST: 아침, LUNCH: 점심, DINNER: 저녁, SNACK: 간식, MORNINGSNACK: 간식(오전), AFTERNOONSNACK: 간식(오후), NIGHTSNACK: 야식)")
            val eatType: EatType,
            @Schema(description = "식사")
            val meal: List<Meal>,
            @Schema(description = "cgm 연동여부")
            val isCgmBloodSugar: Boolean? = false,
            @Schema(description = "혈당")
            val bloodSugar: List<BloodSugar>?,
            @Schema(description = "섭취타입 총 영양소")
            var total: Nutrients? = null,
        ) {
            data class Meal(
                @Schema(description = "식사 ID")
                val eatHistoryId: Long,
                @Schema(description = "식사 총 영양소")
                val total: Nutrients,
                @Schema(description = "식사 이미지")
                val image: String?,
                @Schema(description = "식사 이미지 썸네일")
                val imageThumb: String?,
                @Schema(description = "메모")
                val comment: String?,
                @Schema(description = "즐겨찾기 ID")
                val favoriteId: Long? = null,
                @Schema(description = "반복기록 ID")
                val repeatId: Long? = null,
                @Schema(description = "음식")
                val foodInfo: List<FoodInfo>?
            )

            @Schema(name = "ResponseGetMealFoodInfo")
            data class FoodInfo(
                @Schema(description = "음식 ID")
                val id: Long,
                @Schema(description = "음식 고유번호")
                val foodInfoId: Long?,
                @Schema(description = "커스텀 음식 고유번호")
                val customFoodInfoId: Long?,
                @Schema(description = "음식명")
                val foodName: String,
                @Schema(description = "음식위치")
                val position: Position,
                @Schema(description = "섭취한 양")
                val eatAmount: Double,
                @Schema(description = "음식 영양소")
                val nutrient: Nutrients
            ) {
                @Schema(name = "ResponseGetMealPosition")
                data class Position(
                    @Schema(description = "x축 시작점")
                    val xmin: Int,
                    @Schema(description = "x축 끝점")
                    val xmax: Int,
                    @Schema(description = "y축 시작점")
                    val ymin: Int,
                    @Schema(description = "y축 끝점")
                    val ymax: Int
                )
            }
        }

        @Schema(name = "ResponseGetMealNutrients")
        data class Nutrients(
            @Schema(description = "에너지(칼로리)")
            val calorie: Double? = -1.0,
            @Schema(description = "탄수화물")
            val carbohydrate: Double? = -1.0,
            @Schema(description = "단백질")
            val protein: Double? = -1.0,
            @Schema(description = "지방")
            val fat: Double? = -1.0,
            @Schema(description = "당류")
            val totalSugars: Double? = -1.0,
            @Schema(description = "식이섬유")
            val totalDietaryFiber: Double? = -1.0,
            @Schema(description = "칼슘")
            val calcium: Double? = -1.0,
            @Schema(description = "나트륨")
            val sodium: Double? = -1.0,
            @Schema(description = "콜레스테롤")
            val cholesterol: Double? = -1.0,
            @Schema(description = "포화 지방산")
            val saturatedFattyAcid: Double? = -1.0,
            @Schema(description = "트랜스 지방산")
            val transFattyAcid: Double? = -1.0,
        )

        data class BloodSugar(
            @Schema(description = "혈당 ID")
            val id :Long,
            @Schema(description = "혈당 입력 시간대 (BS10_BEFORE_BREAKFAST: 아침식전, BS14_AFTER_BREAKFAST: 아침식후 1시간, BS15_AFTER_BREAKFAST: 아침식후 2시간, BS16_BEFORE_MORNING_SNACK: 오전간식전, BS18_AFTER_MORNING_SNACK: 오전간식후 1시간, BS19_AFTER_MORNING_SNACK: 오전간식후 2시간, BS20_BEFORE_LUNCH: 점심식전, BS24_AFTER_LUNCH: 점심식후 1시간, BS25_AFTER_LUNCH: 점심식후 2시간, BS26_BEFORE_AFTERNOON_SNACK: 오후간식전, BS28_AFTER_AFTERNOON_SNACK: 오후간식후 1시간, BS29_AFTER_AFTERNOON_SNACK: 오후간식후 2시간, BS30_BEFORE_DINNER: 저녁식전, BS34_AFTER_DINNER: 저녁식후 1시간, BS35_AFTER_DINNER: 저녁식후 2시간, BS36_BEFORE_MIDNIGHT_SNACK: 야식전, BS38_AFTER_MIDNIGHT_SNACK: 야식후 1시간, BS39_AFTER_MIDNIGHT_SNACK: 야식후 2시간, BS90_BEFORE_BED: 취침전)")
            val inputType: BloodSugarInputType,
            @Schema(description = "혈당 입력 타입 (MORNING: 아침, AFTERNOON: 점심, DINNER: 저녁, BEFOREBED: 취침전, MORNINGSNACK: 오전간식, AFTERNOONSNACK: 오후간식, NIGHTSNACK: 야식)")
            val mealType: MealType,
            @Schema(description = "혈당 입력 시간대 (BEFORE: 식전, AFTER_1: 식후 1시간, AFTER: 식후 2시간)")
            val mealTime: MealTime?,
            @Schema(description = "혈당")
            val bloodSugar: Int,
            @Schema(description = "메모")
            val memo: String?,
            @Schema(description = "혈당 입력 시간")
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
            val date: LocalDateTime,
            @JsonIgnore
            val eatDate: LocalDate
        ) {
            constructor(bloodSugar: UserBloodGlucose.Dto, aesKey: String): this(
                id = bloodSugar.id,
                inputType = bloodSugar.inputType,
                mealType = bloodSugar.inputType.mealType,
                mealTime = bloodSugar.inputType.mealTime,
                bloodSugar = bloodSugar.bloodSugar.decrypt(aesKey).toInt(),
                memo = bloodSugar.memo,
                date = LocalDateTime.parse("${bloodSugar.inputDate}${bloodSugar.inputTime}", YYYYMMDDHHMMSS),
                eatDate = LocalDate.parse(bloodSugar.inputDate, YYYYMMDD)
            )

            constructor(bloodSugar: UserCgmBloodGlucose.Dto, meal: EatHistoryEntity.Dto, mealTime: MealTime, inputType: BloodSugarInputType, aesKey: String): this(
                id = bloodSugar.id,
                inputType = inputType,
                mealType = meal.eatType.mealType,
                mealTime = mealTime,
                bloodSugar = bloodSugar.value.decrypt(aesKey).toInt(),
                memo = "",
                date = bloodSugar.eventAt,
                eatDate = meal.date.toLocalDate()
            )
        }
    }

//    fun toApply(nutrients: List<Nutrient>) = this.apply {
//        "${this.body.summary.""}"
//        body.summary = nutrients.map {
//            GetMealBody.Summary(it, it.name, body.recommend["carbohydrate"])
//        }
//    }

    constructor(
        user: User.Dto,
        eatHistories: List<EatHistoryEntity.Dto>?,
        eatHistoryFoods: List<EatHistoryFood.Dto>?,
        userBloodSugars: List<GetMealBody.BloodSugar>?,
        s3EatHistoryImageFileUrlGetter: S3FileUrlGetter,
        date: LocalDate,
        isCgmBloodSugar: Boolean
    ): this(
        body = GetMealBody(
            recommend = makeNutrient(user.customcalorie.toDouble()),
            total = eatHistoryFoods?.let { makeNutrient(it) },
            eatHistory = eatHistories?.distinct()?.sortedBy { it.eatType.value }?.groupBy { it.eatType }?.map { eatHistory ->
                GetMealBody.EatHistory(
                    eatType = eatHistory.key,
                    meal = eatHistory.value.map { meal ->
                        GetMealBody.EatHistory.Meal(
                            eatHistoryId = meal.id,
                            total = eatHistoryFoods?.filter { meal.id == it.eatHistoryId }?.let { eatHistoryFood ->
                                GetMealBody.Nutrients(
                                    calorie = makeNutrient(eatHistoryFood.map { it.energy?.amount(it.eatAmount) }),
                                    carbohydrate = makeNutrient(eatHistoryFood.map { it.carbohydrate?.amount(it.eatAmount) }),
                                    protein = makeNutrient(eatHistoryFood.map { it.protein?.amount(it.eatAmount) }),
                                    fat = makeNutrient(eatHistoryFood.map { it.fat?.amount(it.eatAmount) }),
                                    totalSugars = makeNutrient(eatHistoryFood.map { it.totalSugars?.amount(it.eatAmount) }),
                                    totalDietaryFiber = makeNutrient(eatHistoryFood.map { it.totalDietaryFiber?.amount(it.eatAmount) }),
                                    calcium = makeNutrient(eatHistoryFood.map { it.calcium?.amount(it.eatAmount) }),
                                    sodium = makeNutrient(eatHistoryFood.map { it.sodium?.amount(it.eatAmount) }),
                                    cholesterol = makeNutrient(eatHistoryFood.map { it.cholesterol?.amount(it.eatAmount) }),
                                    saturatedFattyAcid = makeNutrient(eatHistoryFood.map { it.saturatedFattyAcid?.amount(it.eatAmount) }),
                                    transFattyAcid = makeNutrient(eatHistoryFood.map { it.transFattyAcid?.amount(it.eatAmount) }),
                                )
                            } ?: GetMealBody.Nutrients(),
                            image = meal.imgPath?.takeIf { it.isNotEmpty() }?.let { s3EatHistoryImageFileUrlGetter.getFileUrl(it) },
                            imageThumb = meal.imgThumbPath?.takeIf { it.isNotEmpty() }?.let { s3EatHistoryImageFileUrlGetter.getFileUrl(it) },
                            comment = meal.comment,
                            foodInfo = eatHistoryFoods?.filter { meal.id == it.eatHistoryId }
                                ?.sortedByDescending { it.energy }
                                ?.map { eatHistoryFood ->
                                    GetMealBody.EatHistory.FoodInfo(
                                        id = eatHistoryFood.id,
                                        foodInfoId = eatHistoryFood.foodInfoId,
                                        customFoodInfoId = eatHistoryFood.customFoodInfoId,
                                        foodName = eatHistoryFood.fullFoodName ?: eatHistoryFood.foodName,
                                        position = GetMealBody.EatHistory.FoodInfo.Position(
                                            xmin = eatHistoryFood.xmin,
                                            xmax = eatHistoryFood.xmax,
                                            ymin = eatHistoryFood.ymin,
                                            ymax = eatHistoryFood.ymax,
                                        ),
                                        eatAmount = eatHistoryFood.eatAmount,
                                        nutrient = GetMealBody.Nutrients(
                                            calorie = eatHistoryFood.energy?.amount(eatHistoryFood.eatAmount),
                                            carbohydrate = eatHistoryFood.carbohydrate?.amount(eatHistoryFood.eatAmount),
                                            protein = eatHistoryFood.protein?.amount(eatHistoryFood.eatAmount),
                                            fat = eatHistoryFood.fat?.amount(eatHistoryFood.eatAmount),
                                            totalSugars = eatHistoryFood.totalSugars?.amount(eatHistoryFood.eatAmount),
                                            totalDietaryFiber = eatHistoryFood.totalDietaryFiber?.amount(eatHistoryFood.eatAmount),
                                            calcium = eatHistoryFood.calcium?.amount(eatHistoryFood.eatAmount),
                                            sodium = eatHistoryFood.sodium?.amount(eatHistoryFood.eatAmount),
                                            cholesterol = eatHistoryFood.cholesterol?.amount(eatHistoryFood.eatAmount),
                                            saturatedFattyAcid = eatHistoryFood.saturatedFattyAcid?.amount(eatHistoryFood.eatAmount),
                                            transFattyAcid = eatHistoryFood.transFattyAcid?.amount(eatHistoryFood.eatAmount),
                                        )
                                    )
                                }
                        )
                    }.sortedByDescending { it.total.calorie },
                    isCgmBloodSugar = when (isCgmBloodSugar) {
                        true -> true
                        false -> when {
                            user.cgmConnectDate?.let { date >= it } == true -> true
                            else -> false
                        }
                    },
                    bloodSugar = userBloodSugars?.filter { it.inputType.eatType == eatHistory.key }
                )
            }
        ).apply {
            eatHistory?.map { meal ->
                meal.total = GetMealBody.Nutrients(
                    calorie = makeNutrient(meal.meal.map { it.total.calorie }),
                    carbohydrate = makeNutrient(meal.meal.map { it.total.carbohydrate }),
                    protein = makeNutrient(meal.meal.map { it.total.protein }),
                    fat = makeNutrient(meal.meal.map { it.total.fat }),
                    totalSugars = makeNutrient(meal.meal.map { it.total.totalSugars }),
                    totalDietaryFiber = makeNutrient(meal.meal.map { it.total.totalDietaryFiber }),
                    calcium = makeNutrient(meal.meal.map { it.total.calcium }),
                    sodium = makeNutrient(meal.meal.map { it.total.sodium }),
                    cholesterol = makeNutrient(meal.meal.map { it.total.cholesterol }),
                    saturatedFattyAcid = makeNutrient(meal.meal.map { it.total.saturatedFattyAcid }),
                    transFattyAcid = makeNutrient(meal.meal.map { it.total.transFattyAcid }),
                )
            }
        }
    )

    fun toApply(
        nutrients: List<Nutrient>,
        locale: String?
    ) = this.apply {
        body.summary = nutrients.map { nutrient ->
            GetMealBody.Summary(
                nutrient,
                when (locale) {
                    "en" -> nutrient.nameEn
                    else -> nutrient.nameKo
                },
                body.recommend.getPropertyValue(nutrient.code).toDouble(),
                body.total?.getPropertyValue(nutrient.code)?.toDouble()
            )
        }
    }

    companion object {
        private fun makeNutrient(nutrients: List<Double?>): Double =
            nutrients.filter { it != null && it >= 0.0 }.let { nutrient ->
                val sum = nutrient.sumOf { it!! }
                if (nutrient.isEmpty() || sum < 0.0) -1.0 else sum
            }

        fun makeNutrient(calorie: Double): GetMealBody.Nutrients =
            GetMealBody.Nutrients(
                calorie = calorie,
                carbohydrate = (calorie * 0.55) / 4,
                protein = (calorie * 0.2) / 4,
                fat = (calorie * 0.25) / 9,
                totalSugars = (calorie * 0.1) / 4,
                totalDietaryFiber = 25.0,
                calcium = 750.0,
                sodium = 2300.0,
                cholesterol = 300.0,
                saturatedFattyAcid = (calorie * 0.07) / 9,
                transFattyAcid = (calorie * 0.01) / 9,
            )

        fun makeNutrient(foods: List<EatHistoryFood.Dto>): GetMealBody.Nutrients =
            GetMealBody.Nutrients(
                calorie = makeNutrient(foods.map { it.energy?.amount(it.eatAmount) }),
                carbohydrate = makeNutrient(foods.map { it.carbohydrate?.amount(it.eatAmount) }),
                protein = makeNutrient(foods.map { it.protein?.amount(it.eatAmount) }),
                fat = makeNutrient(foods.map { it.fat?.amount(it.eatAmount) }),
                totalSugars = makeNutrient(foods.map { it.totalSugars?.amount(it.eatAmount) }),
                totalDietaryFiber = makeNutrient(foods.map { it.totalDietaryFiber?.amount(it.eatAmount) }),
                calcium = makeNutrient(foods.map { it.calcium?.amount(it.eatAmount) }),
                sodium = makeNutrient(foods.map { it.sodium?.amount(it.eatAmount) }),
                cholesterol = makeNutrient(foods.map { it.cholesterol?.amount(it.eatAmount) }),
                saturatedFattyAcid = makeNutrient(foods.map { it.saturatedFattyAcid?.amount(it.eatAmount) }),
                transFattyAcid = makeNutrient(foods.map { it.transFattyAcid?.amount(it.eatAmount) }),
            )
    }
}
