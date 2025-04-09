package com.doinglab.sangsik.api.domains.meal.service

import com.doinglab.sangsik.Exception.CustomException
import com.doinglab.sangsik.api.amazon.s3.S3FileUrlGetter
import com.doinglab.sangsik.api.domains.common.dto.CustomDto
import com.doinglab.sangsik.api.domains.meal.dto.request.RequestPostRepeatDto
import com.doinglab.sangsik.api.domains.meal.dto.response.ResponseGetRepeatDto
import com.doinglab.sangsik.api.domains.meal.dto.response.ResponseGetRepeatIdDto
import com.doinglab.sangsik.api.domains.meal.entity.*
import com.doinglab.sangsik.api.domains.meal.repo.MealRepo
import com.doinglab.sangsik.api.domains.meal.repo.RepeatRepo
import com.doinglab.sangsik.api.domains.user.entity.User
import com.doinglab.sangsik.enums.StatusCode
import com.doinglab.sangsik.utils.getLogger
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime

@Service
@Transactional
class RepeatService(
    private val repeatRepo: RepeatRepo,
    private val mealRepo: MealRepo,
    private val s3EatHistoryImageFileUrlGetter: S3FileUrlGetter
) {
    private val logger = getLogger()

    fun doGetRepeat(userId: Long, lastId: Long?, limit: Long): ResponseGetRepeatDto =
        ResponseGetRepeatDto.create(repeatRepo.findRepeatEatHistories(userId, lastId, limit))

    fun doGetRepeatId(user: User.Dto, id: Long): ResponseGetRepeatIdDto =
        repeatRepo.findRepeatEatHistory(id)?.let { repeatEatHistory ->
            if (repeatEatHistory.userId != user.id) throw CustomException(StatusCode.NOT_FOUND_REPEAT)

            repeatRepo.findRepeatEatHistoryFood(repeatEatHistory.id)?.let { repeatEatHistoryFood ->
                if (repeatEatHistoryFood.userId != user.id) throw CustomException(StatusCode.NOT_FOUND_REPEAT)

                ResponseGetRepeatIdDto(repeatEatHistory, repeatEatHistoryFood.foodInfo, user.customcalorie.toDouble(), s3EatHistoryImageFileUrlGetter)
            } ?: throw CustomException(StatusCode.NOT_FOUND_REPEAT)
        } ?: throw CustomException(StatusCode.NOT_FOUND_REPEAT)

    fun doDeleteRepeatId(userId: Long, id: Long): CustomDto =
        repeatRepo.findRepeatEatHistory(id)?.let { repeatEatHistory ->
            if (repeatEatHistory.userId != userId) throw CustomException(StatusCode.NOT_FOUND_REPEAT)

            repeatRepo.deleteRepeatEatHistory(repeatEatHistory.id).let {
                CustomDto()
            }
        } ?: throw CustomException(StatusCode.NOT_FOUND_REPEAT)

    fun doPostRepeat(userId: Long, request: RequestPostRepeatDto): CustomDto {
        if (repeatRepo.findRepeatEatHistory(userId, request.eatHistoryId) != null) throw CustomException(StatusCode.ALREADY_REPEAT)

        return mealRepo.findEatHistory(userId, request.eatHistoryId)?.let { meal ->
            mealRepo.findEatHistoryFoods(meal.userId, listOf(meal.id))?.let { foods ->
                repeatRepo.saveRepeatEatHistory(
                    RepeatEatHistory.Dto(meal, request, foods.filter { it.energy != null && it.energy > -1 }.sumOf { it.energy!! }.let {
                        if (it >= 0) it else -1.0
                    }
                ))?.let { repeatEatHistoryFood ->
                    repeatRepo.saveRepeatEatHistoryFood(
                        RepeatEatHistoryFood.Dto(meal.userId, repeatEatHistoryFood.id, foods)
                    )?.let {
                        repeatRepo.saveRepeatLink(
                            RepeatLink.Dto(userId, repeatEatHistoryFood.id, request.eatHistoryId)
                        ) ?: throw CustomException(StatusCode.FAIL_INSERT)

                        if (request.startDate.isBefore(LocalDate.now())) {
                            var startDate = request.startDate
                            val endDate = request.endDate?.let { endDate ->
                                when {
                                    endDate < LocalDate.now() -> endDate
                                    else -> LocalDate.now()
                                }
                            } ?: LocalDate.now()
                            val eatHistories = mutableListOf<EatHistory.Dto>()
                            val eatHistoryFoods = mutableListOf<EatHistoryFood.Dto>()
                            val multiPredictPositions = mutableListOf<MultiPredictPositions.Dto>()

                            while (startDate != endDate) {
                                if (startDate.dayOfWeek in request.days.map { it.week }) {
                                    eatHistories.add(meal.copy(request.eatType, LocalDateTime.of(startDate, request.repeatTime)))
                                }

                                startDate = startDate.plusDays(1)
                            }

                            mealRepo.saveAllEatHistory(eatHistories)?.let { saveEatHistories ->
                                if (eatHistories.size != saveEatHistories.size) throw CustomException(StatusCode.FAIL_INSERT)

                                saveEatHistories.map { eathistory ->
                                    foods.map { food ->
                                        eatHistoryFoods.add(food.copy(eathistory.id))
                                        multiPredictPositions.add(food.toMultiPredictPosition(eathistory.id))
                                    }
                                }
                                mealRepo.saveAllEatHistoryFood(eatHistoryFoods)?.let {
                                    if (eatHistoryFoods.size != it.size) throw CustomException(StatusCode.FAIL_INSERT)
                                }
                                mealRepo.saveAllMultiPredictPositions(multiPredictPositions)?.let {
                                    if (multiPredictPositions.size != it.size) throw CustomException(StatusCode.FAIL_INSERT)
                                }
                            }
                        }

                        CustomDto()
                    } ?: throw CustomException(StatusCode.FAIL_INSERT)
                } ?: throw CustomException(StatusCode.FAIL_INSERT)
            } ?: throw CustomException(StatusCode.NOT_FOUND_FOOD)
        } ?: throw CustomException(StatusCode.NOT_FOUND_MEAL)
    }

    fun findRepeatEatHistories(userId: Long, eatHistoryIds: List<Long>): List<RepeatEatHistory.Dto>? =
        repeatRepo.findRepeatEatHistories(userId, eatHistoryIds)

//    @Scheduled(cron = "0 * * * * *")
//    fun doPostRepeatEatHistory() {
//        repeatRepo.findRepeatEatHistories(LocalTime.now(), LocalDate.now())?.map {
//            println(it)
//        }
//    }
}
