package com.doinglab.sangsik.api.domains.user.service

import com.doinglab.sangsik.Exception.CustomException
import com.doinglab.sangsik.api.domains.common.dto.CustomDto
import com.doinglab.sangsik.api.domains.user.dto.request.RequestPostWeightDto
import com.doinglab.sangsik.api.domains.user.dto.response.ResponseGetWeightDto
//import com.doinglab.sangsik.api.domains.user.dto.response.ResponseGetWeightGraphDto
import com.doinglab.sangsik.api.domains.user.entity.User
import com.doinglab.sangsik.api.domains.user.entity.UserHistory
import com.doinglab.sangsik.api.domains.user.repo.UserRepo
import com.doinglab.sangsik.enums.StatusCode
import com.doinglab.sangsik.utils.weight
import org.springframework.transaction.annotation.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
@Transactional
class WeightService(
    private val userRepo: UserRepo
) {
    fun doGetWeight(user: User.Dto, date: LocalDate): ResponseGetWeightDto =
        date.let { start ->
            (0..6).map {
                start.minusDays(it.toLong())
            }.sorted().let { dates ->
                val userHistories = userRepo.findUserHistory(user.id, dates)
                val firstWeight = userHistories?.firstOrNull { it.date == dates.first() }?.let {
                    it.weight.weight(it.unitWeight, user.unitWeight)
                } ?: userRepo.findRecentUserHistory(user.id, dates.first())?.let {
                    it.weight.weight(it.unitWeight, user.unitWeight)
                } ?: 0F

                ResponseGetWeightDto(user, dates, userHistories, firstWeight)
            }
        }

    fun doPostWeight(user: User.Dto, request: RequestPostWeightDto): CustomDto =
        (userRepo.findUserHistory(user.id, listOf(request.inputDate))?.firstOrNull() ?: UserHistory.Dto(
            id = 0L,
            userId = user.id,
        )).let { userHistory ->
            userRepo.saveUserHistory(userHistory.apply {
                unitWeight = user.unitWeight
                date = request.inputDate
                weight = request.weight
            })?.let {
                if (it.id < 1) throw CustomException(StatusCode.FAIL_INSERT)
            }

            CustomDto()
        }

    fun doPutWeight(user: User.Dto, id: Long, request: RequestPostWeightDto): CustomDto =
        userRepo.findUserHistory(user.id, id)?.let { userHistory ->
            userRepo.saveUserHistory(userHistory.apply {
                unitWeight = user.unitWeight
                date = request.inputDate
                weight = request.weight
            })?.let {
                if (it.id < 1) throw CustomException(StatusCode.FAIL_UPDATE)
            }

            CustomDto()
        } ?: throw CustomException(StatusCode.NOT_FOUND_RECORD)
}
