package com.doinglab.sangsik.utils

import com.doinglab.sangsik.enums.MailSenderFrom
import com.doinglab.sangsik.enums.MailSenderHost
import jakarta.mail.internet.MimeUtility
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.web.multipart.MultipartFile

object UtilsMail {
    private val logger = getLogger()

    fun send(to: String, subject: String, contents: String) = send(listOf(to), subject, contents, null)
    fun send(to: String, subject: String, contents: String, files: List<MultipartFile>?) = send(listOf(to), subject, contents, files)
    fun send(to: List<String>, subject: String, contents: String, files: List<MultipartFile>?) =
        sendMail(
            to.map { MailSendModel(it) },
            subject,
            contents,
            files
        )

    private fun sendMail(
        to: List<MailSendModel>,
        subject: String,
        contents: String,
        files: List<MultipartFile>?
    ) {
        try {
            val sender = JavaMailSenderImpl()
            val mail = sender.createMimeMessage()
            var multipart = false

            if (files != null) {
                multipart = true
            }

            val helper = MimeMessageHelper(mail, multipart, Charsets.UTF_8.name())

            if (
                to.any {
                    it.to.lastIndexOf("@hotmail.") > 0 || it.to.lastIndexOf("@outlook.") > 0 ||
                            it.to.lastIndexOf("@msn.") > 0 || it.to.lastIndexOf("@live.") > 0 ||
                            it.to.lastIndexOf("@microsoft.") > 0 || it.to.lastIndexOf("@live.") > 0
                }
            ) {
                sender.host = MailSenderHost.AWS_SES_SEOUL_DOINGLAB.host
                sender.username = MailSenderHost.AWS_SES_SEOUL_DOINGLAB.authName
                sender.password = MailSenderHost.AWS_SES_SEOUL_DOINGLAB.authPassword
            } else {
                sender.host = MailSenderHost.DOINGLAB.host
            }

//            logger.info("host [${sender.host}] mail [${to.map { it.to }}]")

            helper.setFrom(MailSenderFrom.SUPPORT_DOINGLAB.email, MailSenderFrom.SUPPORT_DOINGLAB.personal)

            helper.setSubject(subject)
            helper.setText(contents, true)

            // <editor-fold desc="첨부파일 업로드">
            files?.filter { it.size != 0L || !it.originalFilename.isNullOrEmpty() }?.map {

                helper.addAttachment(
                    MimeUtility.encodeText(it.originalFilename, "EUC-KR", "B")
                ) { it.inputStream }

            }
            //</editor-fold>

            to.forEach {
                helper.setTo(it.to)
                if (it.cc.size > 0) helper.setCc(it.cc.toTypedArray())
                if (it.bcc.size > 0) helper.setBcc(it.bcc.toTypedArray())
                sender.send(mail)
            }

        } catch (e: Exception) {
            logger.error("UtilsMail.sendMail Exception", e)
        }
    }

    data class MailSendModel(
        val to: String,
        var cc: ArrayList<String> = arrayListOf(),
        var bcc: ArrayList<String> = arrayListOf(),
    )
}
