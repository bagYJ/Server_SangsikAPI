package com.doinglab.sangsik.config

import com.doinglab.sangsik.api.domains.auth.service.HttpAuthService
import com.doinglab.sangsik.config.component.AuthArgumentResolver
import com.doinglab.sangsik.utils.getRemoteAddress
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    private val authArgumentResolver: AuthArgumentResolver
): WebMvcConfigurer {
    @Autowired
    lateinit var interceptor: HttpInterceptor
    @Value("\${spring.profiles.active}")
    private val profile: String? = null

    override fun addInterceptors(registry: InterceptorRegistry) {
        // 운영에서 swagger 작동 안하게 설정
        val matchUrls = if (!this.profile.isNullOrEmpty() && this.profile != "prod") {
            arrayOf("/", "/favicon.ico", "/error", "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**")
        } else {
            arrayOf("/", "/favicon.ico", "/error")
        }

        registry.addInterceptor(interceptor)
            .excludePathPatterns(*matchUrls)
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(authArgumentResolver)
    }

    @Component
    class HttpInterceptor(
        private val httpAuthService: HttpAuthService
    ) : HandlerInterceptor {
        override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
            MDC.put("x-forwarded-for", getRemoteAddress(request))
            MDC.put("request-method", request.method)
            MDC.put("request-uri", request.requestURI)

            return when (listOf(
                "/",
                "/elbHealthCheck",
                "/cgm/connect"
            ).contains(request.requestURI)) {
                true -> true
                false -> when(httpAuthService.httpAuth(request, response)) {
                    true -> super.preHandle(request, response, handler)
                    false -> false
                }
            }
        }

    }
}
