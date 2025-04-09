package com.doinglab.sangsik.api.domains.auth.entity

import com.doinglab.sangsik.config.converter.NumericBooleanConverter
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.time.LocalDate

@Entity(name = "ClientCompany")
class ClientCompany(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val account: String,
    val password: String,
    val name: String?,
    val appName: String,
    val email: String,
    val phone: String,
    @Column(name = "plan_id")
    val planId: Long,
    @Column(name = "excessPlan_id")
    val excessPlanId: Int,
    val accessToken: String,
    @Column(name = "HttpAuthID")
    val httpAuthID: String,
    @Column(name = "HttpAuthPass")
    val httpAuthPass: String,
    @Convert(converter = NumericBooleanConverter::class)
    val tokenCheck: Boolean,
    val roleId: Int,
    @Convert(converter = NumericBooleanConverter::class)
    val deleteFlag: Boolean? = false,
    val enrollDate: LocalDate,
    val expiryDate: LocalDate,
    val nextPlan: Int?
) {
    data class Dto(
        val id: Long,
        val account: String,
        val password: String,
        val name: String?,
        val appName: String,
        val email: String,
        val phone: String,
        val planId: Long,
        val excessPlanId: Int,
        val accessToken: String,
        val httpAuthID: String,
        val httpAuthPass: String,
        val tokenCheck: Boolean,
        val roleId: Int,
        val deleteFlag: Boolean? = false,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        val enrollDate: LocalDate,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        val expiryDate: LocalDate,
        val nextPlan: Int?,
        var plan: Plan.Dto? = null,
        var nowCallCounts: List<NowCallCount.Dto>? = null
    )

    fun toDto() = Dto(id, account, password, name, appName, email, phone, planId, excessPlanId, accessToken, httpAuthID, httpAuthPass, tokenCheck, roleId, deleteFlag, enrollDate, expiryDate, nextPlan)
}
