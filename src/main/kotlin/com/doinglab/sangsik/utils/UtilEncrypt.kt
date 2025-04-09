package com.doinglab.sangsik.utils

import org.apache.commons.codec.binary.Hex
import java.nio.charset.StandardCharsets
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

object UtilEncrypt

fun String.generateKey(): SecretKeySpec =
    ByteArray(16).let {
        val rawKey = this.toByteArray(Charsets.UTF_8)
        System.arraycopy(rawKey, 0, it, 0, if (rawKey.size > it.size) it.size else rawKey.size)
        SecretKeySpec(it, "AES")
    }

fun String.encrypt(key: String): String =
    Cipher.getInstance("AES").let {
        it.init(Cipher.ENCRYPT_MODE, key.generateKey())
        Hex.encodeHexString(it.doFinal(this.toByteArray(StandardCharsets.UTF_8))).uppercase()
    }

fun Int.encrypt(key: String): String =
    this.toString().encrypt(key)

fun Long.encrypt(key: String): String =
    this.toString().encrypt(key)

fun String.decrypt(key: String): String =
    Cipher.getInstance("AES").let {
        it.init(Cipher.DECRYPT_MODE, key.generateKey())
        String(it.doFinal(Hex.decodeHex(this.toCharArray())))
    }

