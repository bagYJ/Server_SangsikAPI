package com.doinglab.sangsik.api.amazon.mq

import com.doinglab.sangsik.utils.getLogger
import org.springframework.beans.factory.annotation.Value
import org.springframework.jms.core.JmsTemplate
import org.springframework.stereotype.Service
import java.util.UUID.randomUUID

@Service
class ActiveMQService(
    private val jmsTemplate: JmsTemplate
) {
    private val logger = getLogger()

    @Value("\${activeMQ.queueName}")
    private lateinit var queueName: String

    // message type 을 둘 필요가 있을까? notification, chat, ...
    fun sendMessage(
        message: String,
        channelId: Int,
        groupId: String,
        queue: String? = null,
        userUUID: String? = null,
    ) {
        jmsTemplate.convertAndSend(queue ?: queueName, message) {
            val uuid = userUUID ?: randomUUID().toString()

            logger.error("message will be sent uuid : $uuid GroupID $groupId")
            it.setStringProperty("JMSXGroupID", groupId)
            it.setStringProperty("JMS_SQS_DeduplicationId", uuid)
            it.setIntProperty("channelID", channelId)
            it
        }
    }
}
