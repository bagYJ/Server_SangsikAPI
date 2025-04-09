package com.doinglab.sangsik.api.domains.meal.service

import com.doinglab.sangsik.Exception.CustomException
import com.doinglab.sangsik.api.aes.dto.AesDto
import com.doinglab.sangsik.api.amazon.s3.AmazonS3Service
import com.doinglab.sangsik.api.amazon.s3.S3FileUploader
import com.doinglab.sangsik.api.amazon.s3.S3FileUrlGetter
import com.doinglab.sangsik.api.domains.cgm.service.CgmService
import com.doinglab.sangsik.api.domains.common.dto.CustomDto
import com.doinglab.sangsik.api.domains.food.entity.FoodInfoR2
import com.doinglab.sangsik.api.domains.food.repo.FoodRepo
import com.doinglab.sangsik.api.domains.meal.dto.request.RequestPostMealDto
import com.doinglab.sangsik.api.domains.meal.dto.response.ResponseGetMealDto
import com.doinglab.sangsik.api.domains.meal.dto.response.ResponseGetMealIdDto
import com.doinglab.sangsik.api.domains.meal.entity.EatHistory
import com.doinglab.sangsik.api.domains.meal.entity.EatHistoryFood
import com.doinglab.sangsik.api.domains.meal.entity.FavoriteLink
import com.doinglab.sangsik.api.domains.meal.entity.MultiPredictPositions
import com.doinglab.sangsik.api.domains.meal.repo.FavoriteRepo
import com.doinglab.sangsik.api.domains.meal.repo.MealRepo
import com.doinglab.sangsik.api.domains.meal.repo.RepeatRepo
import com.doinglab.sangsik.api.domains.user.entity.User
import com.doinglab.sangsik.api.domains.user.repo.UserRepo
import com.doinglab.sangsik.enums.MealTime
import com.doinglab.sangsik.enums.Nutrient
import com.doinglab.sangsik.enums.StatusCode
import com.doinglab.sangsik.utils.between
import com.doinglab.sangsik.utils.getLogger
import com.doinglab.sangsik.utils.toJsonObject
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Service
@Transactional
class MealService(
    private val mealRepo: MealRepo,
    private val favoriteRepo: FavoriteRepo,
    private val repeatRepo: RepeatRepo,
    private val userRepo: UserRepo,
    private val amazonS3Service: AmazonS3Service,
    private val cgmService: CgmService,
    private val s3EatHistoryImageFileUrlGetter: S3FileUrlGetter,
    private val s3EatHistoryImageFileUploader: S3FileUploader,
    private val foodRepo: FoodRepo,
    private val aesProperties: AesDto
) {
    private val logger = getLogger()

    @Transactional(readOnly = true)
    fun doGetMeal(user: User.Dto, date: LocalDate, locale: String?, nutrients: List<Nutrient>?): ResponseGetMealDto {
        val eatHistories = mutableListOf<EatHistory.Dto>()
        val eatHistoryFoods = mutableListOf<EatHistoryFood.Dto>()
        val foodInfoR2 = mutableListOf<FoodInfoR2.Dto>()
        mealRepo.findMeals(user.id, date).map { meal ->
            meal.first?.let { eatHistories.add(it) }
            meal.second?.let { eatHistoryFoods.add(it) }
            meal.third?.let { foodInfoR2.add(it) }
        }
        val cgmUserBloodSugars = cgmService.findUserCgmBloodGlucose(user, date.atStartOfDay(), date.atTime(23, 59, 59).plusMinutes(121))
        val bloodSugars = userRepo.findUserBloodSugar(user.id, date)
        val userBloodSugars = eatHistories.sortedBy { it.eatType.value }.groupBy { it.eatType }.values.map { it.first() }.map { eatHistory ->
            MealTime.entries.map { mealTime ->
                cgmUserBloodSugars?.firstOrNull { it.eventAt.between(eatHistory.date.plusMinutes(mealTime.minute), eatHistory.date.plusMinutes(mealTime.minute).plusMinutes(5)) }?.let {
                    ResponseGetMealDto.GetMealBody.BloodSugar(it, eatHistory, mealTime, eatHistory.eatType.mealType.findBloodSugarInputType(mealTime)!!, aesProperties.aesKey)
                } ?: bloodSugars?.filter { bloodSugar ->
                    bloodSugar.datetime < (cgmUserBloodSugars?.minOfOrNull { it.eventAt } ?: LocalDateTime.of(date, LocalTime.of(23, 59, 59)))
                }?.firstOrNull {
                    it.inputType == eatHistory.eatType.mealType.findBloodSugarInputType(mealTime)
                }?.let { bloodSugar ->
                    ResponseGetMealDto.GetMealBody.BloodSugar(bloodSugar, aesProperties.aesKey)
                }
            }
        }.flatten().mapNotNull { it }

        return ResponseGetMealDto(
            user,
            eatHistories.takeIf { it.isNotEmpty() },
            eatHistoryFoods.takeIf { it.isNotEmpty() },
            userBloodSugars,
            s3EatHistoryImageFileUrlGetter,
            date, cgmService.findUserCgmTokens(user.id)?.any { date.between(it.sdt, it.edt) } == true || cgmUserBloodSugars?.isNotEmpty() == true,
        ).toApply(Nutrient.summary(nutrients), locale)
    }

    fun doDeleteMealId(userId: Long, eatHistoryId: Long) =
        findEatHistory(userId, eatHistoryId)?.let { meal ->
            try {
                mealRepo.deleteEatHistory(meal.id)
                mealRepo.deleteEatHistoryFood(meal.id)
                mealRepo.deleteEatHistoryComment(meal.id)
            } catch (e: Exception) {
                logger.error("Error deleting meal", e)
                throw CustomException(StatusCode.FAIL_DELETE)
            }

            CustomDto()
        } ?: throw CustomException(StatusCode.NOT_FOUND_MEAL)

    fun findEatHistory(userId: Long, eatHistoryId: Long): EatHistory.Dto? =
        mealRepo.findEatHistory(userId, eatHistoryId)

    fun doPostMeal(userId: Long, request: String, file: MultipartFile?, thumbFile: MultipartFile?): CustomDto =
        request.toJsonObject(RequestPostMealDto::class.java)?.let { json ->
            if (file?.isEmpty == false) {
                json.imagePath = amazonS3Service.uploadEatHistoryImageFileAndGetName(file)
            }
            if (thumbFile?.isEmpty == false) {
                json.imageThumbPath = amazonS3Service.uploadEatHistoryImageFileAndGetName(thumbFile, "thumb")
            }
            mealRepo.saveEatHistory(EatHistory.Dto(userId, json))?.let { eatHistory ->
                val foods = getFoodInfo(userId, eatHistory.id, json)
                mealRepo.saveAllEatHistoryFood(foods.first)
                mealRepo.saveAllMultiPredictPositions(foods.second)

                json.favoriteLinkId?.let { favoriteLinkId ->
                    if (favoriteLinkId > 0) {
                        favoriteRepo.findFavoriteEatHistory(favoriteLinkId)?.let { favoriteEatHistory ->
                            favoriteRepo.saveFavoriteLink(
                                FavoriteLink.Dto(userId, favoriteEatHistory.id, eatHistory.id)
                            )
                        } ?: throw CustomException(StatusCode.NOT_FOUND_FAVORITE)
                    }
                }

                CustomDto()
            } ?: throw CustomException(StatusCode.FAIL_INSERT)
        } ?: throw CustomException(StatusCode.BAD_REQUEST)

    fun doGetMealId(userId: Long, eatHistoryId: Long): ResponseGetMealIdDto =
        mealRepo.findEatHistory(userId, eatHistoryId)?.let { meal ->
            mealRepo.findEatHistoryFoods(userId, listOf(meal.id))?.let { foods ->
                ResponseGetMealIdDto(meal, foods, s3EatHistoryImageFileUrlGetter)
            } ?: throw CustomException(StatusCode.NOT_FOUND_FOOD)
        } ?: throw CustomException(StatusCode.NOT_FOUND_MEAL)

//    todo 즐겨찾기 API 추후 추가
    fun doPutMeal(userId: Long, eatHistoryId: Long, request: RequestPostMealDto): CustomDto =
        mealRepo.findEatHistory(userId, eatHistoryId)?.let { defaultEatHistory ->
            mealRepo.saveEatHistory(defaultEatHistory.copy(request))?.let { eatHistory ->
                mealRepo.deleteEatHistoryFood(eatHistory.id)
                mealRepo.deleteMultiPredictPositions(eatHistory.id)

                val foods = getFoodInfo(userId, eatHistory.id, request)
                mealRepo.saveAllEatHistoryFood(foods.first)
                mealRepo.saveAllMultiPredictPositions(foods.second)

                CustomDto()
            } ?: throw CustomException(StatusCode.FAIL_UPDATE)
        } ?: throw CustomException(StatusCode.NOT_FOUND_MEAL)

    fun findEatHistory(userId: Long, date: LocalDate): List<EatHistory.Dto>? =
        mealRepo.findEatHistory(userId, date)

    fun findEatHistoryFoods(userId: Long, eatHistoryIds: List<Long>): List<EatHistoryFood.Dto>? =
        mealRepo.findEatHistoryFoods(userId, eatHistoryIds)

    fun saveAllEatHistory(eatHistories: List<EatHistory.Dto>): List<EatHistory.Dto>? =
        mealRepo.saveAllEatHistory(eatHistories)

    fun saveAllEatHistoryFood(eatHistoryFoods: List<EatHistoryFood.Dto>): List<EatHistoryFood.Dto>? =
        mealRepo.saveAllEatHistoryFood(eatHistoryFoods)

    fun saveAllMultiPredictPositions(multiPredictPositions: List<MultiPredictPositions.Dto>): List<MultiPredictPositions.Dto>? =
        mealRepo.saveAllMultiPredictPositions(multiPredictPositions)

    private fun getFoodInfo(userId: Long, eatHistoryId: Long, request: RequestPostMealDto): Pair<List<EatHistoryFood.Dto>, List<MultiPredictPositions.Dto>> =
        request.foodInfos.let { foodInfos ->
            foodRepo.findFoodInfoCustoms(userId, foodInfos.mapNotNull { it.nutrient.foodInfoId })?.map { foodInfo ->
                foodInfos.firstOrNull { it.nutrient.foodInfoId == foodInfo.foodNumber && it.nutrient.foodName == foodInfo.koName }?.let {
                    it.nutrient.customFoodInfoId = it.nutrient.foodInfoId
                    it.nutrient.foodInfoId = 0L
                }
            }

            val eatHistoryFoods = mutableListOf<EatHistoryFood.Dto>()
            val multiPredictPositions = mutableListOf<MultiPredictPositions.Dto>()

            foodInfos.map { foodInfo ->
                eatHistoryFoods.add(EatHistoryFood.Dto(userId, eatHistoryId, foodInfo))
                multiPredictPositions.add(MultiPredictPositions.Dto(eatHistoryId, foodInfo))
            }

            Pair(eatHistoryFoods, multiPredictPositions)
        }
}
