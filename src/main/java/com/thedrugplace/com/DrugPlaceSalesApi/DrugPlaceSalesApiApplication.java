package com.thedrugplace.com.DrugPlaceSalesApi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories("com.thedrugplace.com.*")
@ComponentScan(basePackages = {"com.thedrugplace.com.*"})
@EnableScheduling
@EntityScan("com.thedrugplace.com.*")
public class DrugPlaceSalesApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DrugPlaceSalesApiApplication.class, args);
    }

    // This bean will run when the application starts
    @Bean
    public CommandLineRunner init() {
        return args -> {
            // Set the default timezone to EAT (East Africa Time)
            TimeZone.setDefault(TimeZone.getTimeZone("Africa/Nairobi"));
            System.out.println("Spring Boot application running in EAT timezone: " + TimeZone.getDefault().getDisplayName());
        };
    }
}
