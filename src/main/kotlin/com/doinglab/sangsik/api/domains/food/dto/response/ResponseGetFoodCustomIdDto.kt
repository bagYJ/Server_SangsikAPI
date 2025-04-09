package com.doinglab.sangsik.api.domains.food.dto.response

import com.doinglab.sangsik.api.domains.common.dto.BaseDto
import com.doinglab.sangsik.api.domains.food.entity.FoodInfoR2

data class ResponseGetFoodCustomIdDto(
    override val body: ResponseGetFoodIdDto.GetFoodIdBody
): BaseDto() {
    constructor(foodInfo: FoodInfoR2.Dto): this(ResponseGetFoodIdDto.GetFoodIdBody(
        id = foodInfo.foodNumber,
        predictKey = foodInfo.predictKey,
        manufacturer = foodInfo.manufacturer,
        foodName = foodInfo.koName,
        foodCategory = "",
        foodType = foodInfo.foodType,
        mainCountry = "Korea",
        standardUnit = foodInfo.servingUnit,
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
        customFoodInfo = true
    ))
}
