package com.doinglab.sangsik.enums

enum class Nutrient(
    val code: String,
    val nameKo: String,
    val nameEn: String
) {
    CARBOHYDRATE("carbohydrate", "탄수화물", "Carb"),
    PROTEIN("protein", "단백질", "Protein"),
    FAT("fat", "지방", "Fat"),
    TOTALSUGARS("totalSugars", "당류", "Sugars"),
    TOTALDIETARYFIBER("totalDietaryFiber", "식이섬유", "Fiber"),
    CALCIUM("calcium", "칼슘", "Calcium"),
    SODIUM("sodium", "나트륨", "Sodium"),
    CHOLESTEROL("cholesterol", "콜레스테롤", "Cholesterol"),
    SATURATEDFATTYACID("saturatedFattyAcid", "포화 지방산", "Saturated Fat"),
    TRANSFATTYACID("transFattyAcid", "트랜스 지방산", "Trans fat"),;

    companion object {
        val default = listOf(CARBOHYDRATE, PROTEIN, FAT, TOTALDIETARYFIBER)

        fun summary(nutrients: List<Nutrient>?): List<Nutrient> =
            (nutrients?.union(default) ?: default).map { it }.subList(0, 4)
    }
}
