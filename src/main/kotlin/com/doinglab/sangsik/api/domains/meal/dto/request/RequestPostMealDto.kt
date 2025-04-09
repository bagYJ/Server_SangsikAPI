package com.doinglab.sangsik.api.domains.meal.dto.request

import com.doinglab.sangsik.api.domains.meal.entity.EatHistoryFood
import com.doinglab.sangsik.enums.EatType
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

data class RequestPostMealDto(
    @Schema(description = "섭취타입 (BREAKFAST: 아침, LUNCH: 점심, DINNER: 저녁, SNACK: 간식, MORNINGSNACK: 간식(오전), AFTERNOONSNACK: 간식(오후), NIGHTSNACK: 야식)")
    val eatType: EatType,
    @Schema(description = "섭취 시간", type = "string", example = "2024-01-01 00:00:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val date: LocalDateTime,
    @Deprecated(message = "추후 사용")
    @Schema(description = "즐겨찾기 ID")
    val favoriteLinkId: Long?,
    @Schema(description = "섭취 음식 정보")
    val foodInfos: List<FoodInfo>,
    @Schema(description = "코멘트")
    val comment: String?
) {
    @Schema(description = "섭취이미지 (내 식사에서 조회한 식단의 기등록 이미지 경로)")
    var imagePath: String? = ""
    @JsonIgnore
    var imageThumbPath: String? = ""

    @Schema(name = "RequestPostMealFoodInfo")
    data class FoodInfo(
        @Schema(description = "음식명")
        val name: String,
        @Schema(description = "음식명")
        val fullName: String,
        @Schema(description = "섭취한 양")
        val eatAmount: Double,
        @Schema(description = "음식위치")
        val position: Position,
        @Schema(description = "음식정보")
        val nutrient: Nutrients,
        @Schema(description = "실츨된 음식 양정보")
        val estimatedAmount: EatHistoryFood.EstimatedAmount?,
        @Schema(description = "재료 명 리스트")
        val ingredients: List<EatHistoryFood.Ingredient>?
    ) {
        @Schema(name = "RequestPostMealPosition")
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

        @Schema(name = "RequestPostMealNutrients")
        data class Nutrients(
            @Schema(description = "음식 ID")
            var foodInfoId: Long? = 0L,
            @Schema(description = "내음식 ID")
            var customFoodInfoId: Long? = 0L,
            @Schema(description = "제조사")
            val manufacturer: String,
            @Schema(description = "음식명")
            val foodName: String,
            @Schema(description = "음식명")
            val fullFoodName: String,
            @Schema(description = "음식타입")
            val foodType: String,
            @Schema(description = "1회 섭취량")
            val servingSize: Double,
            @Schema(description = "섭취량")
            val totalServingSize: Double,
            @Schema(description = "섭취량 단위")
            val unit: String,
            @Schema(description = "섭취 단위(그릇)")
            val counts: String? = "",
            @Schema(description = "에너지(칼로리)")
            val energy: Double? = -1.0,
            @Schema(description = "탄수화물 ")
            val carbohydrate: Double? = -1.0,
            @Schema(description = "단백질 ")
            val protein: Double? = -1.0,
            @Schema(description = "지방 ")
            val fat: Double? = -1.0,
            @Schema(description = "당류 ")
            val totalSugars: Double? = -1.0,
            @Schema(description = "식이섬유 ")
            val totalDietaryFiber: Double? = -1.0,
            @Schema(description = "칼슘 ")
            val calcium: Double? = -1.0,
            @Schema(description = "철 ")
            val iron: Double? = -1.0,
            @Schema(description = "마그네슘 ")
            val magnesium: Double? = -1.0,
            @Schema(description = "인 ")
            val phosphorus: Double? = -1.0,
            @Schema(description = "칼륨 ")
            val potassium: Double? = -1.0,
            @Schema(description = "나트륨 ")
            val sodium: Double? = -1.0,
            @Schema(description = "아연 ")
            val zinc: Double? = -1.0,
            @Schema(description = "셀레늄 ")
            val selenium: Double? = -1.0,
            @Schema(description = "레티놀 ")
            val retinol: Double? = -1.0,
            @Schema(description = "베타카로틴 ")
            val betaCarotene: Double? = -1.0,
            @Schema(description = "비타민 A ")
            val vitaminA: Double? = -1.0,
            @Schema(description = "비타민 B1 ")
            val thiamin: Double? = -1.0,
            @Schema(description = "비타민 B2 ")
            val riboflavin: Double? = -1.0,
            @Schema(description = "니아신 ")
            val niacin: Double? = -1.0,
            @Schema(description = "비타민 B6 ")
            val vitaminB6: Double? = -1.0,
            @Schema(description = "비오틴 ")
            val biotin: Double? = -1.0,
            @Schema(description = "엽산(DEF) ")
            val totalFolate: Double? = -1.0,
            @Schema(description = "비타민 C, 원본(totalAscorbicAcid) ")
            val vitaminC: Double? = -1.0,
            @Schema(description = "비타민 D ")
            val vitaminD: Double? = -1.0,
            @Schema(description = "비타민 E ")
            val vitaminE: Double? = -1.0,
            @Schema(description = "비타민 K ")
            val vitaminK: Double? = -1.0,
            @Schema(description = "이소류신 ")
            val isoleucine: Double? = -1.0,
            @Schema(description = "류신 ")
            val leucine: Double? = -1.0,
            @Schema(description = "발린 ")
            val valine: Double? = -1.0,
            @Schema(description = "bcaa ")
            val bcaa: Double? = -1.0,
            @Schema(description = "콜레스테롤 ")
            val cholesterol: Double? = -1.0,
            @Schema(description = "포화 지방산 ")
            val saturatedFattyAcid: Double? = -1.0,
            @Schema(description = "에이코사펜타에노산 ")
            val epa: Double? = -1.0,
            @Schema(description = "도코사헥사에노산 ")
            val dha: Double? = -1.0,
            @Schema(description = "오메가3 지방산 ")
            val omega3FattyAcid: Double? = -1.0,
            @Schema(description = "오메가6 지방산 ")
            val omega6FattyAcid: Double? = -1.0,
            @Schema(description = "트랜스 지방산 ")
            val transFattyAcid: Double? = -1.0,
            @Schema(description = "곡류군")
            val grains: Double? = -1.0,
            @Schema(description = "단백질군")
            val proteins: Double? = -1.0,
            @Schema(description = "채소군")
            val vegetables: Double? = -1.0,
            @Schema(description = "과일군")
            val fruits: Double? = -1.0,
            @Schema(description = "유지군")
            val oils: Double? = -1.0,
            @Schema(description = "당류군")
            val sugars: Double? = -1.0,
            @Schema(description = "유제품군")
            val dairy: Double? = -1.0,
            @Schema(description = "견과류군")
            val nuts: Double? = -1.0,
            @Schema(description = "건강하지 않은 오일군")
            val unhealthyOil: Double? = -1.0
        )
    }

//    init {
//        this.foodInfos.map { foodInfo ->
//            if (foodInfo.nutrient.foodInfoId == 0L && foodInfo.nutrient.customFoodInfoId == 0L) throw CustomException(StatusCode.BAD_REQUEST)
//        }
//    }
}
