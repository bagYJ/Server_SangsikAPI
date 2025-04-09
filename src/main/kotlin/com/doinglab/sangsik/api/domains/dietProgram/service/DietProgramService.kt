package com.doinglab.sangsik.api.domains.dietProgram.service

import com.doinglab.sangsik.Exception.CustomException
import com.doinglab.sangsik.api.domains.chat.service.ChatRoomService
import com.doinglab.sangsik.api.domains.common.dto.CustomDto
import com.doinglab.sangsik.api.domains.dietProgram.dto.request.RequestPostProgramDto
import com.doinglab.sangsik.api.domains.dietProgram.dto.response.ResponseGetProgramCodeDto
import com.doinglab.sangsik.api.domains.dietProgram.dto.response.ResponseGetProgramDto
import com.doinglab.sangsik.api.domains.dietProgram.dto.response.ResponsePostProgramDto
import com.doinglab.sangsik.api.domains.dietProgram.entity.DietProgramEnrolledUser
import com.doinglab.sangsik.api.domains.dietProgram.repo.DietProgramRepo
import com.doinglab.sangsik.api.domains.staff.entity.Staff
import com.doinglab.sangsik.api.domains.staff.repo.StaffRepo
import com.doinglab.sangsik.api.domains.user.entity.User
import com.doinglab.sangsik.api.domains.user.repo.UserRepo
import com.doinglab.sangsik.enums.*
import com.doinglab.sangsik.utils.countHolidayBetweenDays
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class DietProgramService(
    private val dietProgramRepo: DietProgramRepo,
    private val userRepo: UserRepo,
    private val staffRepo: StaffRepo,
    private val chatRoomService: ChatRoomService
) {
    companion object {
        private const val SHORT_PROGRAM_DAYS = 5
    }

    fun doGetProgramCode(code: String): ResponseGetProgramCodeDto =
        dietProgramRepo.findDietProgram(code).let { program ->
            program.first?.let { partnerCompany ->
                program.second?.let { dietProgram ->
                    if (dietProgram.status != DietProgramStatus.OPERATED) throw CustomException(StatusCode.NOT_OPERATED)
                    if (EnrollType.applyCode.find { it == dietProgram.enrollType } == null) throw CustomException(StatusCode.NO_APPLY_CODE)

                    ResponseGetProgramCodeDto(dietProgram, partnerCompany.code)
                } ?: throw CustomException(StatusCode.NOT_FOUND_DIET_PROGRAM)
            } ?: throw CustomException(StatusCode.NO_MATCH_DIET_PROGRAM_CODE)
        }

    @Caching(evict = [
        CacheEvict(key = "#user.id", value = ["enrolledUser"], beforeInvocation = false),
        CacheEvict(key = "#user.accessToken", value = ["user"], beforeInvocation = false)
    ])
    fun doPostProgram(user: User.Dto, request: RequestPostProgramDto): ResponsePostProgramDto =
        dietProgramRepo.findDietProgram(request.code, request.dietProgramId).let { program ->
            program.first?.let {
                program.second?.let { dietProgram ->
                    if (dietProgram.status != DietProgramStatus.OPERATED) throw CustomException(StatusCode.NOT_OPERATED)
                    if (dietProgram.enrollType !in EnrollType.applyCode) throw CustomException(StatusCode.NO_APPLY_CODE)

                    // 과거 히스토리들을 돌며, 같은 프로그램을 했는지 검사
                    dietProgramRepo.findActiveDietProgramEnrolledUser(user.id)?.map {
                        // 다른 프로그램 진행중이면 무조건 안됨
                        if (it.programId != dietProgram.id && it.status.value < EnrolledStatus.DONE.value) throw CustomException(StatusCode.ALREADY_ENROLLED_PROGRAM)
                        if (dietProgram.canReEntry) {
                            // 프로그램 다시 등록 가능 할 떄, 프로그램이 있으나, 완료상태가 아니면 fail
                            if (it.programId == dietProgram.id && it.status.value < EnrolledStatus.DONE.value) throw CustomException(StatusCode.ALREADY_ENROLLED_PROGRAM)
                        } else {
                            // 프로그램에 다시 등록이 불가능 할 떄, 과거에 같은 프로그램을 했으면 fail
                            if (it.programId == dietProgram.id) throw CustomException(StatusCode.CANNOT_REGIST_PROGRAM)
                        }
                    }

                    chatRoomService.createChatRoom(user.id, dietProgram.staffId)?.let { roomId ->
                        val dietProgramId = dietProgramRepo.findDietProgramPriceList(dietProgram.id).let { price ->
                            createEnrolledProgram(user.id, dietProgram.id, dietProgram.staffId, roomId, (price.periodType.day * price.period), price.periodType, request)
                        }
                        chatRoomService.createChatReadIndex(user.id, false, roomId)
                        chatRoomService.createChatReadIndex(dietProgram.staffId, true, roomId)
                        userRepo.save(user.apply {
                            name = request.name
                            birthDate = request.birthDate.atStartOfDay()
                        }.toEntity())

                        ResponsePostProgramDto(dietProgramId, roomId)
                    } ?: throw CustomException(StatusCode.FAIL_INSERT)
                } ?: throw CustomException(StatusCode.NOT_FOUND_DIET_PROGRAM)
            } ?: throw CustomException(StatusCode.NO_MATCH_DIET_PROGRAM_CODE)
        }

    fun doGetProgram(userId: Long) =
        dietProgramRepo.findActiveDietProgramEnrolledUser(userId)?.let { dietProgramEnrolleds ->
            val dietPrograms = dietProgramRepo.findDietProgram(dietProgramEnrolleds.map { it.programId }.distinct())
            val staffs = staffRepo.findStaff(dietProgramEnrolleds.map { it.staffId }.distinct())

            ResponseGetProgramDto(dietProgramEnrolleds, dietPrograms, staffs)
        }

    @Caching(evict = [
        CacheEvict(key = "#user.id", value = ["enrolledUser"], beforeInvocation = false),
        CacheEvict(key = "#user.accessToken", value = ["user"], beforeInvocation = false)
    ])
    fun doDeleteProgram(user: User.Dto, programId: Long): CustomDto =
        dietProgramRepo.findDietProgramEnrolledUser(programId)?.let { dietProgramEnrolled ->
            if (dietProgramEnrolled.userId != user.id) throw CustomException(StatusCode.NOT_FOUND_DIET_PROGRAM)

            dietProgramRepo.saveDietProgramEnrolledUser(dietProgramEnrolled.apply {
                status = EnrolledStatus.CANCELUSER
                finishDate = LocalDateTime.now()
            }).run {
                if (id < 1) throw CustomException(StatusCode.FAIL_UPDATE)
            }

            CustomDto()
        } ?: throw CustomException(StatusCode.NOT_FOUND_DIET_PROGRAM)

    @Cacheable(key = "#userId", value = ["enrolledUser"], unless = "#result == null")
    fun findDietProgramEnrolledUser(userId: Long, date: LocalDateTime): Pair<DietProgramEnrolledUser.Dto?, Staff.Dto?>? =
        dietProgramRepo.findDietProgramEnrolledUser(userId, date)

    fun findActiveDietProgramEnrolledUserForStaffId(staffId: Long): List<DietProgramEnrolledUser.Dto>? =
        dietProgramRepo.findActiveDietProgramEnrolledUserForStaffId(staffId)

    private fun createEnrolledProgram(userId: Long, dietProgramId: Long, staffId: Long, roomId: Long, dutyDays: Int, periodType: PeriodType, request: RequestPostProgramDto): Long {
        fun getFinishDate(startDate: LocalDateTime): LocalDateTime =
            startDate.let {
                when(dutyDays <= SHORT_PROGRAM_DAYS) {
                    true -> it.plusDays(dutyDays + countHolidayBetweenDays(it, it.plusDays(dutyDays.toLong())).toLong())
                    false -> it.plusDays(dutyDays.toLong())
                }.minusSeconds(1L)
            }

        val startDate = LocalDateTime.now()
        val finishDate = LocalDateTime.of(2099, 12, 31, 23, 59)

        return dietProgramRepo.createDietProgramEnrolledUser(userId, dietProgramId, staffId, roomId, startDate, finishDate, when(periodType == PeriodType.TIMES) {
            true -> EnrolledStatus.INPROGRESS
            false -> EnrolledStatus.WAIT
        }, request)
    }
}
