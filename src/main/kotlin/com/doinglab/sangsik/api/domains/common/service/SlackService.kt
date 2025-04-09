package com.doinglab.sangsik.api.domains.common.service

import com.slack.api.Slack
import com.slack.api.model.block.Blocks.*
import com.slack.api.model.block.composition.BlockCompositions.markdownText
import com.slack.api.webhook.WebhookPayloads.payload
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class SlackService {
    @Value("\${webhook.slack.url}")
    private val url: String? = null
    private val slack = Slack.getInstance()

    fun sendMessage(request: HttpServletRequest, ex: Exception) =
        slack.send(url, payload { p ->
            p.text(ex.localizedMessage)
                .blocks(listOf(
                    section { it.text(markdownText(":boom: ${ex.toString().replace(ex.localizedMessage, "").split(".").last()}")) },
                    divider(),
                    context { it.elements(listOf(markdownText("[${LocalDateTime.now()}] [${request.method}] ${request.requestURI}"))) },
                    section { it.text(markdownText("""
                        ```
                        ${ex.localizedMessage}
                        ```
                    """.trimIndent())) }
                ))
        })
}
