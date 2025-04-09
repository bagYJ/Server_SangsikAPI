package com.doinglab.sangsik.api.domains.food.entity

import com.doinglab.sangsik.config.converter.NumericBooleanConverter
import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate

@Entity(name = "FoodInfoCustom")
@DynamicUpdate
class FoodInfoCustom(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FoodNumber")
    val foodNumber: Long,
    @Column(name = "UserId")
    val userId: Long,
    @Column(name = "LargeCategory")
    val largeCategory: String? = "",
    @Column(name = "MiddleCategory")
    val middleCategory: String? = "",
    @Column(name = "PredictKey")
    val predictKey: String? = "",
    @Column(name = "Priarity")
    val priarity: Int? = 0,
    @Column(name = "RelatedKeyword")
    val relatedKeyword: String? = "",
    @Column(name = "FoodName")
    val foodName: String? = "",
    @Column(name = "FoodType")
    val foodType: String? = "",
    @Column(name = "Manufacturer")
    val manufacturer: String? = "",
    @Column(name = "EnglishName")
    val englishName: String? = "",
    @Column(name = "ChineseName")
    val chineseName: String? = "",
    @Column(name = "JapaneseName")
    val japaneseName: String? = "",
    @Column(name = "FoodSearchClassification")
    val foodSearchClassification: Int? = 0,
    @Column(name = "SearchKeyword")
    val searchKeyword: String? = "",
    @Column(name = "StandardUnit")
    val standardUnit: String? = "",
    @Column(name = "Classification")
    val classification: String? = "",
    @Column(name = "Unit")
    val unit: String? = "",
    @Column(name = "TotalGram")
    val totalGram: Double? = 0.0,
    @Column(name = "Calorie")
    val calorie: Double? = 0.0,
    @Column(name = "CarbonHydrate")
    val carbonHydrate: Double? = 0.0,
    @Column(name = "Sugar")
    val sugar: Double? = 0.0,
    @Column(name = "Protein")
    val protein: Double? = 0.0,
    @Column(name = "Fat")
    val fat: Double? = 0.0,
    @Column(name = "SaturatedFattyAcid")
    val saturatedFattyAcid: Double? = 0.0,
    @Column(name = "TransFat")
    val transFat: Double? = 0.0,
    @Column(name = "Cholesterol")
    val cholesterol: Double? = 0.0,
    @Column(name = "Sodium")
    val sodium: Double? = 0.0,
    @Column(name = "Cellulose")
    val cellulose: Double? = 0.0,
    @Column(name = "Calcium")
    val calcium: Double? = 0.0,
    @Column(name = "VitaminA")
    val vitaminA: Double? = 0.0,
    @Column(name = "vitaminB1")
    val vitaminB1: Double? = 0.0,
    @Column(name = "VitaminC")
    val vitaminC: Double? = 0.0,
    @Column(name = "vitaminD")
    val vitaminD: Double? = 0.0,
    @Column(name = "vitaminE")
    val vitaminE: Double? = 0.0,
    @Convert(converter = NumericBooleanConverter::class)
    val deleteFlag: Boolean? = false,
) {
    fun toDto() = FoodInfoR2.Dto(foodNumber, predictKey, priarity, "", foodType, foodName, englishName, japaneseName, "Korea", manufacturer, unit ?: "g", -1.0, -1.0, standardUnit, "", totalGram, calorie, carbonHydrate, protein, fat, sugar, cellulose, calcium, -1.0, -1.0, -1.0, -1.0, sodium, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, vitaminA, vitaminB1, -1.0, -1.0, -1.0, -1.0, -1.0, vitaminC, vitaminD, vitaminE, -1.0, -1.0, -1.0, -1.0, -1.0, cholesterol, saturatedFattyAcid, -1.0, -1.0, -1.0, -1.0, transFat, -1.0, -1.0, -1.0, userId, deleteFlag)
}
