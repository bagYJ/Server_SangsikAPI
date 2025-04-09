package com.doinglab.sangsik.api.domains.food.service

import com.doinglab.sangsik.Exception.CustomException
import com.doinglab.sangsik.api.domains.common.dto.CustomDto
import com.doinglab.sangsik.api.domains.food.dto.request.RequestPostFoodCustomDto
import com.doinglab.sangsik.api.domains.food.dto.response.ResponseGetFoodAutocompleteDto
import com.doinglab.sangsik.api.domains.food.dto.response.ResponseGetFoodCustomDto
import com.doinglab.sangsik.api.domains.food.dto.response.ResponseGetFoodCustomIdDto
import com.doinglab.sangsik.api.domains.food.dto.response.ResponseGetFoodIdDto
import com.doinglab.sangsik.api.domains.food.repo.FoodRepo
import com.doinglab.sangsik.enums.StatusCode
import org.apache.commons.text.similarity.JaccardSimilarity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class FoodService(
    private val foodRepo: FoodRepo
) {
    fun doGetFoodAutocomplete(key: String, count: Int): ResponseGetFoodAutocompleteDto =
        key.trim().take(40).split(" ")
            .filter { it.isNotEmpty() }.map { keyword ->
                foodRepo.findFoodNameSearch(keyword)
            }
            .flatten().asSequence()
            .distinctBy { it.foodName + it.manufacturer }
            .sortedByDescending {
                JaccardSimilarity()
                    .apply(key, it.foodName)
                    .plus(JaccardSimilarity().apply(key, it.manufacturer) * 0.1)
            }
            .distinctBy { it.foodName + it.manufacturer }
            .take(if (count > 500) 500 else count).let {
                ResponseGetFoodAutocompleteDto(body = it.toList().map { food ->
                    food.toResponse()
                })
            }

    fun doGetFoodId(id: Long, locale: String): ResponseGetFoodIdDto =
        foodRepo.findFoodInfo(id)?.let { food ->
            food.first?.let { foodInfo ->
                ResponseGetFoodIdDto(foodInfo, food.second, locale)
            } ?: throw CustomException(StatusCode.NOT_FOUND_FOOD)
        } ?: throw CustomException(StatusCode.NOT_FOUND_FOOD)

    fun doGetFoodCustom(userId: Long): ResponseGetFoodCustomDto =
        ResponseGetFoodCustomDto(foodRepo.findFoodInfoCustoms(userId), userId)

    fun doGetFoodCustomId(userId: Long, id: Long): ResponseGetFoodCustomIdDto =
        foodRepo.findFoodInfoCustom(id)?.let { foodInfo ->
            if (foodInfo.deleteFlag == true || foodInfo.userId != userId) throw CustomException(StatusCode.NOT_FOUND_FOOD)

            ResponseGetFoodCustomIdDto(foodInfo)
        } ?: throw CustomException(StatusCode.NOT_FOUND_FOOD)

    fun doPostFoodCustom(userId: Long, request: RequestPostFoodCustomDto): CustomDto =
        foodRepo.saveFoodInfoCustom(userId, request.toDto()).let {
            if (it.foodNumber < 1) throw CustomException(StatusCode.FAIL_INSERT)

            CustomDto()
        }

    fun doPutFoodCustomId(userId: Long, id: Long, request: RequestPostFoodCustomDto): CustomDto =
        foodRepo.findFoodInfoCustom(id)?.let { foodInfo ->
            if (foodInfo.deleteFlag == true || foodInfo.userId != userId) throw CustomException(StatusCode.NOT_FOUND_FOOD)

            foodRepo.saveFoodInfoCustom(userId, foodInfo.toCopy(request)).let {
                if (it.foodNumber < 1) throw CustomException(StatusCode.FAIL_UPDATE)

                CustomDto()
            }
        } ?: throw CustomException(StatusCode.NOT_FOUND_FOOD)

    fun doDeleteFoodCustomId(userId: Long, id: Long): CustomDto =
        foodRepo.findFoodInfoCustom(id)?.let { foodInfo ->
            if (foodInfo.deleteFlag == true || foodInfo.userId != userId) throw CustomException(StatusCode.NOT_FOUND_FOOD)

            foodRepo.saveFoodInfoCustom(userId, foodInfo.copy(deleteFlag = true)).let {
                if (it.foodNumber < 1) throw CustomException(StatusCode.FAIL_DELETE)

                CustomDto()
            }
        } ?: throw CustomException(StatusCode.NOT_FOUND_FOOD)
}
