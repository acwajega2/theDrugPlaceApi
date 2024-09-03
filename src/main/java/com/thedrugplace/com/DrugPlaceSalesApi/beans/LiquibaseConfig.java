package com.thedrugplace.com.DrugPlaceSalesApi.beans;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.logging.Logger;

/**
 * Configuration class for Liquibase integration in Spring Boot application.
 */
@Configuration
public class LiquibaseConfig {

    private static final Logger logger = Logger.getLogger(LiquibaseConfig.class.getName());

    @Value("${spring.liquibase.change-log}")
    private String changeLog;

    /**
     * Configures and initializes Liquibase with the provided DataSource and change log file.
     *
     * @param dataSource The DataSource to be used by Liquibase for database migrations.
     * @return An initialized SpringLiquibase bean.
     */
    @Bean
    public SpringLiquibase liquibase(DataSource dataSource, Environment environment) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(changeLog);
        liquibase.setShouldRun(true);  // Enable Liquibase migrations to run
        liquibase.setContexts(environment.getProperty("liquibase.contexts", "default")); // Use default or environment-specific contexts

        // Log the Liquibase configuration
        logger.info("Initializing Liquibase with change log: " + changeLog);
        logger.info("Database URL: " + environment.getProperty("spring.datasource.url"));
        logger.info("Database Username: " + environment.getProperty("spring.datasource.username"));

        try {
            liquibase.afterPropertiesSet(); // Initialize Liquibase
            logger.info("Liquibase initialization successful.");
        } catch (LiquibaseException e) {
            logger.severe("Liquibase initialization failed: " + e.getMessage());
            throw new RuntimeException("Liquibase initialization error", e); // Rethrow as runtime exception to prevent app from starting
        }

        return liquibase;
    }
}
