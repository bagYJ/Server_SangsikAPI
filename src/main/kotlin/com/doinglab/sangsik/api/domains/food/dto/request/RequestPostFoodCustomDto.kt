package com.doinglab.sangsik.api.domains.food.dto.request

import com.doinglab.sangsik.api.domains.food.entity.FoodInfoR2
import com.doinglab.sangsik.enums.FoodCustomUnit
import io.swagger.v3.oas.annotations.media.Schema

data class RequestPostFoodCustomDto(
    @Schema(description = "음식명")
    val foodName: String,
    @Schema(description = "제조사")
    val manufacturer: String? = "",
    @Schema(description = "분량 단위")
    val unit: FoodCustomUnit? = FoodCustomUnit.G,
    @Schema(description = "음식 단위")
    val standardUnit: String? = "",
    @Schema(description = "칼로리")
    val calorie: Double? = 0.0,
    @Schema(description = "나트륨")
    val sodium: Double? = 0.0,
    @Schema(description = "당분")
    val sugar: Double? = 0.0,
    @Schema(description = "단백질")
    val protein: Double? = 0.0,
    @Schema(description = "칼슘")
    val calcium: Double? = 0.0,
    @Schema(description = "콜레스트롤")
    val cholesterol: Double? = 0.0,
    @Schema(description = "지방")
    val fat: Double? = 0.0,
    @Schema(description = "탄수화물")
    val carbonhydrate: Double? = 0.0,
    @Schema(description = "포화지방")
    val saturatedFat: Double? = 0.0,
    @Schema(description = "트랜스 지방")
    val transFat: Double? = 0.0,
    @Schema(description = "식이섬유")
    val dietaryFiber: Double? = 0.0,
    @Schema(description = "비타민 A")
    val vitaminA: Double? = 0.0,
    @Schema(description = "비타민 B")
    val vitaminB: Double? = 0.0,
    @Schema(description = "비타민 C")
    val vitaminC: Double? = 0.0,
    @Schema(description = "비타민 D")
    val vitaminD: Double? = 0.0,
    @Schema(description = "비타민 E")
    val vitaminE: Double? = 0.0,
    @Schema(description = "전체 무게(그람)")
    val totalGram: Double,
) {
    fun toDto() = FoodInfoR2.Dto(
        foodNumber = 0L,
        koName = foodName,
        manufacturer = manufacturer,
        unit = unit?.code,
        servingUnit = standardUnit,
        totalGram = totalGram,
        energy = calorie,
        carbohydrate = carbonhydrate,
        totalSugars = sugar,
        protein = protein,
        fat = fat,
        saturatedFattyAcid = saturatedFat,
        transFattyAcid = transFat,
        cholesterol = cholesterol,
        sodium = sodium,
        totalDietaryFiber = dietaryFiber,
        calcium = calcium,
        vitaminA = vitaminA,
        thiamin = vitaminB,
        vitaminC = vitaminC,
        vitaminD = vitaminD,
        vitaminE = vitaminE
    )
}
