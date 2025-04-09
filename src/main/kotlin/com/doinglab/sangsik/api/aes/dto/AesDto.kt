package com.doinglab.sangsik.api.aes.dto

import com.doinglab.sangsik.define.CoreDefine
import com.doinglab.sangsik.define.CoreDefine.Companion.AES
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

data class AesDto(
    val encryptKey: String,
    val encryptIv: String,
    val decryptKey: String
) {
    val aesKey =
        Cipher.getInstance(CoreDefine.ALTHORITHM).let { cipher ->
            val ivSpec = IvParameterSpec(Base64.getDecoder().decode(encryptIv))
            cipher.init(Cipher.DECRYPT_MODE, Base64.getDecoder().decode(decryptKey).let {
                SecretKeySpec(it, 0, it.size, AES)
            }, ivSpec)

            String(cipher.doFinal(Base64.getDecoder().decode(encryptKey)), Charsets.UTF_8)
        }
}
