package com.doinglab.sangsik.api.amazon.mq

import com.doinglab.sangsik.api.domains.cgm.service.CgmService
import com.doinglab.sangsik.define.CoreDefine.Companion.DASH
import com.doinglab.sangsik.define.CoreDefine.Companion.MESSAGE_QUEUE
import com.doinglab.sangsik.utils.getLogger
import org.springframework.beans.factory.annotation.Value
import org.springframework.jms.annotation.JmsListener
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Service
import java.io.IOException
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@Service
class ActiveMQListenerService(
    private val cgmService: CgmService,
) {
    private val logger = getLogger()

    @Value("\${activeMQ.cgmQueue.group.scrap}")
    private lateinit var cgmGroupScrap: String
    @Value("\${activeMQ.cgmQueue.group.batch}")
    private lateinit var cgmGroupBatch: String

    @JmsListener(destination = "\${activeMQ.cgmQueue.name}")
    @Throws(IOException::class)
    fun cgmQueue(
        message: String,
        @Header("JMSXGroupID") groupId: String,
        @Header("channelID") channelId: Int,
    ) {
        if (MESSAGE_QUEUE.firstOrNull { it ==  "$channelId-$groupId"}?.isNotEmpty() == true) return
        logger.info("cgmQueue $message. GroupId: $groupId channelId: $channelId")

        when (groupId) {
            cgmGroupScrap -> {
                if (message != DASH) {
                    cgmService.connectCgm(message, channelId.toLong())
                }
                cgmService.insertUserCgmBloodGlucose(channelId.toLong())
            }
            cgmGroupBatch -> cgmService.refreshCgmTokenForStaffId(channelId.toLong())
        }
        MESSAGE_QUEUE.add("$channelId-$groupId")

        Executors.newSingleThreadScheduledExecutor().schedule({
            MESSAGE_QUEUE.remove("$channelId-$groupId")
        }, 1, TimeUnit.MINUTES)
    }
}
