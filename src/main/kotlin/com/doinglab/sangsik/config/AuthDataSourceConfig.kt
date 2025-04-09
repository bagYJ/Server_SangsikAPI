package com.doinglab.sangsik.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = ["com.doinglab.sangsik.api.domains.auth"],
    entityManagerFactoryRef = "authEntityManager",
    transactionManagerRef = "authTransactionManager"
)
class AuthDataSourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.auth")
    fun authDataSource(): DataSource
        = DataSourceBuilder.create().build()

    @Bean
    fun authEntityManager(): LocalContainerEntityManagerFactoryBean
        = (LocalContainerEntityManagerFactoryBean()).apply {
            dataSource = authDataSource()
            setPackagesToScan("com.doinglab.sangsik.api.domains")
            jpaVendorAdapter = HibernateJpaVendorAdapter()

//        Flyway.configure()
//            .dataSource(dataSource)
//            .locations("db/migration/auth")
//            .baselineOnMigrate(true)
//            .load().migrate()
        }

    @Bean
    fun authTransactionManager()
        = JpaTransactionManager(authEntityManager().`object`!!)
}
