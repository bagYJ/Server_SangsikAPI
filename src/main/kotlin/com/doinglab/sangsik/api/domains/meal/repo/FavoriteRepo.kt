package com.doinglab.sangsik.api.domains.meal.repo

import com.doinglab.sangsik.api.domains.meal.entity.FavoriteEatHistory
import com.doinglab.sangsik.api.domains.meal.entity.FavoriteEatHistoryFood
import com.doinglab.sangsik.api.domains.meal.entity.FavoriteLink
import com.doinglab.sangsik.define.CoreDefine.Companion.CHAT_MESSAGE
import com.doinglab.sangsik.define.CoreDefine.Companion.FAVORITE_EAT_HISTORY
import com.doinglab.sangsik.define.CoreDefine.Companion.FAVORITE_EAT_HISTORY_FOOD
import com.doinglab.sangsik.define.CoreDefine.Companion.FAVORITE_LINK
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository

@Repository
class FavoriteRepo(
    @Qualifier("dietJpaQueryFactory") private val jpaQueryFactory: JPAQueryFactory,
    private val favoriteEatHistoryJpaRepo: FavoriteEatHistoryJpaRepo,
    private val favoriteEatHistoryFoodJpaRepo: FavoriteEatHistoryFoodJpaRepo,
    private val favoriteLinkJpaRepo: FavoriteLinkJpaRepo
) {
    fun findFavoriteEatHistories(userId: Long, lastId: Long?, limit: Long): List<FavoriteEatHistory.Dto>? =
        jpaQueryFactory.selectFrom(FAVORITE_EAT_HISTORY)
            .where(FAVORITE_EAT_HISTORY.userId.eq(userId)).apply {
                lastId?.let {
                    where(CHAT_MESSAGE.id.lt(it))
                }
            }
            .orderBy(FAVORITE_EAT_HISTORY.id.desc())
            .limit(limit).fetch().map { it.toDto() }

    fun findFavoriteEatHistories(userId: Long, eatHistoryIds: List<Long>): List<FavoriteEatHistory.Dto>? =
        jpaQueryFactory.selectFrom(FAVORITE_EAT_HISTORY)
            .where(
                FAVORITE_EAT_HISTORY.userId.eq(userId)
                    .and(FAVORITE_EAT_HISTORY.eatHistoryId.`in`(eatHistoryIds))
            )
            .fetch().map { it.toDto() }

    fun findFavoriteEatHistory(id: Long): FavoriteEatHistory.Dto? =
        jpaQueryFactory.selectFrom(FAVORITE_EAT_HISTORY)
            .where(FAVORITE_EAT_HISTORY.id.eq(id)).fetchOne()?.toDto()

    fun findFavoriteEatHistory(userId: Long, id: Long): FavoriteEatHistory.Dto? =
        jpaQueryFactory.selectFrom(FAVORITE_EAT_HISTORY)
            .where(
                FAVORITE_EAT_HISTORY.eatHistoryId.eq(id)
                    .and(FAVORITE_EAT_HISTORY.userId.eq(userId))
            ).fetchOne()?.toDto()

    fun findFavoriteEatHistoryFood(id: Long): FavoriteEatHistoryFood.Dto? =
        jpaQueryFactory.selectFrom(FAVORITE_EAT_HISTORY_FOOD)
            .where(FAVORITE_EAT_HISTORY_FOOD.favoriteId.eq(id))
            .fetchOne()?.toDto()

    fun findFavoriteLinks(userId: Long, eatHistoryIds: List<Long>): List<FavoriteLink.Dto>? =
        jpaQueryFactory.selectFrom(FAVORITE_LINK)
            .where(
                FAVORITE_LINK.eatHistoryId.`in`(eatHistoryIds)
                    .and(FAVORITE_LINK.userId.eq(userId))
            ).fetch().map { it.toDto() }

    fun findFavoriteLink(userId: Long, id: Long): FavoriteLink.Dto? =
        jpaQueryFactory.selectFrom(FAVORITE_LINK)
            .where(
                FAVORITE_LINK.id.eq(id)
                    .and(FAVORITE_LINK.userId.eq(userId))
            ).fetchOne()?.toDto()

    fun saveFavoriteEatHistory(favoriteEatHistory: FavoriteEatHistory.Dto): FavoriteEatHistory.Dto? =
        favoriteEatHistoryJpaRepo.save(favoriteEatHistory.toEntity()).toDto()

    fun saveFavoriteEatHistoryFood(favoriteEatHistoryFoods: FavoriteEatHistoryFood.Dto): FavoriteEatHistoryFood.Dto? =
        favoriteEatHistoryFoodJpaRepo.save(favoriteEatHistoryFoods.toEntity()).toDto()

    fun saveFavoriteLink(favoriteLink: FavoriteLink.Dto): FavoriteLink.Dto? =
        favoriteLinkJpaRepo.save(favoriteLink.toEntity()).toDto()

    fun deleteFavoriteEatHistory(id: Long) =
        favoriteEatHistoryJpaRepo.deleteById(id)
}
