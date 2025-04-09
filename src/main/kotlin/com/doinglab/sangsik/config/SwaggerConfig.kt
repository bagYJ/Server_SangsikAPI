package com.doinglab.sangsik.config

import com.doinglab.sangsik.annotations.Auth
import com.doinglab.sangsik.annotations.RequireNotAuth
import com.doinglab.sangsik.define.CoreDefine.Companion.ACCESSTOKEN_HEADER
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.media.StringSchema
import io.swagger.v3.oas.models.parameters.Parameter
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springdoc.core.customizers.OperationCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@Configuration
@EnableWebMvc
@Profile("!prod")
class SwaggerConfig {
    private val securitySchemesName: String = "Auth"

    @Bean
    fun swaggerApi(): OpenAPI? =
        OpenAPI().info(
            Info().title("DoingLab SangSik Service API")
                .description("두잉랩 상식 서비스 API")
                .version("V1")
                .contact(Contact().name("DoingLab").url("https://sangsik.api.doinglab.com"))
            )
            .addSecurityItem(SecurityRequirement().addList(securitySchemesName))
            .components(
                Components().addSecuritySchemes(
                    securitySchemesName,
                    SecurityScheme().name(securitySchemesName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("Basic")
                )
            )

    @Bean
    fun operationCustomerizer(): OperationCustomizer =
        OperationCustomizer { operation, handlerMethod ->
            if (handlerMethod.methodParameters.any { it.hasParameterAnnotation(Auth::class.java) }) {
                val tokenParam = Parameter()
                    .`in`(ParameterIn.HEADER.toString())
                    .schema(StringSchema().name(ACCESSTOKEN_HEADER))
                    .name(ACCESSTOKEN_HEADER)
                    .description(ACCESSTOKEN_HEADER)
                    .required(true).apply {
                        if (handlerMethod.methodParameters.any { it.hasParameterAnnotation(RequireNotAuth::class.java) }) {
                            required(false)
                        }
                    }

                operation.addParametersItem(tokenParam)
            }

            operation
        }
}
