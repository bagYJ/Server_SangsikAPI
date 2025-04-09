package com.doinglab.sangsik.api.domains.food.entity

import com.doinglab.sangsik.api.domains.food.dto.request.RequestPostFoodCustomDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name = "FoodInfo")
class FoodInfo(
    @Id
    @Column(name = "FoodNumber")
    val foodNumber: Long,
    @Column(name = "LargeCategory")
    val largeCategory: String?,
    @Column(name = "MiddleCategory")
    val middleCategory: String?,
    @Column(name = "PredictKey")
    val predictKey: String?,
    @Column(name = "Priarity")
    val priarity: Int?,
    @Column(name = "RelatedKeyword")
    val relatedKeyword: String?,
    @Column(name = "FoodName")
    val foodName: String?,
    @Column(name = "FoodType")
    val foodType: String?,
    @Column(name = "Manufacturer")
    val manufacturer: String?,
    @Column(name = "EnglishName")
    val englishName: String?,
    @Column(name = "ChineseName")
    val chineseName: String?,
    @Column(name = "JapaneseName")
    val japaneseName: String?,
    @Column(name = "FoodSearchClassification")
    val foodSearchClassification: Int?,
    @Column(name = "Country")
    val country: String,
    @Column(name = "StandardUnit")
    val standardUnit: String?,
    @Column(name = "Classification")
    val classification: String?,
    @Column(name = "Unit")
    val unit: String?,
    @Column(name = "ServingSize")
    val servingSize: Double?,
    @Column(name = "TotalServingSize")
    val totalServingSize: Double?,
    val servingUnit: String?,
    val servingUnitSub: String?,
    @Column(name = "TotalGram")
    val totalGram: Double?,
    @Column(name = "Calorie")
    val calorie: Double?,
    @Column(name = "CarbonHydrate")
    val carbonHydrate: Double?,
    @Column(name = "Sugar")
    val sugar: Double?,
    @Column(name = "Protein")
    val protein: Double?,
    @Column(name = "Fat")
    val fat: Double?,
    @Column(name = "SaturatedFattyAcid")
    val saturatedFattyAcid: Double?,
    @Column(name = "TransFat")
    val transFat: Double?,
    @Column(name = "Cholesterol")
    val cholesterol: Double?,
    @Column(name = "Sodium")
    val sodium: Double?,
    @Column(name = "Cellulose")
    val cellulose: Double?,
    @Column(name = "Calcium")
    val calcium: Double?,
    @Column(name = "VitaminA")
    val vitaminA: Double?,
    @Column(name = "vitaminB1")
    val vitaminB1: Double?,
    @Column(name = "VitaminC")
    val vitaminC: Double?,
    @Column(name = "vitaminD")
    val vitaminD: Double?,
    @Column(name = "vitaminE")
    val vitaminE: Double?
) {
    data class Dto(
        val foodNumber: Long,
        val largeCategory: String? = "",
        val middleCategory: String? = "",
        val predictKey: String? = "",
        val priarity: Int? = 0,
        val relatedKeyword: String? = "",
        val foodName: String?,
        val foodType: String? = "",
        val manufacturer: String? = "",
        val englishName: String? = "",
        val chineseName: String? = "",
        val japaneseName: String? = "",
        val foodSearchClassification: Int? = 0,
        val country: String = "Korea",
        val standardUnit: String? = "",
        val classification: String? = "",
        val unit: String? = "",
        val servingSize: Double? = 0.0,
        val totalServingSize: Double? = 0.0,
        val servingUnit: String? = "",
        val servingUnitSub: String? = "",
        val totalGram: Double?,
        val calorie: Double?,
        val carbonHydrate: Double?,
        val sugar: Double?,
        val protein: Double?,
        val fat: Double?,
        val saturatedFattyAcid: Double?,
        val transFat: Double?,
        val cholesterol: Double?,
        val sodium: Double?,
        val cellulose: Double?,
        val calcium: Double?,
        val vitaminA: Double?,
        val vitaminB1: Double?,
        val vitaminC: Double?,
        val vitaminD: Double?,
        val vitaminE: Double?,
        val userId: Long? = 0L,
        val deleteFlag: Boolean? = false,
        val searchKeyword: String? = ""
    ) {
        fun toEntity() = FoodInfo(foodNumber, largeCategory, middleCategory, predictKey, priarity, relatedKeyword, foodName, foodType, manufacturer, englishName, chineseName, japaneseName, foodSearchClassification, country, standardUnit, classification, unit, servingSize, totalServingSize, servingUnit, servingUnitSub, totalGram, calorie, carbonHydrate, sugar, protein, fat, saturatedFattyAcid, transFat, cholesterol, sodium, cellulose, calcium, vitaminA, vitaminB1, vitaminC, vitaminD, vitaminE)

        fun toCustomEntity(userId: Long) = FoodInfoCustom(foodNumber, userId, largeCategory, middleCategory, predictKey, priarity, relatedKeyword, foodName, foodType, manufacturer, englishName, chineseName, japaneseName, foodSearchClassification, searchKeyword, standardUnit, classification, unit, totalGram, calorie, carbonHydrate, sugar, protein, fat, saturatedFattyAcid, transFat, cholesterol, sodium, cellulose, calcium, vitaminA, vitaminB1, vitaminC, vitaminD, vitaminE, deleteFlag)

        fun toCopy(request: RequestPostFoodCustomDto) = copy(
            foodName = request.foodName,
            manufacturer = request.manufacturer,
            standardUnit = request.standardUnit,
            unit = request.unit?.code,
            totalGram = request.totalGram,
            calorie = request.calorie,
            carbonHydrate = request.carbonhydrate,
            sugar = request.sugar,
            protein = request.protein,
            fat = request.fat,
            saturatedFattyAcid = request.saturatedFat,
            transFat = request.transFat,
            cholesterol = request.cholesterol,
            sodium = request.sodium,
            cellulose = request.dietaryFiber,
            calcium = request.calcium,
            vitaminA = request.vitaminA,
            vitaminB1 = request.vitaminB,
            vitaminC = request.vitaminC,
            vitaminD = request.vitaminD,
            vitaminE = request.vitaminE
        )
    }

    fun toDto() = Dto(foodNumber, largeCategory, middleCategory, predictKey, priarity, relatedKeyword, foodName, foodType, manufacturer, englishName, chineseName, japaneseName, foodSearchClassification, country, standardUnit, classification, unit, servingSize, totalServingSize, servingUnit, servingUnitSub, totalGram, calorie, carbonHydrate, sugar, protein, fat, saturatedFattyAcid, transFat, cholesterol, sodium, cellulose, calcium, vitaminA, vitaminB1, vitaminC, vitaminD, vitaminE)
}
