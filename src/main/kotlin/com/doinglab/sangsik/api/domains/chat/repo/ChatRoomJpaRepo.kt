package com.doinglab.sangsik.api.domains.chat.repo

import com.doinglab.sangsik.api.domains.chat.entity.ChatRoom
import org.springframework.data.jpa.repository.JpaRepository

interface ChatRoomJpaRepo: JpaRepository<ChatRoom, Long>
