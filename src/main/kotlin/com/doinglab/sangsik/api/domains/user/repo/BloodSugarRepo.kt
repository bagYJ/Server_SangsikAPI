package com.doinglab.sangsik.api.domains.user.repo

import com.doinglab.sangsik.api.domains.user.entity.UserBloodGlucose
import com.doinglab.sangsik.define.CoreDefine.Companion.USER_BLOOD_GLUCOSE
import com.doinglab.sangsik.define.CoreTime.Companion.YYYYMMDD
import com.doinglab.sangsik.enums.BloodSugarInputType
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class BloodSugarRepo(
    @Qualifier("dietJpaQueryFactory") private val jpaQueryFactory: JPAQueryFactory,
    private val userBloodSugarJpaRepo: UserBloodSugarJpaRepo,
    private val userBloodGlucoseJpaRepo: UserBloodGlucoseJpaRepo
) {
    fun findUserBloodsugar(userId: Long, date: LocalDate, inputType: BloodSugarInputType?): List<UserBloodGlucose.Dto>? =
        jpaQueryFactory.selectFrom(USER_BLOOD_GLUCOSE)
            .where(
                USER_BLOOD_GLUCOSE.userId.eq(userId)
                    .and(USER_BLOOD_GLUCOSE.inputDate.eq(date.format(YYYYMMDD)))
            ).apply {
                if (inputType != null) where(USER_BLOOD_GLUCOSE.inputType.eq(inputType))
            }.fetch().map {
                it.toDto()
            }

    fun findUserBloodsugar(userId: Long, id: Long): UserBloodGlucose.Dto? =
        jpaQueryFactory.selectFrom(USER_BLOOD_GLUCOSE)
            .where(
                USER_BLOOD_GLUCOSE.userId.eq(userId)
                    .and(USER_BLOOD_GLUCOSE.id.eq(id))
            ).fetchOne()?.toDto()

    fun saveUserBloodsugar(userBloodSugar: UserBloodGlucose.Dto) =
        userBloodGlucoseJpaRepo.save(userBloodSugar.toEntity())

    fun deleteUserBloodsugar(userId: Long, id: Long) =
        jpaQueryFactory.delete(USER_BLOOD_GLUCOSE).where(
            USER_BLOOD_GLUCOSE.userId.eq(userId)
                .and(USER_BLOOD_GLUCOSE.id.eq(id))
        ).execute()
}
