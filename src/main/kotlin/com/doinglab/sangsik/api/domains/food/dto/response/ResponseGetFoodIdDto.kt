package com.doinglab.sangsik.api.domains.food.dto.response

import com.doinglab.sangsik.api.domains.common.dto.BaseDto
import com.doinglab.sangsik.api.domains.food.entity.FoodInfoR2
import com.doinglab.sangsik.api.domains.food.entity.FoodServingUnitTranslation
import io.swagger.v3.oas.annotations.media.Schema

data class ResponseGetFoodIdDto(
    override val body: GetFoodIdBody
): BaseDto() {
    data class GetFoodIdBody(
        @Schema(description = "음식 ID")
        val id: Long,
        @Schema(description = "음식 고유 명칭")
        val predictKey: String?,
        @Schema(description = "제조사")
        val manufacturer: String?,
        @Schema(description = "음식명")
        val foodName: String?,
        @Schema(description = "음식 카테고리")
        val foodCategory: String?,
        @Schema(description = "음식 타입")
        val foodType: String?,
        @Schema(description = "음식 국가")
        val mainCountry: String?,
        @Schema(description = "음식 단위")
        val standardUnit: String?,
        @Schema(description = "분량 단위")
        val unit: String?,
        @Schema(description = "칼로리")
        val calories: Double?,
        @Schema(description = "나트륨")
        val sodium: Double?,
        @Schema(description = "당분")
        val sugar: Double?,
        @Schema(description = "단백질")
        val protein: Double?,
        @Schema(description = "칼슘")
        val calcium: Double?,
        @Schema(description = "콜레스트롤")
        val cholesterol: Double?,
        @Schema(description = "지방")
        val fat: Double?,
        @Schema(description = "탄수화물")
        val carbonhydrate: Double?,
        @Schema(description = "포화지방")
        val saturatedFat: Double?,
        @Schema(description = "트랜스 지방")
        val transFat: Double?,
        @Schema(description = "식이섬유")
        val dietaryFiber: Double?,
        @Schema(description = "비타민 A")
        val vitaminA: Double?,
        @Schema(description = "비타민 B")
        val vitaminB: Double?,
        @Schema(description = "비타민 C")
        val vitaminC: Double?,
        @Schema(description = "비타민 D")
        val vitaminD: Double?,
        @Schema(description = "비타민 E")
        val vitaminE: Double?,
        @Schema(description = "전체 무게(그람)")
        val totalGram: Double?,
        @Schema(description = "유저가 추가한 FoodInfo 인가?")
        val customFoodInfo: Boolean
    )

    companion object {
        val ignoreStandardUnits =
            listOf("공기", "국그릇", "그릇", "대접", "대접시", "뚝배기", "머그잔", "면기", "소접시", "작은공기", "종이컵", "종지", "중접시")
    }

    constructor(foodInfo: FoodInfoR2.Dto, servingUnit: FoodServingUnitTranslation.Dto?, locale: String) : this(
        GetFoodIdBody(
            id = foodInfo.foodNumber,
            predictKey = foodInfo.predictKey,
            manufacturer = foodInfo.manufacturer,
            foodName = if (listOf("ko", "kr", "\"ko\"", "\"kr\"")
                    .any { str -> str == locale.lowercase() }
            ) if (foodInfo.koName?.isEmpty() == true) foodInfo.enName else foodInfo.koName
            else if (foodInfo.enName?.isEmpty() == true) foodInfo.koName else foodInfo.enName,
            foodCategory = "",
            foodType = foodInfo.foodType,
            mainCountry = "Korea",
            standardUnit = (if (locale == "ko" || locale == "kr")
                if (servingUnit?.nameKo != null)
                    if (foodInfo.servingUnitSub == "-" || foodInfo.servingUnitSub?.isEmpty() == true) servingUnit.nameKo else "${servingUnit.nameKo} (${foodInfo.servingUnitSub})"
                else
                    if (foodInfo.servingUnitSub == "-" || foodInfo.servingUnitSub?.isEmpty() == true) foodInfo.servingUnit else "${foodInfo.servingUnit} (${foodInfo.servingUnitSub})"
            else
                servingUnit?.nameEn ?: foodInfo.servingUnit).let { foodInfoUnit ->
                if (foodInfo.unit?.startsWith("1") == true) {
                    foodInfo.unit.substring(1).let { unit ->
                        ignoreStandardUnits.find { unit.startsWith(it) }
                    }
                } else {
                    foodInfoUnit
                }
            },
            unit = foodInfo.unit,
            calories = foodInfo.energy,
            sodium = foodInfo.sodium,
            sugar = foodInfo.totalSugars,
            protein = foodInfo.protein,
            calcium = foodInfo.calcium,
            cholesterol = foodInfo.cholesterol,
            fat = foodInfo.fat,
            carbonhydrate = foodInfo.carbohydrate,
            saturatedFat = foodInfo.saturatedFattyAcid,
            transFat = foodInfo.transFattyAcid,
            dietaryFiber = foodInfo.totalDietaryFiber,
            vitaminA = foodInfo.vitaminA,
            vitaminB = foodInfo.thiamin,
            vitaminC = foodInfo.vitaminC,
            vitaminD = foodInfo.vitaminD,
            vitaminE = foodInfo.vitaminE,
            totalGram = foodInfo.totalGram,
            customFoodInfo = false
        )
    )
}
