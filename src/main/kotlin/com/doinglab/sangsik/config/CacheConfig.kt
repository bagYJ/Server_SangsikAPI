package com.doinglab.sangsik.config

import com.doinglab.sangsik.enums.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@EnableCaching
@Configuration
class CacheConfig {
    @Bean
    fun defaultCacheConfig(): Caffeine<Any, Any> =
        Caffeine.newBuilder()
            .expireAfterWrite(1, TimeUnit.MINUTES)

    @Bean
    fun cacheCustomManager(defaultCacheConfig: Caffeine<Any, Any>): CacheManagerCustomizer<CaffeineCacheManager> =
        CacheManagerCustomizer { cacheManager ->
            cacheManager.setCaffeine(defaultCacheConfig)

            Cache.entries.map {
                cacheManager.registerCustomCache(it.cacheName, Caffeine.newBuilder().expireAfterWrite(it.duration, TimeUnit.MINUTES).build())
            }
        }
}
