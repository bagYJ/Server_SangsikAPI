package com.doinglab.sangsik.api.domains.meal.service

import com.doinglab.sangsik.Exception.CustomException
import com.doinglab.sangsik.api.amazon.s3.S3FileUrlGetter
import com.doinglab.sangsik.api.domains.common.dto.CustomDto
import com.doinglab.sangsik.api.domains.meal.dto.request.RequestPostFavoriteDto
import com.doinglab.sangsik.api.domains.meal.dto.response.ResponseGetFavoriteDto
import com.doinglab.sangsik.api.domains.meal.dto.response.ResponseGetFavoriteIdDto
import com.doinglab.sangsik.api.domains.meal.entity.FavoriteEatHistory
import com.doinglab.sangsik.api.domains.meal.entity.FavoriteEatHistoryFood
import com.doinglab.sangsik.api.domains.meal.entity.FavoriteLink
import com.doinglab.sangsik.api.domains.meal.repo.FavoriteRepo
import com.doinglab.sangsik.api.domains.meal.repo.MealRepo
import com.doinglab.sangsik.api.domains.user.entity.User
import com.doinglab.sangsik.enums.StatusCode
import org.springframework.transaction.annotation.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class FavoriteService(
    private val favoriteRepo: FavoriteRepo,
    private val mealRepo: MealRepo,
    private val s3EatHistoryImageFileUrlGetter: S3FileUrlGetter
) {
    fun doGetFavorite(userId: Long, lastId: Long?, limit: Long): ResponseGetFavoriteDto =
        ResponseGetFavoriteDto.create(favoriteRepo.findFavoriteEatHistories(userId, lastId, limit))

    fun doGetFavoriteId(user: User.Dto, id: Long): ResponseGetFavoriteIdDto =
        favoriteRepo.findFavoriteEatHistory(id)?.let { favoriteEatHistory ->
            if (favoriteEatHistory.userId != user.id) throw CustomException(StatusCode.NOT_FOUND_FAVORITE)

            favoriteRepo.findFavoriteEatHistoryFood(favoriteEatHistory.id)?.let { favoriteEatHistoryFood ->
                if (favoriteEatHistoryFood.userId != user.id) throw CustomException(StatusCode.NOT_FOUND_FAVORITE)

                ResponseGetFavoriteIdDto(
                    favoriteEatHistory,
                    favoriteEatHistoryFood.foodInfo,
                    user.customcalorie.toDouble(),
                    s3EatHistoryImageFileUrlGetter
                )
            } ?: throw CustomException(StatusCode.NOT_FOUND_FAVORITE)
        } ?: throw CustomException(StatusCode.NOT_FOUND_FAVORITE)

    fun doDeleteFavoriteId(userId: Long, id: Long): CustomDto =
        favoriteRepo.findFavoriteEatHistory(id)?.let { favoriteEatHistory ->
            if (favoriteEatHistory.userId != userId) throw CustomException(StatusCode.NOT_FOUND_FAVORITE)

            favoriteRepo.deleteFavoriteEatHistory(favoriteEatHistory.id).let {
                CustomDto()
            }
        } ?: throw CustomException(StatusCode.NOT_FOUND_FAVORITE)

    fun doPostFavorite(userId: Long, request: RequestPostFavoriteDto): CustomDto {
        if (favoriteRepo.findFavoriteEatHistory(userId, request.eatHistoryId) != null) throw CustomException(StatusCode.ALREADY_FAVORITE)

        return mealRepo.findEatHistory(userId, request.eatHistoryId)?.let { meal ->
            mealRepo.findEatHistoryFoods(meal.userId, listOf(meal.id))?.let { foods ->
                favoriteRepo.saveFavoriteEatHistory(
                    FavoriteEatHistory.Dto(meal, request.title, foods.filter { it.energy != null && it.energy > -1 }.sumOf { it.energy!! }.let {
                        if (it >= 0) it else -1.0
                    })
                )?.let { favoriteEatHistory ->
                    favoriteRepo.saveFavoriteEatHistoryFood(
                        FavoriteEatHistoryFood.Dto(
                            meal.userId,
                            favoriteEatHistory.id,
                            foods
                        )
                    )?.let {
                        favoriteRepo.saveFavoriteLink(
                            FavoriteLink.Dto(userId, favoriteEatHistory.id, request.eatHistoryId)
                        )?.let {
                            CustomDto()
                        } ?: throw CustomException(StatusCode.FAIL_INSERT)
                    } ?: throw CustomException(StatusCode.FAIL_INSERT)
                } ?: throw CustomException(StatusCode.FAIL_INSERT)
            } ?: throw CustomException(StatusCode.NOT_FOUND_FOOD)
        } ?: throw CustomException(StatusCode.NOT_FOUND_MEAL)
    }

    fun findFavoriteEatHistories(userId: Long, eatHistoryIds: List<Long>): List<FavoriteEatHistory.Dto>? =
        favoriteRepo.findFavoriteEatHistories(userId, eatHistoryIds)
}
