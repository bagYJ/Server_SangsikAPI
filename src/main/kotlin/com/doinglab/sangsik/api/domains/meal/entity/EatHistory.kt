package com.doinglab.sangsik.api.domains.meal.entity

import com.doinglab.sangsik.api.domains.meal.dto.request.RequestPostMealDto
import com.doinglab.sangsik.config.converter.NumericBooleanConverter
import com.doinglab.sangsik.enums.EatType
import com.doinglab.sangsik.enums.SaltSweetLevel
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "EatHistory")
class EatHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(name = "User_id")
    val userId: Long,
    @Column(name = "FoodInfo_id")
    val foodInfoId: Long = 0L,
    @Column(name = "food_name")
    val foodName: String = "",
    @Column(name = "predict_key")
    val predictKey: String = "",
    @Column(name = "eat_type")
    @Convert(converter = EatType.Converter::class)
    val eatType: EatType,
    val cholesterol: Double = -1.0,
    val fat: Double = -1.0,
    val protein: Double = -1.0,
    val carbonhydrate: Double = -1.0,
    val sodium: Double = -1.0,
    val totalgram: Double = 0.0,
    val calorie: Double = -1.0,
    @Column(name = "img_thumb_path")
    val imgThumbPath: String? = "",
    @Column(name = "img_path")
    val imgPath: String? = "",
    @Column(name = "fat_level")
    val fatLevel: Int = 0,
    @Column(name = "protein_level")
    val proteinIevel: Int = 0,
    @Column(name = "carbonhydrate_level")
    val carbonhydrateLevel: Int = 0,
    @Column(name = "sweet_level")
    @Convert(converter = SaltSweetLevel.Converter::class)
    val sweetLevel: SaltSweetLevel?,
    @Column(name = "salt_level")
    @Convert(converter = SaltSweetLevel.Converter::class)
    val saltLevel: SaltSweetLevel?,
    @Column(name = "eat_amount")
    val eatAmount: Float = 0F,
    @Column(name = "feedback_id")
    val feedbackId: Int = 0,
    @Column(name = "download_flag")
    @Convert(converter = NumericBooleanConverter::class)
    val downloadFlag: Boolean = false,
    val date: LocalDateTime,
    @Column(name = "staff_modify_date")
    val staffModifyDate: LocalDateTime?,
    val comment: String? = "",
    @Column(name = "staff_comment")
    val staffComment: String? = ""
) {
    data class Dto(
        val id: Long,
        val userId: Long,
        val foodInfoId: Long = 0L,
        val foodName: String = "",
        val predictKey: String = "",
        @Convert(converter = EatType.Converter::class)
        val eatType: EatType,
        val cholesterol: Double = -1.0,
        val fat: Double = -1.0,
        val protein: Double = -1.0,
        val carbonhydrate: Double = -1.0,
        val sodium: Double = -1.0,
        val totalgram: Double = 0.0,
        val calorie: Double = -1.0,
        val imgThumbPath: String? = "",
        val imgPath: String? = "",
        val fatLevel: Int = 0,
        val proteinIevel: Int = 0,
        val carbonhydrateLevel: Int = 0,
        @Convert(converter = SaltSweetLevel.Converter::class)
        val sweetLevel: SaltSweetLevel? = SaltSweetLevel.SMALL,
        @Convert(converter = SaltSweetLevel.Converter::class)
        val saltLevel: SaltSweetLevel? = SaltSweetLevel.SMALL,
        val eatAmount: Float = 0F,
        val feedbackId: Int = 0,
        @Convert(converter = NumericBooleanConverter::class)
        val downloadFlag: Boolean = false,
        val date: LocalDateTime,
        val staffModifyDate: LocalDateTime? = null,
        val comment: String? = "",
        val staffComment: String? = ""
    ) {
        companion object {
            fun totalNutrient(nutrients: List<Double>?): Double =
                nutrients?.filter { it > 0 }?.sum() ?: -1.0
        }

        fun toEntity() = EatHistory(id, userId, foodInfoId, foodName, predictKey, eatType, cholesterol, fat, protein, carbonhydrate, sodium, totalgram, calorie, imgThumbPath, imgPath, fatLevel, proteinIevel, carbonhydrateLevel, sweetLevel, saltLevel, eatAmount, feedbackId, downloadFlag, date, staffModifyDate, comment, staffComment)

        fun copy(eatType: EatType, date: LocalDateTime): Dto = this.copy(
            id = 0L,
            eatType = eatType,
            date = date,
            staffModifyDate = null,
            comment = "",
            staffComment = ""
        )

        fun copy(request: RequestPostMealDto): Dto = this.copy(
            eatType = request.eatType,
            date = request.date,
            comment = request.comment,
            cholesterol = totalNutrient(request.foodInfos.mapNotNull { it.nutrient.cholesterol }),
            fat = totalNutrient(request.foodInfos.mapNotNull { it.nutrient.fat }),
            protein = totalNutrient(request.foodInfos.mapNotNull { it.nutrient.protein }),
            carbonhydrate = totalNutrient(request.foodInfos.mapNotNull { it.nutrient.carbohydrate }),
            sodium = totalNutrient(request.foodInfos.mapNotNull { it.nutrient.sodium }),
            calorie = totalNutrient(request.foodInfos.mapNotNull { it.nutrient.energy })
        )

        constructor(userId: Long, request: RequestPostMealDto): this(
            id = 0L,
            userId = userId,
            eatType = request.eatType,
            imgPath = request.imagePath,
            imgThumbPath = request.imageThumbPath,
            date = request.date,
            comment = request.comment,
            cholesterol = totalNutrient(request.foodInfos.mapNotNull { it.nutrient.cholesterol }),
            fat = totalNutrient(request.foodInfos.mapNotNull { it.nutrient.fat }),
            protein = totalNutrient(request.foodInfos.mapNotNull { it.nutrient.protein }),
            carbonhydrate = totalNutrient(request.foodInfos.mapNotNull { it.nutrient.carbohydrate }),
            sodium = totalNutrient(request.foodInfos.mapNotNull { it.nutrient.sodium }),
            calorie = totalNutrient(request.foodInfos.mapNotNull { it.nutrient.energy })
        )
    }

    fun toDto() = Dto(id, userId, foodInfoId, foodName, predictKey, eatType, cholesterol, fat, protein, carbonhydrate, sodium, totalgram, calorie, imgThumbPath, imgPath, fatLevel, proteinIevel, carbonhydrateLevel, sweetLevel, saltLevel, eatAmount, feedbackId, downloadFlag, date, staffModifyDate, comment, staffComment)
}
