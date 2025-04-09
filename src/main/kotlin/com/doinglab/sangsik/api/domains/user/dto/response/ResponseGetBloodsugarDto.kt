package com.doinglab.sangsik.api.domains.user.dto.response

import com.doinglab.sangsik.api.domains.cgm.entity.UserCgmBloodGlucose
import com.doinglab.sangsik.api.domains.common.dto.BaseDto
import com.doinglab.sangsik.api.domains.meal.entity.EatHistory
import com.doinglab.sangsik.api.domains.user.dto.response.ResponseGetBloodsugarDto.GetBloodsugarBody.BloodsugarRecord
import com.doinglab.sangsik.api.domains.user.dto.response.ResponseGetBloodsugarDto.GetBloodsugarBody.UserBloodsugar
import com.doinglab.sangsik.api.domains.user.entity.UserBloodGlucose
import com.doinglab.sangsik.define.CoreTime.Companion.HHMMSS
import com.doinglab.sangsik.define.CoreTime.Companion.YYYYMMDD
import com.doinglab.sangsik.enums.BloodSugarInputType
import com.doinglab.sangsik.enums.MealTime
import com.doinglab.sangsik.enums.MealType
import com.doinglab.sangsik.utils.between
import com.doinglab.sangsik.utils.decrypt
import com.doinglab.sangsik.utils.range
import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalTime

