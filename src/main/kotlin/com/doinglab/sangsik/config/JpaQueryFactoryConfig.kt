package com.doinglab.sangsik.config


import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JpaQueryFactoryConfig {

    @PersistenceContext(unitName = "dietEntityManager")
    private lateinit var dietEntityManager: EntityManager

    @PersistenceContext(unitName = "authEntityManager")
    private lateinit var authEntityManager: EntityManager

    @Bean
    fun dietJpaQueryFactory() = JPAQueryFactory(dietEntityManager)

    @Bean
    fun authJpaQueryFactory() = JPAQueryFactory(authEntityManager)
}
