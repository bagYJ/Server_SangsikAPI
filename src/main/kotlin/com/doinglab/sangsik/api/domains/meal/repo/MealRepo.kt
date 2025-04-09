package com.doinglab.sangsik.api.domains.meal.repo

import com.doinglab.sangsik.api.domains.food.entity.FoodInfoR2
import com.doinglab.sangsik.api.domains.meal.entity.EatHistory
import com.doinglab.sangsik.api.domains.meal.entity.EatHistoryFood
import com.doinglab.sangsik.api.domains.meal.entity.MultiPredictPositions
import com.doinglab.sangsik.define.CoreDefine.Companion.EAT_HISTORY
import com.doinglab.sangsik.define.CoreDefine.Companion.EAT_HISTORY_COMMENT
import com.doinglab.sangsik.define.CoreDefine.Companion.EAT_HISTORY_FOOD
import com.doinglab.sangsik.define.CoreDefine.Companion.FOOD_INFO_R2
import com.doinglab.sangsik.define.CoreDefine.Companion.MULTI_PREDICT_POSITIONS
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.time.LocalTime

@Repository
class MealRepo(
    @Qualifier("dietJpaQueryFactory") private val jpaQueryFactory: JPAQueryFactory,
    private val eatHistoryJpaRepo: EatHistoryJpaRepo,
    private val eatHistoryFoodJpaRepo: EatHistoryFoodJpaRepo,
    private val eatHistoryCommentJpaRepo: EatHistoryCommentJpaRepo,
    private val multiPredictPositionsJpaRepo: MultiPredictPositionsJpaRepo
) {
    fun findMeals(userId: Long, date: LocalDate): List<Triple<EatHistory.Dto?, EatHistoryFood.Dto?, FoodInfoR2.Dto?>> =
        jpaQueryFactory.select(EAT_HISTORY, EAT_HISTORY_FOOD, FOOD_INFO_R2)
            .from(EAT_HISTORY)
            .leftJoin(EAT_HISTORY_FOOD).on(EAT_HISTORY.id.eq(EAT_HISTORY_FOOD.eatHistoryId))
            .leftJoin(FOOD_INFO_R2).on(EAT_HISTORY_FOOD.foodInfoId.eq(FOOD_INFO_R2.foodNumber))
            .where(
                EAT_HISTORY.userId.eq(userId)
                    .and(EAT_HISTORY.date.between(date.atStartOfDay(), date.atTime(LocalTime.MAX)))
            ).fetch().map { tuple ->
                Triple(
                    tuple.get(EAT_HISTORY)?.toDto(),
                    tuple.get(EAT_HISTORY_FOOD)?.toDto(),
                    tuple.get(FOOD_INFO_R2)?.toDto()
                )
            }

    fun findEatHistory(userId: Long, id: Long): EatHistory.Dto? =
        jpaQueryFactory.select(EAT_HISTORY)
            .from(EAT_HISTORY)
            .where(
                EAT_HISTORY.userId.eq(userId)
                    .and(EAT_HISTORY.id.eq(id))
            ).fetchFirst()?.toDto()

    fun findEatHistory(userId: Long, date: LocalDate): List<EatHistory.Dto>? =
        jpaQueryFactory.select(EAT_HISTORY)
            .from(EAT_HISTORY)
            .where(EAT_HISTORY.userId.eq(userId)
                .and(EAT_HISTORY.date.between(date.atStartOfDay(), date.atTime(23, 59, 59))))
            .fetch()?.map { it.toDto() }

    fun findEatHistoryFoods(userId: Long, eatHistoryIds: List<Long>): List<EatHistoryFood.Dto>? =
        jpaQueryFactory.selectFrom(EAT_HISTORY_FOOD)
            .where(
                EAT_HISTORY_FOOD.eatHistoryId.`in`(eatHistoryIds)
                    .and(EAT_HISTORY_FOOD.userId.eq(userId))
            ).fetch().map { it.toDto() }

    fun deleteEatHistory(id: Long) =
        jpaQueryFactory.delete(EAT_HISTORY).where(EAT_HISTORY.id.eq(id)).execute()

    fun deleteEatHistoryFood(id: Long) =
        jpaQueryFactory.delete(EAT_HISTORY_FOOD).where(EAT_HISTORY_FOOD.eatHistoryId.eq(id)).execute()

    fun deleteEatHistoryComment(id: Long) =
        jpaQueryFactory.delete(EAT_HISTORY_COMMENT).where(EAT_HISTORY_COMMENT.eatHistoryId.eq(id)).execute()

    fun deleteMultiPredictPositions(id: Long) =
        jpaQueryFactory.delete(MULTI_PREDICT_POSITIONS).where(MULTI_PREDICT_POSITIONS.eatHistoryId.eq(id)).execute()

    fun saveEatHistory(eatHistory: EatHistory.Dto): EatHistory.Dto? =
        eatHistoryJpaRepo.save(eatHistory.toEntity()).toDto()

    fun saveAllEatHistory(eatHistories: List<EatHistory.Dto>): List<EatHistory.Dto>? =
        eatHistoryJpaRepo.saveAll(eatHistories.map { it.toEntity() }).map { it.toDto() }

    fun saveAllEatHistoryFood(eatHistoryFoods: List<EatHistoryFood.Dto>): List<EatHistoryFood.Dto>? =
        eatHistoryFoodJpaRepo.saveAll(eatHistoryFoods.map { it.toEntity() }).map { it.toDto() }

    fun saveAllMultiPredictPositions(multiPredictPositions: List<MultiPredictPositions.Dto>): List<MultiPredictPositions.Dto>? =
        multiPredictPositionsJpaRepo.saveAll(multiPredictPositions.map { it.toEntity() }).map { it.toDto() }
}
