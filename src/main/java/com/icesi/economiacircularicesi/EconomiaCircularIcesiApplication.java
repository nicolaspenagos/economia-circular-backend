package com.icesi.economiacircularicesi;

import lombok.AllArgsConstructor;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;


@SpringBootApplication
@EnableJpaRepositories
@AllArgsConstructor
public class EconomiaCircularIcesiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EconomiaCircularIcesiApplication.class, args);
	}

	



}
