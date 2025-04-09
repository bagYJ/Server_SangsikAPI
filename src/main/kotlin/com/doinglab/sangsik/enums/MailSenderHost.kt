package com.doinglab.sangsik.enums

enum class MailSenderHost(val host: String, val authName: String, val authPassword: String) {
    DOINGLAB("mail.doinglab.com", "", ""),
    AWS_SES_SEOUL_DOINGLAB("email-smtp.ap-northeast-2.amazonaws.com", "AKIAXWIQW7Z7M7ORGEM4", "BOA/sjkzbV7Ed/oba+HfSgG/aq+iRq23hi+k2EieCNnx")
}
