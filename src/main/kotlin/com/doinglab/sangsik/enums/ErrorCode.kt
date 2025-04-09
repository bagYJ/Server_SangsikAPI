package com.doinglab.sangsik.enums

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.*

enum class ErrorCode(val status: HttpStatus, val code: String, val message: String, val statusCode: StatusCode) {

    // <editor-fold desc="공통">
    INVALID_DATA(BAD_REQUEST, "400", "Request data is invalid.", StatusCode.BAD_REQUEST),
    COMMON_BAD_REQUEST(BAD_REQUEST, "C004", "Bad Request", StatusCode.BAD_REQUEST),
    NOT_FOUND(HttpStatus.NOT_FOUND, "404", "Not found data.", StatusCode.NOT_FOUND),
    NOT_ACCEPTABLE(HttpStatus.NOT_ACCEPTABLE, "406", "Insufficient access.", StatusCode.NOT_FOUND),
    ALREADY(CONFLICT, "409", "Already data.", StatusCode.SERVER_ERROR),
    NOT_ALLOW_FILE_TYPE(UNSUPPORTED_MEDIA_TYPE, "415", "Unacceptable file format.", StatusCode.SERVER_ERROR),

    SERVER_ERROR(INTERNAL_SERVER_ERROR, "500", "Internal server error.", StatusCode.SERVER_ERROR),

    COMMON_INTERNAL_SERVER_ERROR(INTERNAL_SERVER_ERROR, "C500", "Unexpected Internal Error", StatusCode.SERVER_ERROR),
    // </editor-fold>

    STAFF_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "A001", "Staff Not Found.", StatusCode.SERVER_ERROR),

    EMAIL_ALREADY(CONFLICT, "D409", "Email Already Registered.", StatusCode.SERVER_ERROR),
    EXPIRED_CODE(BAD_REQUEST, "400", "Expired code", StatusCode.EXPIRED_CODE),
    UNSAMED(BAD_REQUEST, "400", "unsamed", StatusCode.UNSAMED),
    USER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "D404", "User Not Found.", StatusCode.NOT_FOUND_USER),

    COMMUNITY_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "D406", "Community Not Found.", StatusCode.SERVER_ERROR),
    COMMUNITY_BAD_USER_EXCEPTION(BAD_REQUEST, "D415", "Bad User.", StatusCode.SERVER_ERROR),

    DIETPROGRAM_ENROLLED_USER_EXCEPTION(HttpStatus.NOT_FOUND, "D190", "DietProgramEnrolledUser Not Found.", StatusCode.SERVER_ERROR),

    DIET_PROGRAM_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "D110", "DietProgram Not Found.", StatusCode.SERVER_ERROR),
    DIET_PROGRAM_DETAIL_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "D111", "DietProgram Detail Not Found.", StatusCode.SERVER_ERROR),
    DIET_PROGRAM_NOT_IN_SERVICE_EXCEPTION(BAD_REQUEST, "D118", "Diet Program Not In Service.", StatusCode.SERVER_ERROR),
    DIET_PROGRAM_INVALID_ENROLL_TYPE_EXCEPTION(BAD_REQUEST, "D119", "Diet Program Invalid Enroll Type.", StatusCode.SERVER_ERROR),
    DIET_PROGRAM_CANNOT_RE_REGISTER_EXCEPTION(CONFLICT, "D120", "Diet Program Cannot Re Register.", StatusCode.SERVER_ERROR),
    DIET_PROGRAM_INVALID_CODE_EXCEPTION(BAD_REQUEST, "D121", "Diet Program Invalid Code On Register.", StatusCode.SERVER_ERROR),

    ROOM_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "D200", "ChatRoom Not Found.", StatusCode.SERVER_ERROR),

    USER_ESSENTIAL_AGREEMENT_NOT_AGREED_EXCEPTION(BAD_REQUEST, "D301", "User Essential Agreement Must Be All Agreed.", StatusCode.SERVER_ERROR),

    PAYMENT_INVALID_PROGRAM_TYPE_EXCEPTION(BAD_REQUEST, "D102", "Payment Invalid Program Type.", StatusCode.SERVER_ERROR),

    DIET_PROGRAM_USER_ALREADY_IN_PROGRESS_EXCEPTION(CONFLICT, "D115", "Diet Program User Already In Progress.", StatusCode.SERVER_ERROR),

    DIET_PROGRAM_ENROLLED_INFO_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "D300", "Program History Not Found.", StatusCode.SERVER_ERROR),

    COUPON_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "D002", "Coupon Not Found.", StatusCode.SERVER_ERROR),
    COUPON_DUPLICATED_REGISTER_BY_OWNER_EXCEPTION(CONFLICT, "D010", "Coupon Duplicated Register By Owner.", StatusCode.SERVER_ERROR),
    COUPON_ALREADY_OWNED_EXCEPTION(BAD_REQUEST, "D009", "Coupon Already Owned By User.", StatusCode.SERVER_ERROR),
    COUPON_ALREADY_USED_EXCEPTION(BAD_REQUEST, "D003", "Coupon Already Used.", StatusCode.SERVER_ERROR),
    COUPON_ALREADY_RESERVED_EXCEPTION(BAD_REQUEST, "D004", "Coupon Already Reserved.", StatusCode.SERVER_ERROR),
    COUPON_INVALID_OWNER_EXCEPTION(BAD_REQUEST, "D005", "Coupon This User Is Not Owner.", StatusCode.SERVER_ERROR),
    COUPON_EXPIRED_EXCEPTION(BAD_REQUEST, "D006", "Coupon Expired.", StatusCode.SERVER_ERROR),
    COUPON_INVALID_TOTAL_AMOUNT_FOR_PAYMENT(BAD_REQUEST, "D007", "Coupon Invalid Total Amount For Payment.", StatusCode.SERVER_ERROR),

    USER_ESSENTIAL_AGREEMENTS_NOT_AGREED_EXCEPTION(BAD_REQUEST, "D301", "User Essential Agreements Must Be All Agreed.", StatusCode.SERVER_ERROR),

    PAYMENT_HISTORY_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "D800", "PaymentHistory Not Found.", StatusCode.SERVER_ERROR),

    SURVEY_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "C080", "Survey Not Found.", StatusCode.SERVER_ERROR),

    CHAT_STAFF_NOTICE_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "D001", "Chat Staff Notice Not Found.", StatusCode.SERVER_ERROR),

    // Admin
    ADMIN_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "A001", "Admin Not Found.", StatusCode.SERVER_ERROR),
    ADMIN_ACCESS_DENIED_EXCEPTION(FORBIDDEN, "A002", "Admin Access Denied.", StatusCode.SERVER_ERROR),

    // User Dormant
    NOT_EMAIL_PATTERN_EXCEPTION(BAD_REQUEST, "D400", "Not Email Pattern.", StatusCode.SERVER_ERROR),
    NOT_SAME_AUTH_CODE_EXCEPTION(BAD_REQUEST, "D401", "Not Same Auth Code.", StatusCode.SERVER_ERROR),
    FAIL_UPDATE_EXCEPTION(HttpStatus.NOT_FOUND, "D402", "Fail Update.", StatusCode.SERVER_ERROR),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "401", "Unauthorized", StatusCode.UNAUTHORIZED),

//    REGIST_FAIL(OK, "")
}
