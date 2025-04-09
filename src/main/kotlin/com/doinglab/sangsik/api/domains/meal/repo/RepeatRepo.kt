package com.doinglab.sangsik.api.domains.meal.repo

import com.doinglab.sangsik.api.domains.meal.entity.RepeatEatHistory
import com.doinglab.sangsik.api.domains.meal.entity.RepeatEatHistoryFood
import com.doinglab.sangsik.api.domains.meal.entity.RepeatLink
import com.doinglab.sangsik.define.CoreDefine.Companion.CHAT_MESSAGE
import com.doinglab.sangsik.define.CoreDefine.Companion.REPEAT_EAT_HISTORY
import com.doinglab.sangsik.define.CoreDefine.Companion.REPEAT_EAT_HISTORY_FOOD
import com.doinglab.sangsik.define.CoreDefine.Companion.REPEAT_LINK
import com.doinglab.sangsik.enums.Week
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.time.LocalTime

@Repository
class RepeatRepo(
    @Qualifier("dietJpaQueryFactory") private val jpaQueryFactory: JPAQueryFactory,
    private val repeatEatHistoryJpaRepo: RepeatEatHistoryJpaRepo,
    private val repeatEatHistoryFoodJpaRepo: RepeatEatHistoryFoodJpaRepo,
    private val repeatLinkJpaRepo: RepeatLinkJpaRepo
) {
    fun findRepeatEatHistories(userId: Long, lastId: Long?, limit: Long): List<RepeatEatHistory.Dto>? =
        jpaQueryFactory.selectFrom(REPEAT_EAT_HISTORY)
            .where(REPEAT_EAT_HISTORY.userId.eq(userId)).apply {
                lastId?.let {
                    where(CHAT_MESSAGE.id.lt(it))
                }
            }
            .orderBy(REPEAT_EAT_HISTORY.id.desc())
            .limit(limit).fetch().map { it.toDto() }

    fun findRepeatEatHistories(userId: Long, eatHistoryIds: List<Long>): List<RepeatEatHistory.Dto>? =
        jpaQueryFactory.selectFrom(REPEAT_EAT_HISTORY)
            .where(
                REPEAT_EAT_HISTORY.userId.eq(userId)
                    .and(REPEAT_EAT_HISTORY.eatHistoryId.`in`(eatHistoryIds))
            )
            .fetch().map { it.toDto() }

    fun findRepeatEatHistories(time: LocalTime, date: LocalDate): List<RepeatEatHistory.Dto>? =
        jpaQueryFactory.selectFrom(REPEAT_EAT_HISTORY)
            .where(
                REPEAT_EAT_HISTORY.days.contains(Week.entries.first { it.week == date.dayOfWeek }.toString())
                    .and(
                        REPEAT_EAT_HISTORY.repeatTime.between(
                            time.withMinute(0).withSecond(0),
                            time.withMinute(59).withSecond(59)
                        )
                    )
                    .and(
                        REPEAT_EAT_HISTORY.startDate.loe(date)
                            .and(
                                REPEAT_EAT_HISTORY.endDate.isNull
                                    .or(
                                        REPEAT_EAT_HISTORY.endDate.isNotNull
                                            .and(REPEAT_EAT_HISTORY.endDate.goe(date))
                                    )
                            )
                    )
            )
            .fetch().map { it.toDto() }

    fun findRepeatEatHistory(id: Long): RepeatEatHistory.Dto? =
        jpaQueryFactory.selectFrom(REPEAT_EAT_HISTORY)
            .where(REPEAT_EAT_HISTORY.id.eq(id)).fetchOne()?.toDto()

    fun findRepeatEatHistory(userId: Long, id: Long): RepeatEatHistory.Dto? =
        jpaQueryFactory.selectFrom(REPEAT_EAT_HISTORY)
            .where(
                REPEAT_EAT_HISTORY.eatHistoryId.eq(id)
                    .and(REPEAT_EAT_HISTORY.userId.eq(userId))
            ).fetchOne()?.toDto()

    fun findRepeatEatHistoryFood(id: Long): RepeatEatHistoryFood.Dto? =
        jpaQueryFactory.selectFrom(REPEAT_EAT_HISTORY_FOOD)
            .where(REPEAT_EAT_HISTORY_FOOD.repeatId.eq(id))
            .fetchOne()?.toDto()

    fun findRepatLink(userId: Long, eatHistoryIds: List<Long>): List<RepeatLink.Dto>? =
        jpaQueryFactory.selectFrom(REPEAT_LINK)
            .where(
                REPEAT_LINK.eatHistoryId.`in`(eatHistoryIds)
                    .and(REPEAT_LINK.userId.eq(userId))
            ).fetch().map { it.toDto() }

    fun saveRepeatEatHistory(repeatEatHistory: RepeatEatHistory.Dto): RepeatEatHistory.Dto? =
        repeatEatHistoryJpaRepo.save(repeatEatHistory.toEntity()).toDto()

    fun saveRepeatEatHistoryFood(repeatEatHistoryFoods: RepeatEatHistoryFood.Dto): RepeatEatHistoryFood.Dto? =
        repeatEatHistoryFoodJpaRepo.save(repeatEatHistoryFoods.toEntity()).toDto()

    fun saveRepeatLink(repeatLink: RepeatLink.Dto): RepeatLink.Dto? =
        repeatLinkJpaRepo.save(repeatLink.toEntity()).toDto()

    fun deleteRepeatEatHistory(id: Long) =
        repeatEatHistoryJpaRepo.deleteById(id)
}
