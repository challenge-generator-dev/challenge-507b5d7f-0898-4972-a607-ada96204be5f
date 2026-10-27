package com.inventoryservice.infrastructure.config;

import jakarta.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.inventoryservice.infrastructure.adapter")
public class DatabaseConfig {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);

    @Value("${spring.datasource.url:jdbc:postgresql://localhost:5432/inventory_db}")
    private String dbUrl;

    @Value("${spring.datasource.username:postgres}")
    private String dbUsername;

    @Value("${spring.datasource.password:postgres}")
    private String dbPassword;

    @Value("${spring.datasource.driver-class-name:org.postgresql.Driver}")
    private String driverClassName;

    @Value("${spring.jpa.hibernate.ddl-auto:update}")
    private String hibernateDdlAuto;

    @Value("${spring.jpa.show-sql:false}")
    private boolean showSql;

    @Value("${spring.jpa.properties.hibernate.dialect:org.hibernate.dialect.PostgreSQLDialect}")
    private String hibernateDialect;

    @Value("${spring.jpa.properties.hibernate.format_sql:true}")
    private boolean formatSql;

    @Bean
    @Primary
    public DataSource dataSource() {
        logger.info("Configuring PostgreSQL DataSource");
        logger.debug("Database URL: {}", dbUrl);
        
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create()
            .url(dbUrl)
            .username(dbUsername)
            .password(dbPassword)
            .driverClassName(driverClassName);
        
        DataSource dataSource = dataSourceBuilder.build();
        logger.info("DataSource configured successfully");
        return dataSource;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        logger.info("Configuring JPA EntityManagerFactory");
        
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.inventoryservice.infrastructure.repository", 
                            "com.inventoryservice.domain.model");
        
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(showSql);
        em.setJpaVendorAdapter(vendorAdapter);
        
        Properties additionalProperties = new Properties();
        additionalProperties.setProperty("hibernate.hbm2ddl.auto", hibernateDdlAuto);
        additionalProperties.setProperty("hibernate.dialect", hibernateDialect);
        additionalProperties.setProperty("hibernate.format_sql", String.valueOf(formatSql));
        additionalProperties.setProperty("hibernate.use_sql_comments", "true");
        additionalProperties.setProperty("hibernate.jdbc.batch_size", "20");
        additionalProperties.setProperty("hibernate.order_inserts", "true");
        additionalProperties.setProperty("hibernate.order_updates", "true");
        additionalProperties.setProperty("hibernate.jdbc.batch_versioned_data", "true");
        
        em.setJpaProperties(additionalProperties);
        logger.info("EntityManagerFactory configured successfully");
        
        return em;
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        logger.info("Configuring JPA TransactionManager");
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        logger.info("TransactionManager configured successfully");
        return transactionManager;
    }
}