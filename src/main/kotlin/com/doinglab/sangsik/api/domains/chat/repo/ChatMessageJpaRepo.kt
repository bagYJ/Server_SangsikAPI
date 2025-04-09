package com.doinglab.sangsik.api.domains.chat.repo

import com.doinglab.sangsik.api.domains.chat.entity.ChatMessage
import org.springframework.data.jpa.repository.JpaRepository

interface ChatMessageJpaRepo: JpaRepository<ChatMessage, Long>
