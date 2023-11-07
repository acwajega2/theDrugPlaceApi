package com.thedrugplace.com.DrugPlaceSalesApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("com.thedrugplace.com.*")
@ComponentScan(basePackages = { "com.thedrugplace.com.*" })
@EntityScan("com.thedrugplace.com.*")
public class DrugPlaceSalesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrugPlaceSalesApiApplication.class, args);
	}

}
