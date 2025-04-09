package com.doinglab.sangsik.api.domains.food.entity

import com.doinglab.sangsik.api.domains.food.dto.response.ResponseGetFoodAutocompleteDto
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name = "FoodNameSearch")
class FoodNameSearch(
    @Id
    val seq: String,
    val foodNumber: Long,
    val foodNameType: Int,
    val foodName: String,
    val weight: String,
    val manufacturer: String?,
    val predictKey: String?,
    val country: String
) {
    data class Dto(
        val seq: String,
        val foodNumber: Long,
        val foodNameType: Int,
        val foodName: String,
        val weight: String,
        val manufacturer: String?,
        val predictKey: String?,
        val country: String
    ) {
        fun toResponse() = ResponseGetFoodAutocompleteDto.GetFoodAutocompleteBody(
            id = foodNumber,
            foodName = if (weight.isNotEmpty() && manufacturer?.isNotEmpty() == true) "$foodName $weight" else foodName,
            manufacturer = if (manufacturer == "-") "" else manufacturer,
            predictKey = if (predictKey == "-") "" else predictKey,
        )
    }

    fun toDto() = Dto(
        seq = seq,
        foodNumber = foodNumber,
        foodNameType = foodNameType,
        foodName = foodName,
        weight = weight,
        manufacturer = if (manufacturer == "-") "" else manufacturer,
        predictKey = if (predictKey == "-") "" else predictKey,
        country = country
    )
}
