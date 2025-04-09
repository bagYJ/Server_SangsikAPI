package com.doinglab.sangsik.api.domains.meal.entity

import com.doinglab.sangsik.api.domains.meal.dto.request.RequestPostMealDto
import com.doinglab.sangsik.utils.amount
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "EatHistoryFood")
class EatHistoryFood(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val eatHistoryId: Long,
    val foodInfoId: Long?,
    val customFoodInfoId: Long?,
    val manufacturer: String?,
    val userId: Long,
    val foodName: String,
    val fullFoodName: String?,
    val foodType: String?,
    val xmin: Int = 0,
    val xmax: Int = 0,
    val ymin: Int = 0,
    val ymax: Int = 0,
    val servingSize: Double,
    val totalServingSize: Double,
    val unit: String,
    val eatAmount: Double,
    val counts: String?,
    val energy: Double? = -1.0,
    val carbohydrate: Double? = -1.0,
    val protein: Double? = -1.0,
    val fat: Double? = -1.0,
    val totalSugars: Double? = -1.0,
    val totalDietaryFiber: Double? = -1.0,
    val calcium: Double? = -1.0,
    val iron: Double? = -1.0,
    val magnesium: Double? = -1.0,
    val phosphorus: Double? = -1.0,
    val potassium: Double? = -1.0,
    val sodium: Double? = -1.0,
    val zinc: Double? = -1.0,
    val selenium: Double? = -1.0,
    val retinol: Double? = -1.0,
    val betaCarotene: Double? = -1.0,
    val vitaminA: Double? = -1.0,
    val thiamin: Double? = -1.0,
    val riboflavin: Double? = -1.0,
    val niacin: Double? = -1.0,
    val vitaminB6: Double? = -1.0,
    val biotin: Double? = -1.0,
    val totalFolate: Double? = -1.0,
    val vitaminC: Double? = -1.0,
    val vitaminD: Double? = -1.0,
    val vitaminE: Double? = -1.0,
    val vitaminK: Double? = -1.0,
    val isoleucine: Double? = -1.0,
    val leucine: Double? = -1.0,
    val valine: Double? = -1.0,
    val bcaa: Double? = -1.0,
    val cholesterol: Double? = -1.0,
    val saturatedFattyAcid: Double? = -1.0,
    val epa: Double? = -1.0,
    val dha: Double? = -1.0,
    val omega3FattyAcid: Double? = -1.0,
    val omega6FattyAcid: Double? = -1.0,
    val transFattyAcid: Double? = -1.0,
    val grains: Double? = -1.0,
    val proteins: Double? = -1.0,
    val vegetables: Double? = -1.0,
    val fruits: Double? = -1.0,
    val oils: Double? = -1.0,
    val sugar: Double? = -1.0,
    val dairy: Double? = -1.0,
    val nuts: Double? = -1.0,
    val unhealthyOil: Double? = -1.0,
    @Convert(converter = EstimatedAmount.Converter::class)
    val estimatedAmount: EstimatedAmount? = null,
    @Convert(converter = Ingredient.Converter::class)
    val ingredients: List<Ingredient>? = listOf(),
    val updatedAt: LocalDateTime,
    val createdAt: LocalDateTime
) {
    class EstimatedAmount(
        @Schema(description = "실측된 음식양 (g)")
        val estimatedServingSize: Double = -1.0,
        @Schema(description = "실측된 섭취량, 기준 그릇 대비 비율")
        val estimatedServingAmount: Double = -1.0,
        @Schema(description = "실측된 음식의 총량")
        val estimatedVolume: Double? = -1.0,
        @Schema(description = "실측된 음식의 담긴 비율")
        val estimatedPortion: Double? = -1.0,
        @Schema(description = "그릇타입")
        val estimatedPlateType: String? = ""
    ) {
        @jakarta.persistence.Converter
        class Converter: AttributeConverter<EstimatedAmount, String> {
            override fun convertToDatabaseColumn(p0: EstimatedAmount?): String? = p0?.let { jacksonObjectMapper().writeValueAsString(p0) }
            override fun convertToEntityAttribute(p0: String?): EstimatedAmount? = p0?.let { jacksonObjectMapper().readValue(p0, EstimatedAmount::class.java) }
        }
    }

    class Ingredient(
        @Schema(description = "재료 key값")
        val key: String?,
        @Schema(description = "재료 이름")
        val name: String,
        @Schema(description = "재료 무게")
        val gram: Double = -1.0
    ) {
        @jakarta.persistence.Converter
        class Converter: AttributeConverter<List<Ingredient>, String> {
            private val objectMapper: ObjectMapper = jacksonObjectMapper()

            override fun convertToDatabaseColumn(attribute: List<Ingredient>?): String? {
                return attribute?.let { objectMapper.registerModules(JavaTimeModule()).writeValueAsString(it) }
            }

            override fun convertToEntityAttribute(dbData: String?): List<Ingredient>? {
                return dbData?.let { objectMapper.registerModules(JavaTimeModule()).readValue(it, object: TypeReference<List<Ingredient>>() {}) }
            }
        }
    }

    data class Dto(
        val id: Long,
        val eatHistoryId: Long,
        val foodInfoId: Long?,
        val customFoodInfoId: Long?,
        val manufacturer: String?,
        val userId: Long,
        val foodName: String,
        val fullFoodName: String?,
        val foodType: String?,
        val xmin: Int = 0,
        val xmax: Int = 0,
        val ymin: Int = 0,
        val ymax: Int = 0,
        val servingSize: Double,
        val totalServingSize: Double,
        val unit: String,
        val eatAmount: Double,
        val counts: String?,
        val energy: Double? = -1.0,
        val carbohydrate: Double? = -1.0,
        val protein: Double? = -1.0,
        val fat: Double? = -1.0,
        val totalSugars: Double? = -1.0,
        val totalDietaryFiber: Double? = -1.0,
        val calcium: Double? = -1.0,
        val iron: Double? = -1.0,
        val magnesium: Double? = -1.0,
        val phosphorus: Double? = -1.0,
        val potassium: Double? = -1.0,
        val sodium: Double? = -1.0,
        val zinc: Double? = -1.0,
        val selenium: Double? = -1.0,
        val retinol: Double? = -1.0,
        val betaCarotene: Double? = -1.0,
        val vitaminA: Double? = -1.0,
        val thiamin: Double? = -1.0,
        val riboflavin: Double? = -1.0,
        val niacin: Double? = -1.0,
        val vitaminB6: Double? = -1.0,
        val biotin: Double? = -1.0,
        val totalFolate: Double? = -1.0,
        val vitaminC: Double? = -1.0,
        val vitaminD: Double? = -1.0,
        val vitaminE: Double? = -1.0,
        val vitaminK: Double? = -1.0,
        val isoleucine: Double? = -1.0,
        val leucine: Double? = -1.0,
        val valine: Double? = -1.0,
        val bcaa: Double? = -1.0,
        val cholesterol: Double? = -1.0,
        val saturatedFattyAcid: Double? = -1.0,
        val epa: Double? = -1.0,
        val dha: Double? = -1.0,
        val omega3FattyAcid: Double? = -1.0,
        val omega6FattyAcid: Double? = -1.0,
        val transFattyAcid: Double? = -1.0,
        val grains: Double? = -1.0,
        val proteins: Double? = -1.0,
        val vegetables: Double? = -1.0,
        val fruits: Double? = -1.0,
        val oils: Double? = -1.0,
        val sugar: Double? = -1.0,
        val dairy: Double? = -1.0,
        val nuts: Double? = -1.0,
        val unhealthyOil: Double? = -1.0,
        val estimatedAmount: EstimatedAmount? = null,
        val ingredients: List<Ingredient>? = listOf(),
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        val updatedAt: LocalDateTime,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        val createdAt: LocalDateTime,
    ) {
        fun toEntity() = EatHistoryFood(id, eatHistoryId, foodInfoId, customFoodInfoId, manufacturer, userId, foodName, fullFoodName, foodType, xmin, xmax, ymin, ymax, servingSize, totalServingSize, unit, eatAmount, counts, energy, carbohydrate, protein, fat, totalSugars, totalDietaryFiber, calcium, iron, magnesium, phosphorus, potassium, sodium, zinc, selenium, retinol, betaCarotene, vitaminA, thiamin, riboflavin, niacin, vitaminB6, biotin, totalFolate, vitaminC, vitaminD, vitaminE, vitaminK, isoleucine, leucine, valine, bcaa, cholesterol, saturatedFattyAcid, epa, dha, omega3FattyAcid, omega6FattyAcid, transFattyAcid, grains, proteins, vegetables, fruits, oils, sugar, dairy, nuts, unhealthyOil, estimatedAmount, ingredients, updatedAt, createdAt)

        fun copy(eatHistoryId: Long): Dto = this.copy(
            id = 0L,
            eatHistoryId = eatHistoryId,
            updatedAt = LocalDateTime.now(),
            createdAt = LocalDateTime.now()
        )

        fun toMultiPredictPosition(eatHistoryId: Long): MultiPredictPositions.Dto = MultiPredictPositions.Dto(
            id = 0L,
            eatHistoryId = eatHistoryId,
            foodInfoId = foodInfoId ?: 0L,
            customFoodInfoId = customFoodInfoId ?: 0L,
            foodName = foodName,
            imgPath = "",
            xmin = xmin,
            xmax = xmax,
            ymin = ymin,
            ymax = ymax,
            score = 0,
            eatamount = eatAmount.toFloat(),
            gram = totalServingSize.toFloat(),
            calorie = energy?.toFloat(),
            candidatelist = "",
            staffModifyDate = null
        )

        fun toMatch(food: Dto): Boolean =
            foodInfoId == food.foodInfoId && customFoodInfoId == food.customFoodInfoId && eatAmount == food.eatAmount

        constructor(userId: Long, eatHistoryId: Long, food: RequestPostMealDto.FoodInfo): this(
            id = 0L,
            eatHistoryId = eatHistoryId,
            foodInfoId = food.nutrient.foodInfoId,
            customFoodInfoId = food.nutrient.customFoodInfoId,
            manufacturer = food.nutrient.manufacturer,
            foodName = food.nutrient.foodName,
            fullFoodName = food.nutrient.fullFoodName,
            foodType = food.nutrient.foodType,
            userId = userId,
            xmin = food.position.xmin,
            xmax = food.position.xmax,
            ymin = food.position.ymin,
            ymax = food.position.ymax,
            servingSize = food.nutrient.servingSize,
            totalServingSize = food.nutrient.totalServingSize,
            unit = food.nutrient.unit,
            eatAmount = food.eatAmount,
            counts = food.nutrient.counts,
            energy = food.nutrient.energy,
            carbohydrate = food.nutrient.carbohydrate,
            protein = food.nutrient.protein,
            fat = food.nutrient.fat,
            totalSugars = food.nutrient.totalSugars,
            totalDietaryFiber = food.nutrient.totalDietaryFiber,
            calcium = food.nutrient.calcium,
            iron = food.nutrient.iron,
            magnesium = food.nutrient.magnesium,
            phosphorus = food.nutrient.phosphorus,
            potassium = food.nutrient.potassium,
            sodium = food.nutrient.sodium,
            zinc = food.nutrient.zinc,
            selenium = food.nutrient.selenium,
            retinol = food.nutrient.retinol,
            betaCarotene = food.nutrient.betaCarotene,
            vitaminA = food.nutrient.vitaminA,
            thiamin = food.nutrient.thiamin,
            riboflavin = food.nutrient.riboflavin,
            niacin = food.nutrient.niacin,
            vitaminB6 = food.nutrient.vitaminB6,
            biotin = food.nutrient.biotin,
            totalFolate = food.nutrient.totalFolate,
            vitaminC = food.nutrient.vitaminC,
            vitaminD = food.nutrient.vitaminD,
            vitaminE = food.nutrient.vitaminE,
            vitaminK = food.nutrient.vitaminK,
            isoleucine = food.nutrient.isoleucine,
            leucine = food.nutrient.leucine,
            valine = food.nutrient.valine,
            bcaa = food.nutrient.bcaa,
            cholesterol = food.nutrient.cholesterol,
            saturatedFattyAcid = food.nutrient.saturatedFattyAcid,
            epa = food.nutrient.epa,
            dha = food.nutrient.dha,
            omega3FattyAcid = food.nutrient.omega3FattyAcid,
            omega6FattyAcid = food.nutrient.omega6FattyAcid,
            transFattyAcid = food.nutrient.transFattyAcid,
            grains = food.nutrient.grains,
            proteins = food.nutrient.proteins,
            vegetables = food.nutrient.vegetables,
            fruits = food.nutrient.fruits,
            oils = food.nutrient.oils,
            sugar = food.nutrient.sugars,
            dairy = food.nutrient.dairy,
            nuts = food.nutrient.nuts,
            unhealthyOil = food.nutrient.unhealthyOil,
            estimatedAmount = food.estimatedAmount,
            ingredients = food.ingredients,
            updatedAt = LocalDateTime.now(),
            createdAt = LocalDateTime.now()
        )

        @jakarta.persistence.Converter
        class Converter: AttributeConverter<List<Dto>, String> {
            private val objectMapper: ObjectMapper = jacksonObjectMapper()

            override fun convertToDatabaseColumn(attribute: List<Dto>?): String {
                return attribute?.let { objectMapper.registerModules(JavaTimeModule()).writeValueAsString(it) } ?: ""
            }

            override fun convertToEntityAttribute(dbData: String?): List<Dto>? {
                return dbData?.let { objectMapper.registerModules(JavaTimeModule()).readValue(it, object: TypeReference<List<Dto>>() {}) }
            }
        }
    }

    fun toDto() = Dto(id, eatHistoryId, foodInfoId, customFoodInfoId, manufacturer, userId, foodName, fullFoodName, foodType, xmin, xmax, ymin, ymax, servingSize, totalServingSize, unit, eatAmount, counts, energy, carbohydrate, protein, fat, totalSugars, totalDietaryFiber, calcium, iron, magnesium, phosphorus, potassium, sodium, zinc, selenium, retinol, betaCarotene, vitaminA, thiamin, riboflavin, niacin, vitaminB6, biotin, totalFolate, vitaminC, vitaminD, vitaminE, vitaminK, isoleucine, leucine, valine, bcaa, cholesterol, saturatedFattyAcid, epa, dha, omega3FattyAcid, omega6FattyAcid, transFattyAcid, grains, proteins, vegetables, fruits, oils, sugar, dairy, nuts, unhealthyOil, estimatedAmount, ingredients, updatedAt, createdAt)
}
