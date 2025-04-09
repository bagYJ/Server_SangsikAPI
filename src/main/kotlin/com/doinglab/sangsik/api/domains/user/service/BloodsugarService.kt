package com.doinglab.sangsik.api.domains.user.service

import com.doinglab.sangsik.Exception.CustomException
import com.doinglab.sangsik.api.aes.dto.AesDto
import com.doinglab.sangsik.api.domains.cgm.service.CgmService
import com.doinglab.sangsik.api.domains.common.dto.CustomDto
import com.doinglab.sangsik.api.domains.meal.service.MealService
import com.doinglab.sangsik.api.domains.user.dto.request.RequestPostBloodsugarDto
import com.doinglab.sangsik.api.domains.user.dto.response.ResponseGetBloodsugarDto
import com.doinglab.sangsik.api.domains.user.dto.response.ResponseGetBloodsugarDto.GetBloodsugarBody
import com.doinglab.sangsik.api.domains.user.entity.User
import com.doinglab.sangsik.api.domains.user.entity.UserBloodGlucose
import com.doinglab.sangsik.api.domains.user.repo.BloodSugarRepo
import com.doinglab.sangsik.enums.BloodSugarInputType
import com.doinglab.sangsik.enums.MealTime
import com.doinglab.sangsik.enums.MealType
import com.doinglab.sangsik.enums.StatusCode
import com.doinglab.sangsik.utils.between
import com.doinglab.sangsik.utils.encrypt
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Service
@Transactional
class BloodsugarService(
    private val bloodSugarRepo: BloodSugarRepo,
    private val mealService: MealService,
    private val cgmService: CgmService,
    private val aesProperties: AesDto
) {
    fun doGetBloodsugar(user: User.Dto, date: LocalDate, mealType: MealType?, mealTime: MealTime?): ResponseGetBloodsugarDto {
        val cgmUserBloodSugars = cgmService.findUserCgmBloodGlucose(user, date)
        val bloodSugars = bloodSugarRepo.findUserBloodsugar(user.id, date, when (mealType) {
            null -> null
            else -> BloodSugarInputType.findByMealAndTimes(mealType, mealTime)
        })
        val isCgmBloodSugar = cgmService.findUserCgmTokens(user.id)?.any { date.between(it.sdt, it.edt) } == true

        return when (cgmUserBloodSugars?.size?.equals(0) == true) {
            true -> when {
                user.cgmConnectDate?.let { date >= it } == true -> when (bloodSugars?.size?.equals(0) == true) {
                    true -> ResponseGetBloodsugarDto(body = GetBloodsugarBody(listOf(), listOf(), isCgmBloodSugar = true))
                    false -> ResponseGetBloodsugarDto(bloodSugars, aesProperties.aesKey, true)
                }
                else -> ResponseGetBloodsugarDto(bloodSugars, aesProperties.aesKey, isCgmBloodSugar)
            }
            false -> when (cgmUserBloodSugars?.minByOrNull { it.eventAt }?.eventAt?.between(LocalDateTime.of(date, LocalTime.of(0, 0)), LocalDateTime.of(date, LocalTime.of(0, 5))) == true) {
                true -> ResponseGetBloodsugarDto(cgmUserBloodSugars, mealService.findEatHistory(user.id, date), aesProperties.aesKey)
                false -> ResponseGetBloodsugarDto(cgmUserBloodSugars, bloodSugars?.filter { it.datetime < cgmUserBloodSugars?.firstOrNull()?.eventAt }, mealService.findEatHistory(user.id, date), aesProperties.aesKey)
            }
        }
    }

    fun doPostBloodsugar(user: User.Dto, request: RequestPostBloodsugarDto): CustomDto {
        if (request.inputDate > LocalDate.now()) throw CustomException(StatusCode.DATE_GREATER_THEN_NOW)
        val userBloodsugar = bloodSugarRepo.findUserBloodsugar(user.id, request.inputDate, request.inputType)?.firstOrNull() ?: UserBloodGlucose.Dto(
            id = 0L,
            userId = user.id,
            inputDate = request.date,
            inputTime = request.time,
            inputType = request.inputType
        )

        return try {
            bloodSugarRepo.saveUserBloodsugar(userBloodsugar.apply {
                inputTime = request.time
                bloodSugar = request.bloodsugar.encrypt(aesProperties.aesKey)
            })

            CustomDto()
        } catch (e: Exception) {
            if (userBloodsugar.id > 0L) throw CustomException(StatusCode.FAIL_UPDATE)
            else throw CustomException(StatusCode.FAIL_INSERT)
        }
    }

    fun doPutBloodsugar(user: User.Dto, id: Long, request: RequestPostBloodsugarDto): CustomDto {
        if (request.inputDate > LocalDate.now()) throw CustomException(StatusCode.DATE_GREATER_THEN_NOW)
        return bloodSugarRepo.findUserBloodsugar(user.id, id)?.let { userBloodsugar ->
            try {
                bloodSugarRepo.findUserBloodsugar(user.id, request.inputDate, request.inputType)?.firstOrNull { it.id != id }?.let {
                    bloodSugarRepo.deleteUserBloodsugar(it.userId, it.id)
                }

                bloodSugarRepo.saveUserBloodsugar(userBloodsugar.apply {
                    inputDate = request.date
                    inputType = request.inputType
                    inputTime = request.time
                    bloodSugar = request.bloodsugar.encrypt(aesProperties.aesKey)
                })
            } catch (e: Exception) {
                throw CustomException(StatusCode.FAIL_UPDATE)
            }

            CustomDto()
        } ?: throw CustomException(StatusCode.NOT_FOUND_RECORD)
    }

    fun doDeleteBloodsugar(userId: Long, id: Long): CustomDto =
        bloodSugarRepo.findUserBloodsugar(userId, id)?.let { userBloodsugar ->
            try {
                bloodSugarRepo.deleteUserBloodsugar(userBloodsugar.userId, userBloodsugar.id)
            } catch (e: Exception) {
                throw CustomException(StatusCode.FAIL_DELETE)
            }

            CustomDto()
        } ?: throw CustomException(StatusCode.NOT_FOUND_RECORD)
}
