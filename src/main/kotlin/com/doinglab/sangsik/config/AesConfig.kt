package com.doinglab.sangsik.config

import com.doinglab.sangsik.api.aes.dto.AesDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AesConfig {
    @Bean("aesProperties")
    fun aesProperties(
        @Value("\${aesEncrypt.key}") encryptKey: String,
        @Value("\${aesEncrypt.iv}") encryptIv: String,
        @Value("\${aesDecrypt.key}") decryptKey: String
    ): AesDto = AesDto(encryptKey, encryptIv, decryptKey)
}
