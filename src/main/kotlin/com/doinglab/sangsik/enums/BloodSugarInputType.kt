package com.doinglab.sangsik.enums

enum class BloodSugarInputType(val desc: String, val mealType: MealType, val mealTime: MealTime?, val eatType: EatType?) {
    BS10_BEFORE_BREAKFAST("아침식전", MealType.MORNING, MealTime.BEFORE, EatType.BREAKFAST),
    BS14_AFTER_BREAKFAST("아침식후 1시간", MealType.MORNING, MealTime.AFTER_1, EatType.BREAKFAST),
    BS15_AFTER_BREAKFAST("아침식후 2시간", MealType.MORNING, MealTime.AFTER, EatType.BREAKFAST),
    BS16_BEFORE_MORNING_SNACK("오전간식전", MealType.MORNINGSNACK, MealTime.BEFORE, EatType.MORNINGSNACK),
    BS18_AFTER_MORNING_SNACK("오전간식후 1시간", MealType.MORNINGSNACK, MealTime.AFTER_1, EatType.MORNINGSNACK),
    BS19_AFTER_MORNING_SNACK("오전간식후 2시간", MealType.MORNINGSNACK, MealTime.AFTER, EatType.MORNINGSNACK),
    BS20_BEFORE_LUNCH("점심식전", MealType.AFTERNOON, MealTime.BEFORE, EatType.LUNCH),
    BS24_AFTER_LUNCH("점심식후 1시간", MealType.AFTERNOON, MealTime.AFTER_1, EatType.LUNCH),
    BS25_AFTER_LUNCH("점심식후 2시간", MealType.AFTERNOON, MealTime.AFTER, EatType.LUNCH),
    BS26_BEFORE_AFTERNOON_SNACK("오후간식전", MealType.AFTERNOONSNACK, MealTime.BEFORE, EatType.AFTERNOONSNACK),
    BS28_AFTER_AFTERNOON_SNACK("오후간식후 1시간", MealType.AFTERNOONSNACK, MealTime.AFTER_1, EatType.AFTERNOONSNACK),
    BS29_AFTER_AFTERNOON_SNACK("오후간식후 2시간", MealType.AFTERNOONSNACK, MealTime.AFTER, EatType.AFTERNOONSNACK),
    BS30_BEFORE_DINNER("저녁식전", MealType.DINNER, MealTime.BEFORE, EatType.DINNER),
    BS34_AFTER_DINNER("저녁식후 1시간", MealType.DINNER, MealTime.AFTER_1, EatType.DINNER),
    BS35_AFTER_DINNER("저녁식후 2시간", MealType.DINNER, MealTime.AFTER, EatType.DINNER),
    BS36_BEFORE_MIDNIGHT_SNACK("야식전", MealType.NIGHTSNACK, MealTime.BEFORE, EatType.NIGHTSNACK),
    BS38_AFTER_MIDNIGHT_SNACK("야식후 1시간", MealType.NIGHTSNACK, MealTime.AFTER_1, EatType.NIGHTSNACK),
    BS39_AFTER_MIDNIGHT_SNACK("야식후 2시간", MealType.NIGHTSNACK, MealTime.AFTER, EatType.NIGHTSNACK),
    BS90_BEFORE_BED("취침전", MealType.BEFOREBED, null, null);

    companion object {
        fun findByMealAndTimes(mealType: MealType, times: MealTime?): BloodSugarInputType? =
            entries.firstOrNull { type ->
                type.mealType == mealType && times == type.mealTime
            }
    }
}
