package com.doinglab.sangsik.annotations

import io.swagger.v3.oas.annotations.Parameter

@Target(AnnotationTarget.VALUE_PARAMETER) // 생성자 매개변수에 적용
@Retention(AnnotationRetention.RUNTIME)
@Parameter(hidden = true)
annotation class RequireNotAuth
