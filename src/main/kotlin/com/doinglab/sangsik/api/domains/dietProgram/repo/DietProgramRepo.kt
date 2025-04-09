package com.doinglab.sangsik.api.domains.dietProgram.repo

import com.doinglab.sangsik.api.domains.dietProgram.dto.request.RequestPostProgramDto
import com.doinglab.sangsik.api.domains.dietProgram.entity.DietProgram
import com.doinglab.sangsik.api.domains.dietProgram.entity.DietProgramEnrolledUser
import com.doinglab.sangsik.api.domains.dietProgram.entity.DietProgramPriceList
import com.doinglab.sangsik.api.domains.staff.entity.PartnerCompany
import com.doinglab.sangsik.api.domains.staff.entity.Staff
import com.doinglab.sangsik.define.CoreDefine.Companion.APP_NAME
import com.doinglab.sangsik.define.CoreDefine.Companion.DIET_PROGRAM
import com.doinglab.sangsik.define.CoreDefine.Companion.DIET_PROGRAM_ENROLLED_USER
import com.doinglab.sangsik.define.CoreDefine.Companion.DIET_PROGRAM_PRICE_LIST
import com.doinglab.sangsik.define.CoreDefine.Companion.PARTNER_COMPANY
import com.doinglab.sangsik.define.CoreDefine.Companion.STAFF
import com.doinglab.sangsik.enums.EnrolledStatus
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class DietProgramRepo(
    @Qualifier("dietJpaQueryFactory") private val jpaQueryFactory: JPAQueryFactory,
    private val dietProgramEnrolledUserJpaRepo: DietProgramEnrolledUserJpaRepo
) {
    fun findDietProgram(code: String): Pair<PartnerCompany.Dto?, DietProgram.Dto?> =
        jpaQueryFactory.select(PARTNER_COMPANY, DIET_PROGRAM)
            .from(PARTNER_COMPANY)
            .leftJoin(DIET_PROGRAM).on(PARTNER_COMPANY.id.eq(DIET_PROGRAM.partnerCompanyId))
            .where(
                PARTNER_COMPANY.code.eq(code)
                    .and(DIET_PROGRAM.appName.eq(APP_NAME))
                    .and(DIET_PROGRAM.isDeleted.isFalse)
            ).fetchFirst().let { tuple ->
                Pair(tuple?.get(PARTNER_COMPANY)?.toDto(), tuple?.get(DIET_PROGRAM)?.toDto())
            }

    fun findDietProgram(code: String, dietProgramId: Long): Pair<PartnerCompany.Dto?, DietProgram.Dto?> =
        jpaQueryFactory.select(PARTNER_COMPANY, DIET_PROGRAM)
            .from(PARTNER_COMPANY)
            .leftJoin(DIET_PROGRAM).on(PARTNER_COMPANY.id.eq(DIET_PROGRAM.partnerCompanyId))
            .where(
                PARTNER_COMPANY.code.eq(code)
                    .and(DIET_PROGRAM.appName.eq(APP_NAME))
                    .and(DIET_PROGRAM.isDeleted.isFalse)
                    .and(DIET_PROGRAM.id.eq(dietProgramId))
            ).fetchFirst().let { tuple ->
                Pair(tuple?.get(PARTNER_COMPANY)?.toDto(), tuple?.get(DIET_PROGRAM)?.toDto())
            }

    fun findDietProgram(staffId: Long): DietProgram.Dto? =
        jpaQueryFactory.select(DIET_PROGRAM)
            .from(DIET_PROGRAM)
            .where(DIET_PROGRAM.staffId.eq(staffId))
            .fetchFirst()?.toDto()

    fun findDietProgram(dietProgramIds: List<Long>): List<DietProgram.Dto>? =
        jpaQueryFactory.selectFrom(DIET_PROGRAM)
            .where(DIET_PROGRAM.id.`in`(dietProgramIds))
            .fetch().map { it.toDto() }

    fun findActiveDietProgramEnrolledUser(userId: Long): List<DietProgramEnrolledUser.Dto>? =
        jpaQueryFactory.selectFrom(DIET_PROGRAM_ENROLLED_USER)
            .where(
                DIET_PROGRAM_ENROLLED_USER.userId.eq(userId)
                    .and(DIET_PROGRAM_ENROLLED_USER.status.notIn(EnrolledStatus.inactive))
            ).orderBy(DIET_PROGRAM_ENROLLED_USER.id.desc()).fetch().map { it.toDto() }

    fun findActiveDietProgramEnrolledUserForStaffId(staffId: Long): List<DietProgramEnrolledUser.Dto>? =
        jpaQueryFactory.selectFrom(DIET_PROGRAM_ENROLLED_USER)
            .where(
                DIET_PROGRAM_ENROLLED_USER.staffId.eq(staffId)
                    .and(DIET_PROGRAM_ENROLLED_USER.status.notIn(EnrolledStatus.inactive))
            ).fetch().map { it.toDto() }

    fun findDietProgramEnrolledUser(id: Long): DietProgramEnrolledUser.Dto? =
        jpaQueryFactory.selectFrom(DIET_PROGRAM_ENROLLED_USER)
            .where(DIET_PROGRAM_ENROLLED_USER.id.eq(id))
            .fetchOne()?.toDto()

    fun findDietProgramEnrolledUser(userId: Long, date: LocalDateTime): Pair<DietProgramEnrolledUser.Dto?, Staff.Dto?>? =
        jpaQueryFactory.select(DIET_PROGRAM_ENROLLED_USER, STAFF)
            .from(DIET_PROGRAM_ENROLLED_USER)
            .leftJoin(STAFF).on(DIET_PROGRAM_ENROLLED_USER.staffId.eq(STAFF.id))
            .where(
                DIET_PROGRAM_ENROLLED_USER.userId.eq(userId)
                    .and(DIET_PROGRAM_ENROLLED_USER.startDate.lt(date))
                    .and(DIET_PROGRAM_ENROLLED_USER.status.notIn(EnrolledStatus.inactive))
            )
            .fetchOne()?.let { tuple ->
                Pair(tuple.get(DIET_PROGRAM_ENROLLED_USER)?.toDto(), tuple.get(STAFF)?.toDto())
            }

    fun findDietProgramEnrolledUserByRoomId(roomId: Long): DietProgramEnrolledUser.Dto? =
        jpaQueryFactory.selectFrom(DIET_PROGRAM_ENROLLED_USER)
            .where(DIET_PROGRAM_ENROLLED_USER.roomId.eq(roomId))
            .fetchOne()?.toDto()

    fun findDietProgramPriceList(dietProgramId: Long): DietProgramPriceList.Dto =
        jpaQueryFactory.selectFrom(DIET_PROGRAM_PRICE_LIST)
            .where(
                DIET_PROGRAM_PRICE_LIST.programId.eq(dietProgramId)
                    .and(DIET_PROGRAM_PRICE_LIST.isDeleted.isFalse)
            ).fetchFirst().toDto()

    fun saveDietProgramEnrolledUser(dietProgramEnrolledUser: DietProgramEnrolledUser.Dto) =
        dietProgramEnrolledUserJpaRepo.save(dietProgramEnrolledUser.toEntity())

    fun createDietProgramEnrolledUser(userId: Long, dietProgramId: Long, staffId: Long, roomId: Long, startDate: LocalDateTime, finishDate: LocalDateTime, status: EnrolledStatus, request: RequestPostProgramDto): Long =
        dietProgramEnrolledUserJpaRepo.save(
            DietProgramEnrolledUser.Dto(
            id = 0L,
            userId = userId,
            programId = dietProgramId,
            staffId = staffId,
            roomId = roomId,
            startDate = startDate,
            finishDate = finishDate,
            initFinishDate = finishDate,
            status = status,
            essentialCollectionPersonalInformation = request.essentialCollectionPersonalInformation,
            essentialSensitiveInformation = request.essentialSensitiveInformation,
            selectableCollectionPersonalInformation = request.selectableCollectionPersonalInformation,
            memberNo = request.memberNo
        ).toEntity()).id

}
