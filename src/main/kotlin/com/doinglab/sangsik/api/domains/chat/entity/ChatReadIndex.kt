package com.doinglab.sangsik.api.domains.chat.entity

import com.doinglab.sangsik.config.converter.NumericBooleanConverter
import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate

@Entity(name = "ChatReadIndex")
@DynamicUpdate
class ChatReadIndex(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val memberId: Long,
    @Convert(converter = NumericBooleanConverter::class)
    val isStaff: Boolean,
    val roomId: Long,
    val readIndex: Long,
) {
    data class Dto(
        val id: Long,
        val memberId: Long,
        val isStaff: Boolean,
        val roomId: Long,
        val readIndex: Long = 0L,
    ) {
        fun toEntity() = ChatReadIndex(id, memberId, isStaff, roomId, readIndex)
    }

    fun toDto() = Dto(id, memberId, isStaff, roomId, readIndex)
}
