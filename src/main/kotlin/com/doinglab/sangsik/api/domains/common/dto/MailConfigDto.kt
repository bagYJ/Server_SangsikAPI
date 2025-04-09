package com.doinglab.sangsik.api.domains.common.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("mail")
data class MailConfigDto(
    var from: String = "",
    @JsonProperty("password-code-limit")
    var passwordCodeLimit: Long = 0L,
    var ko: MailType = MailType(),
    var en: MailType = MailType()
) {
    data class MailType(
        var personal: String = "",
        var address: String = "",
        @JsonProperty("check-email")
        var checkEmail: MailResource = MailResource(),
        @JsonProperty("reset-password-code")
        var resetPasswordCode: MailResource = MailResource()
    ) {
        data class MailResource(
            var title: String = "",
            var resource: String = ""
        )
    }
}
