package com.doinglab.sangsik.enums

enum class MailSenderFrom(val email: String, val personal: String) {
    SUPPORT_DOINGLAB("support@doinglab.com", "두잉랩"),
    SUPPORT_FOODLENS("support@foodlens.ai", "푸드렌즈"),
    CUSTOMER_MAIL("admin@doinglab.com", "두잉랩")
}