@Schema(description = "혈당 정보")
data class ResponseGetBloodsugarDto(
    override val body: GetBloodsugarBody?
): BaseDto() {
    data class GetBloodsugarBody(
        val record: List<BloodsugarRecord>?,
        val graph: List<UserBloodsugar>?,
        @Schema(description = "cgm 연동여부")
        val isCgmBloodSugar: Boolean = false
    ) {
        data class BloodsugarRecord(
            @Schema(description = "혈당 입력 타입 (MORNING: 아침, AFTERNOON: 점심, DINNER: 저녁, BEFOREBED: 취침전, MORNINGSNACK: 오전간식, AFTERNOONSNACK: 오후간식, NIGHTSNACK: 야식)")
            val mealType: MealType,
            @Schema(description = "혈당 정보")
            val userBloodsugar: List<UserBloodsugar?>
        )

        data class UserBloodsugar(
            @Schema(description = "혈당 ID")
            val id: Long,
            @JsonIgnore
            val seqNumber: Long,
            @Schema(description = "혈당 입력 타입 (MORNING: 아침, AFTERNOON: 점심, DINNER: 저녁, BEFOREBED: 취침전, MORNINGSNACK: 오전간식, AFTERNOONSNACK: 오후간식, NIGHTSNACK: 야식)")
            val mealType: MealType?,
            @Schema(description = "혈당 입력 시간 타입 (BEFORE: 식전, AFTER_1: 식후 1시간, AFTER: 식후 2시간)")
            val mealTime: MealTime?,
            @Schema(description = "입력시간대 (BS10_BEFORE_BREAKFAST: 아침식전, BS14_AFTER_BREAKFAST: 아침식후 1시간, BS15_AFTER_BREAKFAST: 아침식후 2시간, BS16_BEFORE_MORNING_SNACK: 오전간식전, BS18_AFTER_MORNING_SNACK: 오전간식후 1시간, BS19_AFTER_MORNING_SNACK: 오전간식후 2시간, BS20_BEFORE_LUNCH: 점심식전, BS24_AFTER_LUNCH: 점심식후 1시간, BS25_AFTER_LUNCH: 점심식후 2시간, BS26_BEFORE_AFTERNOON_SNACK: 오후간식전, BS28_AFTER_AFTERNOON_SNACK: 오후간식후 1시간, BS29_AFTER_AFTERNOON_SNACK: 오후간식후 2시간, BS30_BEFORE_DINNER: 저녁식전, BS34_AFTER_DINNER: 저녁식후 1시간, BS35_AFTER_DINNER: 저녁식후 2시간, BS36_BEFORE_MIDNIGHT_SNACK: 야식전, BS38_AFTER_MIDNIGHT_SNACK: 야식후 1시간, BS39_AFTER_MIDNIGHT_SNACK: 야식후 2시간, BS90_BEFORE_BED: 취침전)")
            val inputType: BloodSugarInputType?,
            @Schema(description = "입력날짜")
            val date: String,
            @Schema(description = "입력시간")
            val time: String,
            @Schema(description = "혈당")
            val bloodSugar: Int
        )
    }

    companion object {
        fun getRecords(userBloodsugars: List<UserBloodGlucose.Dto>?, aesEncryptKey: String): List<BloodsugarRecord>? =
            userBloodsugars?.let { bloodSugars ->
                bloodSugars.sortedBy { it.inputType.mealType.order }.groupBy { it.inputType.mealType }.filter { it.value.isNotEmpty() }.map { userBloodsugar ->
                    BloodsugarRecord(
                        mealType = userBloodsugar.key,
                        userBloodsugar = userBloodsugar.value.map {
                            UserBloodsugar(
                                id = it.id,
                                seqNumber = it.id,
                                mealType = it.inputType.mealType,
                                mealTime = it.inputType.mealTime,
                                inputType = it.inputType,
                                date = it.inputDate,
                                time = it.inputTime,
                                bloodSugar = it.bloodSugar.decrypt(aesEncryptKey).toInt()
                            )
                        }
                    )
                }
            }

        fun getRecords(cgms: List<UserCgmBloodGlucose.Dto>?, meals: List<EatHistory.Dto>?, aesEncryptKey: String): Pair<List<BloodsugarRecord>?, List<Pair<Long, BloodSugarInputType?>>>? =
            cgms?.let { bloodSugars ->
                val inputTypes = mutableListOf<Pair<Long, BloodSugarInputType?>>()

                meals?.sortedBy { it.eatType.value }?.groupBy { it.eatType }?.filter { it.value.isNotEmpty() }?.values?.map { it.first() }?.map { meal ->
                    BloodsugarRecord(
                        mealType = meal.eatType.mealType,
                        userBloodsugar = MealTime.entries.mapNotNull { mealTime ->
                            cgms.firstOrNull {
                                it.eventAt.between(meal.date.plusMinutes(mealTime.minute), meal.date.plusMinutes(mealTime.minute).plusMinutes(5))
                            }?.let { bloodSugar ->
                                inputTypes.add(Pair(bloodSugar.seqNumber, BloodSugarInputType.findByMealAndTimes(meal.eatType.mealType, mealTime)))

                                UserBloodsugar(
                                    id = bloodSugar.id,
                                    seqNumber = bloodSugar.seqNumber,
                                    mealType = meal.eatType.mealType,
                                    mealTime = mealTime,
                                    inputType = BloodSugarInputType.findByMealAndTimes(meal.eatType.mealType, mealTime),
                                    date = bloodSugar.eventAt.format(YYYYMMDD),
                                    time = bloodSugar.eventAt.format(HHMMSS),
                                    bloodSugar = bloodSugar.value.decrypt(aesEncryptKey).toInt()
                                )
                            }
                        }
                    )
                }.let {
                    Pair(it, inputTypes.toList())
                }
            }

        fun getGraph(bloodSugars: List<UserBloodGlucose.Dto>?, aesEncryptKey: String): List<UserBloodsugar>? =
            bloodSugars?.sortedWith(compareBy({ it.inputTime }, { it.inputType }))?.groupBy { group ->
                LocalTime.parse(group.inputTime, HHMMSS).let {
                    it.withMinute((it.minute / 60) * 60).withSecond(0)
                }
            }?.values?.map { it.last() }?.map {
                UserBloodsugar(
                    id = it.id,
                    seqNumber = it.id,
                    mealType = it.inputType.mealType,
                    mealTime = it.inputType.mealTime,
                    inputType = it.inputType,
                    date = it.inputDate,
                    time = it.inputTime,
                    bloodSugar = it.bloodSugar.decrypt(aesEncryptKey).toInt()
                )
            }


        fun getGraph(bloodSugars: List<UserCgmBloodGlucose.Dto>?, inputTypes: List<Pair<Long, BloodSugarInputType?>>?, aesEncryptKey: String): List<UserBloodsugar>? =
            bloodSugars?.map { bloodSugar ->
                val inputType = inputTypes?.firstOrNull { inputType -> inputType.second?.mealTime?.let { bloodSugar.seqNumber in inputType.first.range(it.rangeNum) } == true }?.second

                UserBloodsugar(
                    id = bloodSugar.id,
                    seqNumber = bloodSugar.seqNumber,
                    mealType = inputType?.mealType,
                    mealTime = inputType?.mealTime,
                    inputType = inputType,
                    date = bloodSugar.eventAt.format(YYYYMMDD),
                    time = bloodSugar.eventAt.format(HHMMSS),
                    bloodSugar = bloodSugar.value.decrypt(aesEncryptKey).toInt()
                )
            }
    }

    constructor(userBloodsugars: List<UserBloodGlucose.Dto>?, aesEncryptKey: String, isCgmBloodSugar: Boolean = false): this(userBloodsugars?.let { bloodSugars ->
        GetBloodsugarBody(getRecords(userBloodsugars, aesEncryptKey), getGraph(userBloodsugars, aesEncryptKey), isCgmBloodSugar)
    })

    constructor(cgms: List<UserCgmBloodGlucose.Dto>?, meals: List<EatHistory.Dto>?, aesEncryptKey: String): this(cgms?.let { bloodSugars ->
        val records = getRecords(cgms, meals, aesEncryptKey)

        GetBloodsugarBody(records?.first, getGraph(cgms, records?.second, aesEncryptKey), true)
    })

    constructor(cgms: List<UserCgmBloodGlucose.Dto>?, userBloodsugars: List<UserBloodGlucose.Dto>?, meals: List<EatHistory.Dto>?, aesEncryptKey: String): this(cgms?.let { bloodSugars ->
        val records = getRecords(cgms, meals, aesEncryptKey)
        val graph = getGraph(cgms, records?.second, aesEncryptKey)

        GetBloodsugarBody(
            record = mutableListOf<BloodsugarRecord>().apply {
                records?.first?.let { addAll(it) }
            }.apply {
                getRecords(userBloodsugars?.filter { userBloodsugar ->
                    userBloodsugar.inputTime < cgms.minOf { it.eventAt }.format(HHMMSS)
                }?.filterNot { userBloodsugar ->
                    userBloodsugar.inputType in map { it.userBloodsugar.map { it1 -> it1?.inputType } }.flatten()
                }, aesEncryptKey)?.let { addAll(it) }
            }.groupBy { it.mealType }.filter { it.value.any { bloodSugars -> bloodSugars.userBloodsugar.isNotEmpty() } }.map { record ->
                BloodsugarRecord(
                    mealType = record.key,
                    userBloodsugar = record.value.map { bloodSugar -> bloodSugar.userBloodsugar }.flatten().sortedBy { it?.time }
                )
            }.sortedBy { it.mealType.order },
            graph = mutableListOf<UserBloodsugar>().apply {
                graph?.let { addAll(it) }
            }.apply {
                getGraph(userBloodsugars?.filter { userBloodsugar ->
                    userBloodsugar.inputTime < cgms.minOf { it.eventAt }.format(HHMMSS)
                }?.filterNot { userBloodsugar ->
                    userBloodsugar.inputType in map { it.inputType }
                }, aesEncryptKey)?.let { addAll(it) }
            }.sortedBy { it.time },
            true
        )
    })
}
