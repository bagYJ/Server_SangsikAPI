package com.doinglab.sangsik.config

import jakarta.persistence.SharedCacheMode
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = [
        "com.doinglab.sangsik.api.domains.chat",
        "com.doinglab.sangsik.api.domains.food",
        "com.doinglab.sangsik.api.domains.meal",
        "com.doinglab.sangsik.api.domains.user",
        "com.doinglab.sangsik.api.domains.dietProgram",
        "com.doinglab.sangsik.api.domains.announcement",
        "com.doinglab.sangsik.api.domains.staff",
        "com.doinglab.sangsik.api.domains.cgm"
    ],
    entityManagerFactoryRef = "dietEntityManager",
    transactionManagerRef = "dietTransactionManager"
)
class DietDataSourceConfig {
    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.diet")
    fun dietDataSource(): DataSource
        = DataSourceBuilder.create().build()

    @Primary
    @Bean
    fun dietEntityManager(): LocalContainerEntityManagerFactoryBean
        = (LocalContainerEntityManagerFactoryBean()).apply {
            dataSource = dietDataSource()
            setPackagesToScan("com.doinglab.sangsik.api.domains")
            setSharedCacheMode(SharedCacheMode.ALL)
            jpaVendorAdapter = HibernateJpaVendorAdapter()

//            Flyway.configure()
//                .dataSource(dataSource)
//                .locations("db/migration/diet")
//                .baselineOnMigrate(true)
//                .load().migrate()
        }

    @Primary
    @Bean
    fun dietTransactionManager()
        = JpaTransactionManager(dietEntityManager().`object`!!)
}
