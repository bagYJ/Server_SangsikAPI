package com.doinglab.sangsik.api.domains.user.repo

import com.doinglab.sangsik.api.domains.user.entity.*
import com.doinglab.sangsik.define.CoreDefine.Companion.ANNOUNCEMENT_USER_READ
import com.doinglab.sangsik.define.CoreDefine.Companion.APP_NAME
import com.doinglab.sangsik.define.CoreDefine.Companion.BLOOD_SUGAR
import com.doinglab.sangsik.define.CoreDefine.Companion.DIET_PROGRAM_ENROLLED_USER
import com.doinglab.sangsik.define.CoreDefine.Companion.EAT_HISTORY
import com.doinglab.sangsik.define.CoreDefine.Companion.FOOD_INFO_CUSTOM
import com.doinglab.sangsik.define.CoreDefine.Companion.PUSH_USER_READ
import com.doinglab.sangsik.define.CoreDefine.Companion.USER
import com.doinglab.sangsik.define.CoreDefine.Companion.USER_AGREEMENT
import com.doinglab.sangsik.define.CoreDefine.Companion.USER_BLOOD_GLUCOSE
import com.doinglab.sangsik.define.CoreDefine.Companion.USER_BLOOD_SUGAR
import com.doinglab.sangsik.define.CoreDefine.Companion.USER_CONTACT_POINT
import com.doinglab.sangsik.define.CoreDefine.Companion.USER_HISTORY
import com.doinglab.sangsik.define.CoreDefine.Companion.USER_PUSH_TOKEN
import com.doinglab.sangsik.define.CoreTime.Companion.YYYYMMDD
import com.doinglab.sangsik.enums.LoginSource
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class UserRepo(
    @Qualifier("dietJpaQueryFactory") private val jpaQueryFactory: JPAQueryFactory,
    private val userJpaRepo: UserJpaRepo,
    private val userHistoryJpaRepo: UserHistoryJpaRepo,
    private val userAgreementJpaRepo: UserAgreementJpaRepo,
    private val userPushTokenJpaRepo: UserPushTokenJpaRepo,
    private val userWithdrawalJpaRepo: UserWithdrawalJpaRepo,
    private val userWithdrawalAnswerJpaRepo: UserWithdrawalAnswerJpaRepo
): UserJpaRepo by userJpaRepo {
    fun findUserByEmail(emailId: String, password: String, loginSource: LoginSource): User.Dto? =
        userJpaRepo.getUserByEmailAndPasswordAndLoginSourceAndAppName(emailId, password, loginSource, APP_NAME)?.toDto()

    fun findUserByEmail(emailId: String?, loginSource: LoginSource): User.Dto? =
        userJpaRepo.getUserByEmailAndLoginSourceAndAppName(emailId, loginSource, APP_NAME)?.toDto()

    fun findUserByAccessToken(accessToken: String): User.Dto? =
        userJpaRepo.getUserByAccessTokenAndAppName(accessToken, APP_NAME)?.toDto()

    fun findUserByFacebookId(facebookId: String, loginSource: LoginSource): User.Dto? =
        userJpaRepo.getUserByFacebookIdAndLoginSourceAndAppName(facebookId, loginSource, APP_NAME)?.toDto()

    fun findUserById(userId: Long): User.Dto? =
        userJpaRepo.getUserByIdAndAppName(userId, APP_NAME)?.toDto()

    fun findUserByIds(userIds: List<Long>): List<User.Dto>? =
        userJpaRepo.getUsersByIdInAndAppName(userIds, APP_NAME)?.map { it.toDto() }

    fun findUserBloodSugar(userId: Long, date: LocalDate): List<UserBloodGlucose.Dto>? =
        jpaQueryFactory.selectFrom(USER_BLOOD_GLUCOSE)
            .where(
                USER_BLOOD_GLUCOSE.userId.eq(userId)
                    .and(USER_BLOOD_GLUCOSE.inputDate.eq(date.format(YYYYMMDD)))
            ).fetch().map { it.toDto() }

    fun withdrawal(user: User.Dto) {
        user.run {
            deleteFoodInfoCustom(id)
            deleteEatHistory(id)
            deleteUserHistory(id)
            deleteBloodSugar(id)
            deleteUserPushToken(id)
            deleteUserContactPoint(id)
            deleteDietProgramEnrolledUser(id)
            deleteUserAgreement(id)
            deletePushUserRead(id)
            deleteUserBloodSugar(id)
            deleteUserBloodGlucose(id)
            deleteAnnouncementUserRead(id)
            deleteUser(accessToken)
        }
    }

    fun deleteFoodInfoCustom(userId: Long) =
        jpaQueryFactory.delete(FOOD_INFO_CUSTOM).where(FOOD_INFO_CUSTOM.userId.eq(userId)).execute()

    fun deleteEatHistory(userId: Long) =
        jpaQueryFactory.delete(EAT_HISTORY).where(EAT_HISTORY.userId.eq(userId)).execute()

    fun deleteUserHistory(userId: Long) =
        jpaQueryFactory.delete(USER_HISTORY).where(USER_HISTORY.userId.eq(userId)).execute()

    fun deleteBloodSugar(userId: Long) =
        jpaQueryFactory.delete(BLOOD_SUGAR).where(BLOOD_SUGAR.userId.eq(userId)).execute()

    fun deleteUserPushToken(userId: Long) =
        jpaQueryFactory.delete(USER_PUSH_TOKEN).where((USER_PUSH_TOKEN.id.userId.eq(userId))).execute()

    fun deleteUserContactPoint(userId: Long) =
        jpaQueryFactory.delete(USER_CONTACT_POINT).where(USER_CONTACT_POINT.userId.eq(userId)).execute()

    fun deleteDietProgramEnrolledUser(userId: Long) =
        jpaQueryFactory.delete(DIET_PROGRAM_ENROLLED_USER).where(DIET_PROGRAM_ENROLLED_USER.userId.eq(userId)).execute()

    fun deleteUserAgreement(userId: Long) =
        jpaQueryFactory.delete(USER_AGREEMENT).where(USER_AGREEMENT.userId.eq(userId)).execute()

    fun deletePushUserRead(userId: Long) =
        jpaQueryFactory.delete(PUSH_USER_READ).where(PUSH_USER_READ.userId.eq(userId)).execute()

    fun deleteUserBloodSugar(userId: Long) =
        jpaQueryFactory.delete(USER_BLOOD_SUGAR).where(USER_BLOOD_SUGAR.userId.eq(userId)).execute()

    fun deleteUserBloodGlucose(userId: Long) =
        jpaQueryFactory.delete(USER_BLOOD_GLUCOSE).where(USER_BLOOD_GLUCOSE.userId.eq(userId)).execute()

    fun deleteAnnouncementUserRead(userId: Long) =
        jpaQueryFactory.delete(ANNOUNCEMENT_USER_READ).where(ANNOUNCEMENT_USER_READ.userId.eq(userId)).execute()

    fun deleteUser(accessToken: String) =
        jpaQueryFactory.delete(USER).where(
            USER.accessToken.eq(accessToken)
                .and(USER.appName.eq(APP_NAME))
        ).execute()

    fun findUserHistory(userId: Long, dates: List<LocalDate>): List<UserHistory.Dto>? =
        jpaQueryFactory.selectFrom(USER_HISTORY).where(
            USER_HISTORY.userId.eq(userId)
                .and(USER_HISTORY.date.`in`(dates))
        ).fetch().map { it.toDto() }

    fun findUserHistory(userId: Long, id: Long): UserHistory.Dto? =
        jpaQueryFactory.selectFrom(USER_HISTORY).where(
            USER_HISTORY.userId.eq(userId)
                .and(USER_HISTORY.id.eq(id))
        ).fetchOne()?.toDto()

    fun findRecentUserHistory(userId: Long, date: LocalDate): UserHistory.Dto? =
        jpaQueryFactory.selectFrom(USER_HISTORY).where(
            USER_HISTORY.userId.eq(userId)
                .and(USER_HISTORY.date.lt(date))
        ).orderBy(USER_HISTORY.date.desc()).limit(1L).fetchOne()?.toDto()

    fun saveUser(user: User.Dto): User.Dto? =
        userJpaRepo.save(user.toEntity()).toDto()

    fun saveUser(userId: Long, locale: String): Long? =
        jpaQueryFactory.update(USER)
            .set(USER.locale, locale).where(USER.id.eq(userId)).execute()

    fun saveUserHistory(userHistory: UserHistory.Dto): UserHistory.Dto? =
        userHistoryJpaRepo.save(userHistory.toEntity()).toDto()

    fun saveUserPushToken(userPushToken: UserPushToken.Dto): UserPushToken.Dto? =
        userPushTokenJpaRepo.save(userPushToken.toEntity()).toDto()

    fun saveUserWithdrawal(userWithdrawal: UserWithdrawal.Dto): UserWithdrawal.Dto? =
        userWithdrawalJpaRepo.save(userWithdrawal.toEntity()).toDto()

    fun saveAllUserWithdrawalAnswer(userWithdrawalAnswers: List<UserWithdrawalAnswer.Dto>): List<UserWithdrawalAnswer.Dto>? =
        userWithdrawalAnswerJpaRepo.saveAll(userWithdrawalAnswers.map { it.toEntity() }).map { it.toDto() }

    fun deleteUserHistory(userId: Long, id: Long) =
        jpaQueryFactory.delete(USER_HISTORY).where(
            USER_HISTORY.userId.eq(userId)
                .and(USER_HISTORY.id.eq(id))
        ).execute()

    @Cacheable(key = "#userId", value = ["userAgreement"], unless = "#result == null")
    fun findUserAgreement(userId: Long): UserAgreement.Dto? =
        jpaQueryFactory.selectFrom(USER_AGREEMENT)
            .where(USER_AGREEMENT.userId.eq(userId)).fetchOne()?.toDto()

    @CacheEvict(key = "#userAgreement.userId", value = ["userAgreement"], beforeInvocation = false)
    fun saveUserAgreement(userAgreement: UserAgreement.Dto): UserAgreement.Dto? =
        userAgreementJpaRepo.save(userAgreement.toEntity()).toDto()
}
