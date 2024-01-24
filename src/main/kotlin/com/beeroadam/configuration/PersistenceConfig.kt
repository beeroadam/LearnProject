package com.beeroadam.configuration

import jakarta.persistence.EntityManagerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.sql.DataSource

/**
 * PersistenceConfig is a configuration class for setting up the persistence layer of a Spring application.
 * It is annotated with @Configuration, @EnableTransactionManagement, and @EnableJpaRepositories.
 *
 * @Configuration indicates that this class provides Spring with bean definitions.
 *
 * @EnableTransactionManagement enables Spring's transaction management capability.
 *
 * @EnableJpaRepositories enables Spring Data JPA repositories and is configured to scan the "com.beeroadam.repository" package for repository interfaces.
 *
 * @author abiro
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = ["com.beeroadam.repository"])
class PersistenceConfig {
    /**
     * Configures the data source for the application.
     * It sets up a DriverManagerDataSource for an H2 database and returns it.
     *
     * @return DataSource object configured for H2 database.
     */
    @Bean
    fun dataSource(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName("org.h2.Driver")
        dataSource.url = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1"
        dataSource.username = "sa"
        dataSource.password = ""
        return dataSource
    }

    /**
     * Configures the entity manager factory for the application.
     * It sets up a LocalContainerEntityManagerFactoryBean with the data source and Hibernate as the JPA vendor.
     *
     * @return LocalContainerEntityManagerFactoryBean object configured with the data source and Hibernate as the JPA vendor.
     */
    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        val vendorAdapter = HibernateJpaVendorAdapter()
        vendorAdapter.setGenerateDdl(true)

        val factory = LocalContainerEntityManagerFactoryBean()
        factory.jpaVendorAdapter = vendorAdapter
        factory.setPackagesToScan("com.beeroadam.entity")
        factory.dataSource = dataSource()
        factory.persistenceUnitName = "myPU"
        factory.setJpaProperties(hibernateProperties())

        return factory
    }

    /**
     * Sets the Hibernate properties for the application.
     * These properties include settings for the database schema generation, SQL dialect, and SQL formatting.
     *
     * @return Properties object with the Hibernate properties set.
     */
    private fun hibernateProperties(): Properties {
        val properties = Properties()
        properties.setProperty("hibernate.hbm2ddl.auto", "create")
        properties.setProperty("hibernate.hbm2ddl.import_files", "/scripts/startup.sql")
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
        properties.setProperty("hibernate.show_sql", "true")
        properties.setProperty("hibernate.format_sql", "true")
        properties.setProperty("hibernate.use_sql_comments", "true")
        return properties
    }

    /**
     * Configures the transaction manager for the application.
     * It sets up a JpaTransactionManager with the provided entity manager factory.
     *
     * @param entityManagerFactory The EntityManagerFactory to be used by the transaction manager.
     * @return PlatformTransactionManager object configured with the provided EntityManagerFactory.
     */
    @Bean
    fun transactionManager(entityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
        val txManager = JpaTransactionManager()
        txManager.entityManagerFactory = entityManagerFactory
        return txManager
    }
}