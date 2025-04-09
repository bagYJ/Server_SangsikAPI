package com.doinglab.sangsik.api.domains.food.repo

import com.doinglab.sangsik.api.domains.food.entity.*
import com.doinglab.sangsik.define.CoreDefine.Companion.FOOD_INFO_CUSTOM
import com.doinglab.sangsik.define.CoreDefine.Companion.FOOD_INFO_PLATE
import com.doinglab.sangsik.define.CoreDefine.Companion.FOOD_INFO_R2
import com.doinglab.sangsik.define.CoreDefine.Companion.FOOD_PLATE_SCALE
import com.doinglab.sangsik.define.CoreDefine.Companion.FOOD_SERVING_UNIT_TRANSLATION
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository

@Repository
class FoodRepo(
    @Qualifier("dietJpaQueryFactory") private val jpaQueryFactory: JPAQueryFactory,
    private val foodInfoJpaRepo: FoodInfoJpaRepo,
    private val foodInfoR2JpaRepo: FoodInfoR2JpaRepo,
    private val foodInfoCustomJpaRepo: FoodInfoCustomJpaRepo,
    private val foodNameSearchJpaRepo: FoodNameSearchJpaRepo
) {
    fun findFoodNameSearch(keyword: String): List<FoodNameSearch.Dto> =
        foodNameSearchJpaRepo.findFoodNameSearch(keyword).map { it.toDto() }

    fun findFoodInfo(id: Long): Pair<FoodInfoR2.Dto?, FoodServingUnitTranslation.Dto?>? =
        jpaQueryFactory.select(FOOD_INFO_R2, FOOD_SERVING_UNIT_TRANSLATION)
            .from(FOOD_INFO_R2)
            .leftJoin(FOOD_SERVING_UNIT_TRANSLATION)
            .on(FOOD_INFO_R2.servingUnit.eq(FOOD_SERVING_UNIT_TRANSLATION.unitKey))
            .where(FOOD_INFO_R2.foodNumber.eq(id))
            .fetchOne()?.let { tuple ->
                Pair(tuple.get(FOOD_INFO_R2)?.toDto(), tuple.get(FOOD_SERVING_UNIT_TRANSLATION)?.toDto())
            }

    fun findFoodInfoPlate(id: Long): FoodInfoPlate.Dto? =
        jpaQueryFactory.selectFrom(FOOD_INFO_PLATE)
            .where(FOOD_INFO_PLATE.FoodNumber.eq(id))
            .fetchOne()?.toDto()

    fun findFoodPlateScale(className: String, size: String): FoodPlateScale.Dto? =
        jpaQueryFactory.selectFrom(FOOD_PLATE_SCALE)
            .where(
                FOOD_PLATE_SCALE.className.eq(className)
                    .and(FOOD_PLATE_SCALE.size.eq(size))
            ).fetchOne()?.toDto()

    fun findFoodInfoCustoms(userId: Long): List<FoodInfoR2.Dto>? =
        jpaQueryFactory.selectFrom(FOOD_INFO_CUSTOM)
            .where(
                FOOD_INFO_CUSTOM.userId.eq(userId)
                    .and(FOOD_INFO_CUSTOM.deleteFlag.isFalse)
            )
            .orderBy(FOOD_INFO_CUSTOM.foodNumber.desc())
            .fetch()?.map { it.toDto() }

    fun findFoodInfoCustoms(userId: Long, foodNumbers: List<Long>): List<FoodInfoR2.Dto>? =
        jpaQueryFactory.selectFrom(FOOD_INFO_CUSTOM)
            .where(
                FOOD_INFO_CUSTOM.userId.eq(userId)
                    .and(FOOD_INFO_CUSTOM.foodNumber.`in`(foodNumbers))
                    .and(FOOD_INFO_CUSTOM.deleteFlag.isFalse)
            )
            .orderBy(FOOD_INFO_CUSTOM.foodNumber.desc())
            .fetch()?.map { it.toDto() }

    fun findFoodInfoCustom(id: Long): FoodInfoR2.Dto? =
        jpaQueryFactory.selectFrom(FOOD_INFO_CUSTOM)
            .where(FOOD_INFO_CUSTOM.foodNumber.eq(id))
            .fetchOne()?.toDto()

    fun saveFoodInfoCustom(userId: Long, foodInfoR2: FoodInfoR2.Dto) =
        foodInfoCustomJpaRepo.save(foodInfoR2.toCustomEntity(userId)).toDto()
}
