package com.doinglab.sangsik.annotations

import com.doinglab.sangsik.api.domains.common.dto.CustomDto
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@ApiResponses(
    value = [
        ApiResponse(responseCode = "4xx/5xx", description = "400: Bad Request<br />401: Unauthorized<br />403: Forbidden<br />404: Not Found<br />405: Method Not Allowed<br />500: Internal Server Error<br />502: Bad Gateway", content = [Content(schema = Schema(implementation = CustomDto::class))])
    ]
)
annotation class CommonApiResponses
