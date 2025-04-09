package com.doinglab.sangsik.enums

import com.fasterxml.jackson.annotation.JsonValue

enum class StatusCode(@get:JsonValue val code: String) {
    SUCCESS(""),

    ALREADY_RECORD("already.record"),
    ALREADY_EMAIL("already.email"),
    ALREADY_ENROLLED_PROGRAM("already.enrolled.program"),
    ALREADY_FAVORITE("already.favorite"),
    ALREADY_REPEAT("already.repeat"),

    BAD_REQUEST("bad.request"),
    BAD_GATEWAY("bad.gateway"),

    CHECK_HEIGHT_MIN("check.height.min"),
    CHECK_HEIGHT_MAX("check.height.max"),
    CHECK_WEIGHT_MIN("check.weight.min"),
    CHECK_WEIGHT_MAX("check.weight.max"),
    CHECK_TODAY_OVER("check.today.over"),
    CHECK_EMAIL_PATTERN("check.email.pattern"),
    CHECK_CODE_LENGTH("check.code.length"),
    CHECK_PASSWORD_LENGTH("check.password.length"),
    CHECK_QUESTION_ID("check.question.id"),
    CHECK_ANSWER_ID("check.answer.id"),
    CHECK_TERMS_OF_SERVICE_AGREE("check.terms.of.service.agree"),
    CHECK_COLLECTION_PERSONAL_INFORMATION_AGREE("check.collection.personal.information.agree"),
    CHECK_PERSONAL_INFORMATION_AGREE("check.personal.information.agree"),
    CHECK_OVER_AGE_14_AGREE("check.over.age.14.agree"),
    CHECK_LOGIN_SOURCE_TYPE("check.login.source.type"),
    CANNOT_REGIST_PROGRAM("cannot.regist.program"),
    CHECK_OVER_FILE("check.over.file"),

    DATE_LESS_THEN_JOIN("date.less.then.join"),
    DATE_LESS_THEN_START("date.less.then.start"),
    DATE_GREATER_THEN_NOW("date.greater.then.now"),

    EXPIRED_CODE("expired.code"),
    EMPTY_CODE("empty.code"),
    EMPTY_EMAIL("empty.email"),
    EMPTY_PASSWORD("empty.password"),
    EMPTY_LOGIN_SOURCE("empty.login.source"),
    EMPTY_SOCIAL_ID("empty.social.id"),
    EMPTY_NAME("empty.name"),
    EMPTY_AUTH_CODE("empty.auth.code"),
    EMPTY_FOOD_NAME("empty.food.name"),
    EMPTY_SEQ("empty.seq"),
    EMPTY_LIST("empty.list"),

    FAIL_INSERT("fail.insert"),
    FAIL_UPDATE("fail.update"),
    FAIL_DELETE("fail.delete"),
    FAIL_QUEUE_STATUS_UPDATE("fail.queue.status.update"),

    GREATER_THEN_999("greater.then.999"),

    LESS_THEN_1("less.then.1"),

    METHOD_NOT_ALLOWED("method.not.allowed"),

    NOT_FOUND("not.found"),
    NOT_FOUND_USER("not.found.user"),
    NOT_FOUND_MEAL("not.found.meal"),
    NOT_FOUND_RECORD("not.found.record"),
    NOT_FOUND_CHAT_ROOM("not.found.chat.room"),
    NOT_SAME_AUTH_CODE("not.same.auth.code"),
    NOT_SAME_PASSWORD("not.same.password"),
    NO_CHAT("no.chat"),
    NO_MATCH_DIET_PROGRAM_CODE("no.match.diet.program.code"),
    NOT_FOUND_DIET_PROGRAM("not.found.diet.program"),
    NOT_OPERATED("not.operated"),
    NO_APPLY_CODE("no_apply.code"),
    NOT_FOUND_FAVORITE("not.found.favorite"),
    NOT_FOUND_REPEAT("not.found.repeat"),
    NOT_FOUND_FOOD("not.found.food"),
    NOT_FOUND_ANNOUNCEMENT("not.found.announcement"),

    SERVER_ERROR("server.error"),

    UNSAMED("unsamed"),
    UNSAMED_CODE("unsamed.code"),
    UNSAMED_PASSWORD("unsamed.password"),
    UNAUTHORIZED("unauthorized"),
    UNDER_14_YEARS("under.14.years")
    ;

    companion object {
        val DB_FAIL = listOf(FAIL_UPDATE, FAIL_INSERT, FAIL_DELETE)
    }
}
