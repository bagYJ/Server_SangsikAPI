package com.doinglab.sangsik.config.component

import com.doinglab.sangsik.Exception.UnauthorizedException
import com.doinglab.sangsik.annotations.Auth
import com.doinglab.sangsik.api.domains.user.entity.User
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class AuthArgumentResolver: HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(Auth::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): User.Dto? {
        val request = webRequest.getNativeRequest(HttpServletRequest::class.java) as HttpServletRequest
        return request.getAttribute("auth") as User.Dto? ?: if (request.requestURI.startsWith("/announcement")) null else throw UnauthorizedException()
    }
}
