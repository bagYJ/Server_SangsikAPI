package com.doinglab.sangsik.api.domains.user.entity

import com.doinglab.sangsik.define.CoreTime.Companion.YYYYMMDDHHMMSS
import com.doinglab.sangsik.enums.BloodSugarInputType
import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime

@Entity(name = "UserBloodGlucose")
@DynamicUpdate
class UserBloodGlucose(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val userId: Long,
    val inputDate: String,
    val inputTime: String,
    @Enumerated(EnumType.STRING)
    val inputType: BloodSugarInputType,
    val bloodSugar: String,
    val memo: String? = ""
) {
    data class Dto(
        val id: Long,
        val userId: Long,
        var inputDate: String,
        var inputTime: String = "",
        var inputType: BloodSugarInputType,
        var bloodSugar: String = "",
        var memo: String? = ""
    ) {
        val datetime: LocalDateTime = LocalDateTime.parse("$inputDate$inputTime", YYYYMMDDHHMMSS)
//        fun generateKey(key: String): SecretKeySpec =
//            ByteArray(16).let {
//                val rawKey = key.toByteArray(Charsets.UTF_8)
//                System.arraycopy(rawKey, 0, it, 0, if (rawKey.size > it.size) it.size else rawKey.size)
//                SecretKeySpec(it, "AES")
//            }
//
//        fun encrypt(key: String, value: String): String =
//            Cipher.getInstance("AES").let {
//                it.init(Cipher.ENCRYPT_MODE, generateKey(key))
//                Hex.encodeHexString(it.doFinal(value.toByteArray(StandardCharsets.UTF_8))).uppercase()
//            }
//
//        fun decrypt(key: String, value: String): String =
//            Cipher.getInstance("AES").let {
//                it.init(Cipher.DECRYPT_MODE, generateKey(key))
//                String(it.doFinal(Hex.decodeHex(value.toCharArray())))
//            }

        fun toEntity() = UserBloodGlucose(id, userId, inputDate, inputTime, inputType, bloodSugar, memo)
    }

    fun toDto() = Dto(id, userId, inputDate, inputTime, inputType, bloodSugar, memo)
}
