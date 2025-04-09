package com.doinglab.sangsik.api.domains.chat.repo

import com.doinglab.sangsik.api.domains.chat.entity.ChatReadIndex
import org.springframework.data.jpa.repository.JpaRepository

interface ChatReadIndexJpaRepo: JpaRepository<ChatReadIndex, Long>
