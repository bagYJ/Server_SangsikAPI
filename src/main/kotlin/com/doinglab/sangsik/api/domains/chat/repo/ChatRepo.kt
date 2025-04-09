package com.doinglab.sangsik.api.domains.chat.repo

import com.doinglab.sangsik.api.domains.chat.entity.ChatMessage
import com.doinglab.sangsik.api.domains.chat.entity.ChatReadIndex
import com.doinglab.sangsik.api.domains.chat.entity.ChatRoom
import com.doinglab.sangsik.define.CoreDefine.Companion.CHAT_MESSAGE
import com.doinglab.sangsik.define.CoreDefine.Companion.CHAT_READ_INDEX
import com.doinglab.sangsik.define.CoreDefine.Companion.CHAT_ROOM
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository

@Repository
class ChatRepo(
    @Qualifier("dietJpaQueryFactory") private val jpaQueryFactory: JPAQueryFactory,
    private val chatRoomJpaRepo: ChatRoomJpaRepo,
    private val chatReadIndexJpaRepo: ChatReadIndexJpaRepo,
    private val chatMessageJpaRepo: ChatMessageJpaRepo
) {
    fun unreadMessage(
        userId: Long
    ): Triple<ChatRoom.Dto?, ChatMessage.Dto?, ChatReadIndex.Dto?> =
        jpaQueryFactory.select(CHAT_ROOM, CHAT_MESSAGE, CHAT_READ_INDEX)
            .from(CHAT_ROOM)
            .leftJoin(CHAT_MESSAGE)
            .on(CHAT_ROOM.id.eq(CHAT_MESSAGE.roomId).and(CHAT_ROOM.staffId.eq(CHAT_MESSAGE.writerId)))
            .leftJoin(CHAT_READ_INDEX).on(CHAT_ROOM.id.eq(CHAT_READ_INDEX.roomId).and(CHAT_READ_INDEX.isStaff.isFalse))
            .where(CHAT_ROOM.userId.eq(userId))
            .orderBy(CHAT_MESSAGE.id.desc()).limit(1).fetchOne().let { tuple ->
                Triple(
                    tuple?.get(CHAT_ROOM)?.toDto(),
                    tuple?.get(CHAT_MESSAGE)?.toDto(),
                    tuple?.get(CHAT_READ_INDEX)?.toDto()
                )
            }

    fun createChatRoom(userId: Long, staffId: Long): Long? =
        chatRoomJpaRepo.save(ChatRoom.Dto(
            id = 0L,
            userId = userId,
            staffId = staffId
        ).toEntity()).id

    fun createChatReadIndex(writerId: Long, isStaff: Boolean, roomId: Long): Long? =
        chatReadIndexJpaRepo.save(ChatReadIndex.Dto(
            id = 0L,
            isStaff = isStaff,
            memberId = writerId,
            roomId = roomId
        ).toEntity()).id

    fun findChatRoom(roomId: Long): ChatRoom.Dto? =
        jpaQueryFactory.selectFrom(CHAT_ROOM)
            .where(CHAT_ROOM.id.eq(roomId))
            .fetchOne()?.toDto()

    fun findChatMessage(roomId: Long, lastId: Long?, limit: Long): List<ChatMessage.Dto>? =
        jpaQueryFactory.selectFrom(CHAT_MESSAGE)
            .where(CHAT_MESSAGE.roomId.eq(roomId)).apply {
                lastId?.let {
                    where(CHAT_MESSAGE.id.lt(it))
                }
            }.orderBy(CHAT_MESSAGE.id.desc()).limit(limit).fetch().map { it.toDto() }

    fun findChatReadIndex(roomId: Long, isStaff: Boolean): ChatReadIndex.Dto =
        jpaQueryFactory.selectFrom(CHAT_READ_INDEX)
            .where(
                CHAT_READ_INDEX.roomId.eq(roomId)
                    .and(CHAT_READ_INDEX.isStaff.eq(isStaff))
            ).fetchFirst().toDto()

    fun saveChatMessage(chatMessage: ChatMessage.Dto): ChatMessage.Dto =
        chatMessageJpaRepo.save(chatMessage.toEntity()).toDto()

    fun findUnreadMessage(roomId: Long, isStaff: Boolean): Long =
        jpaQueryFactory.select(CHAT_READ_INDEX.count())
            .from(CHAT_READ_INDEX)
            .leftJoin(CHAT_MESSAGE).on(CHAT_READ_INDEX.roomId.eq(CHAT_MESSAGE.roomId))
            .where(
                CHAT_READ_INDEX.roomId.eq(roomId)
                    .and(CHAT_READ_INDEX.isStaff.eq(isStaff))
                    .and(CHAT_MESSAGE.id.gt(CHAT_READ_INDEX.readIndex))
            ).fetchFirst()

    fun updateChatReadIndex(chatReadIndex: ChatReadIndex.Dto): ChatReadIndex.Dto =
        chatReadIndexJpaRepo.save(chatReadIndex.toEntity()).toDto()
}
