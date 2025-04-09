package com.doinglab.sangsik.api.domains.cgm.repo

import com.doinglab.sangsik.api.cgm.dto.response.ResponseCgmAccountPostTokenDto
import com.doinglab.sangsik.api.cgm.dto.response.ResponseDoGetCgmsDto
import com.doinglab.sangsik.api.domains.cgm.entity.UserCgmBloodGlucose
import com.doinglab.sangsik.api.domains.cgm.entity.UserCgmToken
import com.doinglab.sangsik.api.domains.user.entity.User
import com.doinglab.sangsik.define.CoreDefine.Companion.BATCH_SIZE
import com.doinglab.sangsik.define.CoreDefine.Companion.USER_CGM_BLOOD_GLUCOSE
import com.doinglab.sangsik.define.CoreDefine.Companion.USER_CGM_TOKEN
import com.doinglab.sangsik.define.CoreTime.Companion.YYYY_MM_DD_HH_MM_SS
import com.doinglab.sangsik.enums.CgmStage
import com.doinglab.sangsik.utils.encrypt
import com.doinglab.sangsik.utils.toUtcTime
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Repository
class CgmRepo(
    @Qualifier("dietJpaQueryFactory") private val jpaQueryFactory: JPAQueryFactory,
    private val userCgmTokenJpaRepo: UserCgmTokenJpaRepo,
    private val userCgmBloodGlucoseJpaRepo: UserCgmBloodGlucoseJpaRepo,
    private val jdbcTemplate: JdbcTemplate,
) {
    @Cacheable(key = "#userId", value = ["userCgmToken"], unless = "#result == null")
    fun findUserCgmToken(userId: Long): UserCgmToken.Dto? =
        jpaQueryFactory.select(USER_CGM_TOKEN)
            .from(USER_CGM_TOKEN)
            .where(
                USER_CGM_TOKEN.userId.eq(userId)
                    .and(USER_CGM_TOKEN.disconnectedAt.isNull)
            )
            .fetchOne()?.toDto()

    @Transactional
    fun findUserCgmToken(userId: String): UserCgmToken.Dto? =
        jpaQueryFactory.select(USER_CGM_TOKEN)
            .from(USER_CGM_TOKEN)
            .where(
                USER_CGM_TOKEN.cgmId.eq(userId)
                    .and(USER_CGM_TOKEN.disconnectedAt.isNull)
            )
            .fetchOne()?.toDto()

    fun findUserCgmTokens(userId: Long): List<UserCgmToken.Dto>? =
        jpaQueryFactory.select(USER_CGM_TOKEN)
            .from(USER_CGM_TOKEN)
            .where(USER_CGM_TOKEN.userId.eq(userId))
            .fetch()?.map { it.toDto() }

    fun findUserCgmTokens(userIds: List<Long>): List<UserCgmToken.Dto>? =
        jpaQueryFactory.select(USER_CGM_TOKEN)
            .from(USER_CGM_TOKEN)
            .where(
                (USER_CGM_TOKEN.cgmCalledAt.lt(LocalDateTime.now().minusMinutes(5)))
                    .and(USER_CGM_TOKEN.userId.`in`(userIds))
                    .and(USER_CGM_TOKEN.disconnectedAt.isNull)
            )
            .fetch()?.map { it.toDto() }

    fun findUserCgmTokens(): List<UserCgmToken.Dto>? =
        jpaQueryFactory.select(USER_CGM_TOKEN)
            .from(USER_CGM_TOKEN)
            .where(
                (USER_CGM_TOKEN.updatedAt.lt(LocalDateTime.now().minusMinutes(5)))
                    .and(USER_CGM_TOKEN.disconnectedAt.isNull)
            )
            .fetch()?.map { it.toDto() }

    fun insertUserCgmToken(cgmToken: UserCgmToken.Dto): Long =
        userCgmTokenJpaRepo.save(cgmToken.toEntity()).id

    @Transactional
    @Caching(evict = [
        CacheEvict(key = "#user.id", value = ["userCgmToken"], beforeInvocation = false),
        CacheEvict(key = "#user.accessToken", value = ["user"], beforeInvocation = false)
    ])
    fun updateUserCgmToken(user: User.Dto, accessToken: String, refreshToken: String, expiresIn: Long, updatedAt: LocalDateTime): Long =
        jpaQueryFactory.update(USER_CGM_TOKEN)
            .set(USER_CGM_TOKEN.accessToken, accessToken)
            .set(USER_CGM_TOKEN.refreshToken, refreshToken)
            .set(USER_CGM_TOKEN.expiresIn, expiresIn)
            .set(USER_CGM_TOKEN.updatedAt, updatedAt).where(
                USER_CGM_TOKEN.userId.eq(user.id)
                    .and(USER_CGM_TOKEN.disconnectedAt.isNull)
            ).execute()

    @Transactional
    @Caching(evict = [
        CacheEvict(key = "#user.id", value = ["userCgmToken"], beforeInvocation = false),
        CacheEvict(key = "#user.accessToken", value = ["user"], beforeInvocation = false)
    ])
    fun updateUserCgmToken(user: User.Dto, cgmToken: ResponseCgmAccountPostTokenDto, aesKey: String, cgmCalledAt: LocalDateTime, updatedAt: LocalDateTime): Long =
        jpaQueryFactory.update(USER_CGM_TOKEN)
            .set(USER_CGM_TOKEN.accessToken, cgmToken.accessToken.encrypt(aesKey))
            .set(USER_CGM_TOKEN.refreshToken, cgmToken.refreshToken.encrypt(aesKey))
            .set(USER_CGM_TOKEN.expiresIn, cgmToken.expiresIn)
            .set(USER_CGM_TOKEN.cgmCalledAt, cgmCalledAt)
            .set(USER_CGM_TOKEN.updatedAt, updatedAt).where(
                USER_CGM_TOKEN.userId.eq(user.id)
                    .and(USER_CGM_TOKEN.disconnectedAt.isNull)
            ).execute()

    @Transactional
    @Caching(evict = [
        CacheEvict(key = "#user.id", value = ["userCgmToken"], beforeInvocation = false),
        CacheEvict(key = "#user.accessToken", value = ["user"], beforeInvocation = false)
    ])
    fun updateUserCgmToken(user: User.Dto, cgmCalledAt: LocalDateTime): Long =
        jpaQueryFactory.update(USER_CGM_TOKEN)
            .set(USER_CGM_TOKEN.cgmCalledAt, cgmCalledAt)
            .where(
                USER_CGM_TOKEN.userId.eq(user.id)
                    .and(USER_CGM_TOKEN.disconnectedAt.isNull)
            ).execute()

    @Transactional
    @Caching(evict = [
        CacheEvict(key = "#user.id", value = ["userCgmToken"], beforeInvocation = false),
        CacheEvict(key = "#user.accessToken", value = ["user"], beforeInvocation = false)
    ])
    fun deleteUserCgmToken(user: User.Dto): Long =
        jpaQueryFactory.update(USER_CGM_TOKEN)
            .set(USER_CGM_TOKEN.disconnectedAt, LocalDateTime.now())
            .where(
                USER_CGM_TOKEN.userId.eq(user.id)
                    .and(USER_CGM_TOKEN.disconnectedAt.isNull)
            ).execute()

    fun findUserCgmBloodGlucoseMinEventAt(userId: Long, aesKey: String): LocalDateTime? =
        jpaQueryFactory.select(USER_CGM_BLOOD_GLUCOSE.eventAt.min())
            .from(USER_CGM_BLOOD_GLUCOSE)
            .where(
                USER_CGM_BLOOD_GLUCOSE.userId.eq(userId)
                    .and(USER_CGM_BLOOD_GLUCOSE.stage.`in`(CgmStage.notComplete))
                    .and(USER_CGM_BLOOD_GLUCOSE.value.ne("0".encrypt(aesKey)))
            )
            .fetchOne()?.toUtcTime()

    fun findUserCgmBloodGlucoseMinEventAt(serialNumber: String): LocalDateTime? =
        jpaQueryFactory.select(USER_CGM_BLOOD_GLUCOSE.eventAt.max())
            .from(USER_CGM_BLOOD_GLUCOSE)
            .where(USER_CGM_BLOOD_GLUCOSE.serialNumber.eq(serialNumber))
            .fetchOne()?.toUtcTime()?.plusMinutes(1)

    fun insertUserCgmBloodGlucose(userId: Long, cgms: List<ResponseDoGetCgmsDto>, aesKey: String) =
        jdbcTemplate.batchUpdate("insert into UserCgmBloodGlucose(userId, serialNumber, seqNumber, eventAt, stage, initialValue, value, trend, trendRate) values (?, ?, ?, ?, ?, ?, ?, ?, ?) on duplicate key update stage = values(stage), initialValue = values(initialValue), value = values(value), trend = values(trend), trendRate = values(trendRate)", cgms, BATCH_SIZE) { ps, arg ->
            ps.setLong(1, userId)
            ps.setString(2, arg.serialNumber)
            ps.setLong(3, arg.seqNumber)
            ps.setString(4, arg.eventAt.format(YYYY_MM_DD_HH_MM_SS))
            ps.setInt(5, arg.stage)
            ps.setString(6, arg.initialValue.encrypt(aesKey))
            ps.setString(7, arg.value.encrypt(aesKey))
            ps.setInt(8, arg.trend)
            ps.setFloat(9, arg.trendRate)
        }

    fun findUserCgmBloodGlucose(userId: Long, sdt: LocalDateTime, edt: LocalDateTime): List<UserCgmBloodGlucose.Dto>? =
        jpaQueryFactory.select(USER_CGM_BLOOD_GLUCOSE)
            .from(USER_CGM_BLOOD_GLUCOSE)
            .where(USER_CGM_BLOOD_GLUCOSE.userId.eq(userId)
                .and(USER_CGM_BLOOD_GLUCOSE.eventAt.between(sdt, edt)))
            .orderBy(USER_CGM_BLOOD_GLUCOSE.eventAt.asc())
            .fetch()?.map { it.toDto() }
}
