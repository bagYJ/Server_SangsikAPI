package com.doinglab.sangsik.config.component

import com.doinglab.sangsik.api.domains.common.service.SlackService
import com.doinglab.sangsik.utils.getLogger
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes
import org.springframework.stereotype.Component
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.NoHandlerFoundException
import java.time.LocalDateTime

@Component
class ErrorAttributes(
    private val slackService: SlackService
): DefaultErrorAttributes() {
    private val logger = getLogger()

    override fun resolveException(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any?,
        ex: Exception
    ): ModelAndView? {

        val exLog = mutableMapOf<String, String>().apply {
            put("EXCEPTION", ex.toString().replace(ex.localizedMessage, "").split(".").last())
            put("URI", "[${LocalDateTime.now()}] [${request.method}] ${request.requestURI}")
            put("MESSAGE", ex.localizedMessage)
        }
        logger.error(exLog["URI"], exLog)
        logger.error(ex.message, ex)

        if (ex !is NoHandlerFoundException) {
            slackService.sendMessage(request, ex)
//            Sentry.captureException(ex)
        }

        return super.resolveException(request, response, handler, ex)
    }
}
