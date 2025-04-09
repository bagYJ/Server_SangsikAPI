package com.doinglab.sangsik.api.domains.meal.entity

import com.doinglab.sangsik.api.domains.meal.dto.request.RequestPostMealDto
import com.doinglab.sangsik.utils.amount
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "MultiPredictPositions")
class MultiPredictPositions(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "EatHistory_id")
    val eatHistoryId: Long,
    @Column(name = "FoodInfo_id")
    val foodInfoId: Long?,
    @Column(name = "CustomFoodInfo_id")
    val customFoodInfoId: Long?,
    @Column(name = "FoodName")
    val foodName: String,
    @Column(name = "img_path")
    val imgPath: String,
    val xmin: Int,
    val xmax: Int,
    val ymin: Int,
    val ymax: Int,
    val score: Int,
    val eatamount: Float,
    val gram: Float,
    val calorie: Float? = -1F,
//    @Convert(converter = Dto.Candidatelist.Converter::class)
    val candidatelist: String,
    @Column(name = "staff_modify_date")
    val staffModifyDate: LocalDateTime?
) {
    data class Dto(
        val id: Long,
        val eatHistoryId: Long,
        val foodInfoId: Long?,
        val customFoodInfoId: Long?,
        val foodName: String,
        val imgPath: String,
        val xmin: Int,
        val xmax: Int,
        val ymin: Int,
        val ymax: Int,
        val score: Int,
        val eatamount: Float,
        val gram: Float,
        val calorie: Float? = -1F,
        val candidatelist: String,
        val staffModifyDate: LocalDateTime? = null
    ) {
        data class Candidatelist(
            val id: Long,
            val name: String,
            val predictKey: String
        ) {
            @jakarta.persistence.Converter
            class Converter: AttributeConverter<Candidatelist, String> {
                override fun convertToDatabaseColumn(p0: Candidatelist?): String = jacksonObjectMapper().writeValueAsString(p0)
                override fun convertToEntityAttribute(p0: String?): Candidatelist = jacksonObjectMapper().readValue(p0, Candidatelist::class.java)
            }
        }

        constructor(eatHistoryId: Long, food: RequestPostMealDto.FoodInfo): this(
            id = 0L,
            eatHistoryId = eatHistoryId,
            foodInfoId = food.nutrient.foodInfoId,
            customFoodInfoId = food.nutrient.customFoodInfoId,
            foodName = food.name,
            imgPath = "",
            xmin = food.position.xmin,
            xmax = food.position.xmax,
            ymin = food.position.ymin,
            ymax = food.position.ymax,
            score = 0,
            eatamount = food.eatAmount.toFloat(),
            gram = food.nutrient.totalServingSize.toFloat(),
            calorie = food.nutrient.energy?.amount(food.eatAmount)?.toFloat(),
            candidatelist = ""
        )

        fun toEntity() = MultiPredictPositions(id, eatHistoryId, foodInfoId, customFoodInfoId, foodName, imgPath, xmin, xmax, ymin, ymax, score, eatamount, gram, calorie, candidatelist, staffModifyDate)
    }

    fun toDto() = Dto(id, eatHistoryId, foodInfoId, customFoodInfoId, foodName, imgPath, xmin, xmax, ymin, ymax, score, eatamount, gram, calorie, candidatelist, staffModifyDate)
}
