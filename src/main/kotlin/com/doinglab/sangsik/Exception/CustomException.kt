package com.doinglab.sangsik.Exception

import com.doinglab.sangsik.enums.StatusCode

class CustomException: RuntimeException {
    val statusCode: StatusCode

    constructor(statusCode: StatusCode) : super(statusCode.code) {
        this.statusCode = statusCode
    }

    constructor(message: String?, statusCode: StatusCode) : super(message) {
        this.statusCode = statusCode
    }
}
