package com.doinglab.sangsik.api.domains.staff.entity

import com.doinglab.sangsik.config.converter.NumericBooleanConverter
import com.doinglab.sangsik.enums.FoodlensVersion
import com.doinglab.sangsik.enums.Status
import jakarta.persistence.*

@Entity(name = "Staff")
class Staff(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val appName: String,
    val account: String,
    val password: String,
    val name: String,
    val email: String,
    val roleId: Int,
    val pushToken: String,
    val partnerCompanyId: Long,
    val description: String,
    val imageURL: String,
    val profile: String,
    @Convert(converter = NumericBooleanConverter::class)
    val isShow: Boolean,
    val showPriority: Int,
    val bigImageURL: String,
    @Convert(converter = NumericBooleanConverter::class)
    val canCall: Boolean,
    @Convert(converter = NumericBooleanConverter::class)
    val canChat: Boolean,
    val hashTag: String,
    @Convert(converter = NumericBooleanConverter::class)
    val isImmediate: Boolean,
    @Convert(converter = FoodlensVersion.Converter::class)
    val foodlensVersion: FoodlensVersion,
    @Convert(converter = Status.Converter::class)
    val status: Status
) {
    data class Dto(
        val id: Long,
        val appName: String,
        val account: String,
        val password: String,
        val name: String,
        val email: String,
        val roleId: Int,
        val pushToken: String,
        val partnerCompanyId: Long,
        val description: String,
        val imageURL: String,
        val profile: String,
        val isShow: Boolean,
        val showPriority: Int,
        val bigImageURL: String,
        val canCall: Boolean,
        val canChat: Boolean,
        val hashTag: String,
        val isImmediate: Boolean,
        val foodlensVersion: FoodlensVersion,
        val status: Status
    )

    fun toDto() = Dto(id, appName, account, password, name, email, roleId, pushToken, partnerCompanyId, description, imageURL, profile, isShow, showPriority, bigImageURL, canCall, canChat, hashTag, isImmediate, foodlensVersion, status)
}
