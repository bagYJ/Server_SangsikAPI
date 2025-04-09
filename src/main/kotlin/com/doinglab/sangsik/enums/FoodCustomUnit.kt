package com.doinglab.sangsik.enums

import com.fasterxml.jackson.annotation.JsonValue

enum class FoodCustomUnit(@get:JsonValue val code: String) {
    G("g"), ML("ml")
}
