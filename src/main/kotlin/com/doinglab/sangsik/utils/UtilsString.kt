package com.doinglab.sangsik.utils

import com.doinglab.sangsik.define.CoreDefine
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.InputStream
import java.security.MessageDigest
import java.util.*
import java.util.regex.Pattern
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object UtilsString

fun authKeyEncrypt(username: String, password: String, authType: String): String =
    authType + String(Base64.getEncoder().encode("$username:$password".toByteArray()))

fun String.authKeyDectypt(authType: String): String =
    String(Base64.getDecoder().decode(this.replace(authType, "")))

fun String.sha256(): String {
    val bytes = this.toByteArray()
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(bytes)
    return digest.fold("") { str, it -> str + "%02x".format(it) }
}

fun String.checkEmail(): Boolean = Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", this.trim())

fun String.aesKey(decryptKey: String, encryptIv: String): String =
    Cipher.getInstance(CoreDefine.ALTHORITHM).let { cipher ->
        val ivSpec = IvParameterSpec(Base64.getDecoder().decode(encryptIv))
        cipher.init(Cipher.DECRYPT_MODE, Base64.getDecoder().decode(decryptKey).let {
            SecretKeySpec(it, 0, it.size, "AES")
        }, ivSpec)

        String(cipher.doFinal(Base64.getDecoder().decode(this)), Charsets.UTF_8)
    }

fun InputStream.sha256(): String {
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(this.readBytes())
    return digest.fold("") { str, it -> str + "%02x".format(it) }
}

fun Any.toJsonString(): String {
    val objectMapper = jacksonObjectMapper().registerModule(JavaTimeModule())
    return objectMapper.writeValueAsString(this)
}

fun <T> String.toJsonObject(responseClass: Class<T>): T? {
    return if (this.isEmpty()) {
        null
    } else {
        jacksonObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModules(JavaTimeModule())
            .readValue(this, responseClass)
    }
}
