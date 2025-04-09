package com.doinglab.sangsik.Exception

import com.doinglab.sangsik.enums.ErrorCode

class RequestDataInvalidException : DoingCommonException {
    constructor() : super(ErrorCode.INVALID_DATA.message, ErrorCode.INVALID_DATA)
    constructor(message: String) : super(message, ErrorCode.INVALID_DATA)
}

class NotAllowedFileTypeException : DoingCommonException {
    constructor() : super(ErrorCode.NOT_ALLOW_FILE_TYPE.message, ErrorCode.NOT_ALLOW_FILE_TYPE)
    constructor(message: String) : super(message, ErrorCode.NOT_ALLOW_FILE_TYPE)
}

class NotAllowedAccessException: DoingCommonException {
    constructor(): super(ErrorCode.NOT_ACCEPTABLE.message, ErrorCode.NOT_ACCEPTABLE)
    constructor(message: String): super(message, ErrorCode.NOT_ACCEPTABLE)
}

class NotFoundException: DoingCommonException {
    constructor(): super(ErrorCode.NOT_FOUND.message, ErrorCode.NOT_FOUND)
    constructor(message: String): super(message, ErrorCode.NOT_FOUND)
}

class UserNotFoundException: DoingCommonException {
    constructor(): super(ErrorCode.USER_NOT_FOUND_EXCEPTION.message, ErrorCode.USER_NOT_FOUND_EXCEPTION)
    constructor(message: String): super(message, ErrorCode.USER_NOT_FOUND_EXCEPTION)
}

class EmailAlreadyRegisteredException : DoingCommonException {
    constructor() : super(ErrorCode.EMAIL_ALREADY.message, ErrorCode.EMAIL_ALREADY)
    constructor(message: String) : super("${ErrorCode.EMAIL_ALREADY.message} $message", ErrorCode.EMAIL_ALREADY)
}

class UnauthorizedException: DoingCommonException {
    constructor(): super(ErrorCode.UNAUTHORIZED.message, ErrorCode.UNAUTHORIZED)
    constructor(message: String): super(message, ErrorCode.UNAUTHORIZED)
}

class ServerException: DoingCommonException {
    constructor(): super(ErrorCode.SERVER_ERROR.message, ErrorCode.SERVER_ERROR)
    constructor(message: String): super("${ErrorCode.SERVER_ERROR.message} $message", ErrorCode.SERVER_ERROR)
}

class ExpiredCodeException: DoingCommonException {
    constructor(): super(ErrorCode.EXPIRED_CODE.message, ErrorCode.EXPIRED_CODE)
    constructor(message: String): super("${ErrorCode.EXPIRED_CODE.message} $message", ErrorCode.EXPIRED_CODE)
}

class UnsamedException: DoingCommonException {
    constructor(): super(ErrorCode.UNSAMED.message, ErrorCode.UNSAMED)
    constructor(message: String): super("${ErrorCode.UNSAMED.message} $message", ErrorCode.UNSAMED)
}
