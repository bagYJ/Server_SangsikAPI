package com.doinglab.sangsik.config

import jakarta.jms.Session
import org.apache.activemq.ActiveMQConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.jms.annotation.EnableJms
import org.springframework.jms.config.DefaultJmsListenerContainerFactory
import org.springframework.jms.core.JmsTemplate


@Configuration
@EnableJms
@PropertySource(value= ["classpath:application.yml"])
class ActiveMQConfig {
    @Value("\${activeMQ.account}")
    private val account: String? = null

    @Value("\${activeMQ.password}")
    private val password: String? = null

    @Value("\${activeMQ.endPoint}")
    private val endPoint: String? = null

    @Bean
    fun createActiveMQConnectionFactory(): ActiveMQConnectionFactory {
        // Create a connection factory.
        val connectionFactory = ActiveMQConnectionFactory(endPoint)
        // Pass the username and password.
        connectionFactory.userName = account
        connectionFactory.password = password
        return connectionFactory
    }

    @Bean
    fun jmsListenerContainerFactory(
        connectionFactory: ActiveMQConnectionFactory?,
    ): DefaultJmsListenerContainerFactory {
        val factory = DefaultJmsListenerContainerFactory()
        factory.setConnectionFactory(connectionFactory!!)
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE)
        factory.setPubSubDomain(true)
        return factory
    }

    @Bean
    fun defaultJmsTemplate(
        connectionFactory: ActiveMQConnectionFactory?,
    ): JmsTemplate =
        JmsTemplate(connectionFactory!!).apply {
//            messageConverter(jacksonjms)
            isPubSubDomain = true
        }
}
