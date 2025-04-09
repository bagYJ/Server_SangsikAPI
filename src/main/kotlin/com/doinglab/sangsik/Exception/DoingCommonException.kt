package com.doinglab.sangsik.Exception

import com.doinglab.sangsik.enums.ErrorCode


open class DoingCommonException: RuntimeException {
    val errorCode: ErrorCode

    constructor(errorCode: ErrorCode) : super(errorCode.message) {
        this.errorCode = errorCode
    }

    constructor(message: String?, errorCode: ErrorCode) : super(message) {
        this.errorCode = errorCode
    }
}
