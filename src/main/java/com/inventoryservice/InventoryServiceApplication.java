package com.inventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.inventoryservice.infrastructure.config.DatabaseConfig;
import com.inventoryservice.infrastructure.config.LoggingConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.inventoryservice.infrastructure.repository")
@EnableTransactionManagement
@EnableConfigurationProperties
@Import({DatabaseConfig.class, LoggingConfig.class})
public class InventoryServiceApplication implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(InventoryServiceApplication.class);

    private final Environment environment;

    public InventoryServiceApplication(Environment environment) {
        this.environment = environment;
        validateEnvironment();
    }

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(InventoryServiceApplication.class);
        application.setAdditionalProfiles("dev");
        application.run(args);
        logger.info("Inventory Service iniciado correctamente");
    }

    @Bean
    public String activeProfiles() {
        String[] profiles = environment.getActiveProfiles();
        logger.info("Perfiles activos: {}", Arrays.toString(profiles));
        return Arrays.toString(profiles);
    }

    private void validateEnvironment() {
        String dbUrl = environment.getProperty("spring.datasource.url");
        if (dbUrl == null || dbUrl.isBlank()) {
            throw new IllegalStateException("La propiedad 'spring.datasource.url' no está configurada");
        }

        String dbUsername = environment.getProperty("spring.datasource.username");
        if (dbUsername == null || dbUsername.isBlank()) {
            throw new IllegalStateException("La propiedad 'spring.datasource.username' no está configurada");
        }

        logger.info("Validación de entorno completada con éxito");
    }
}