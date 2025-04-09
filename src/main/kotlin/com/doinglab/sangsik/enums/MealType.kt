package com.doinglab.sangsik.enums

enum class MealType(val desc: String, val order: Int) {
    MORNING("아침 식사", 1),
    AFTERNOON("점심 식사", 3),
    DINNER("저녁 식사", 5),
    BEFOREBED("취침 전", 7),
    MORNINGSNACK("오전 간식", 2),
    AFTERNOONSNACK("오후 간식", 4),
    NIGHTSNACK("야식", 6);

    fun findBloodSugarInputType(mealTime: MealTime?): BloodSugarInputType? =
        BloodSugarInputType.findByMealAndTimes(this, mealTime)

    companion object {
        val require = listOf(MORNING, AFTERNOON, DINNER, BEFOREBED)
    }
}
