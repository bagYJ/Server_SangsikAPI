package com.doinglab.sangsik.enums

import com.fasterxml.jackson.annotation.JsonCreator

enum class MealTime(val desc: String, val minute: Long, val rangeNum: Long) {
    BEFORE("식전", -5, -2),
    AFTER_1("식후 1시간", 60, 2),
    AFTER("식후 2시간", 120, 2);

    companion object {
        @JvmStatic
        @JsonCreator
        fun parsing(inputValue: String): MealTime? =
            inputValue.let {
                when {
                    it.isBlank() -> null
                    else -> entries.first { entry -> entry.name == inputValue }
                }
            }
    }
}
