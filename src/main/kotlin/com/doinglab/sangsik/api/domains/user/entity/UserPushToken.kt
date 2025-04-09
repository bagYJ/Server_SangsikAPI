package com.doinglab.sangsik.api.domains.user.entity

import com.doinglab.sangsik.api.domains.user.dto.request.RequestPutUserTokenDto
import com.doinglab.sangsik.define.CoreDefine.Companion.APP_NAME
import com.doinglab.sangsik.enums.Platform
import com.doinglab.sangsik.enums.PushType
import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import java.io.Serializable
import java.time.LocalDateTime

@Entity(name = "UserPushToken")
@DynamicUpdate
class UserPushToken(
    @EmbeddedId
    val id: UserPushTokenId,
    @Convert(converter = Platform.Converter::class)
    val platform: Platform,
    val token: String,
    val updatedAt: LocalDateTime
) {
    data class Dto(
        val userId: Long,
        val platform: Platform,
        var token: String,
        val appName: String,
        val pushType: PushType,
        val updatedAt: LocalDateTime
    ) {
        fun toEntity() = UserPushToken(UserPushTokenId(userId, appName, pushType), platform, token, updatedAt)

        constructor(userId: Long, request: RequestPutUserTokenDto) : this(
            userId = userId,
            platform = request.platform,
            appName = APP_NAME,
            pushType = request.pushType,
            token = request.token,
            updatedAt = LocalDateTime.now()
        )
    }

    @Embeddable
    class UserPushTokenId(
        @Column(name = "user_id")
        val userId: Long,
        val appName: String,
        @Convert(converter = PushType.Converter::class)
        val pushType: PushType

    ): Serializable

    fun toDto() = Dto(id.userId, platform, token, id.appName, id.pushType, updatedAt)
}
