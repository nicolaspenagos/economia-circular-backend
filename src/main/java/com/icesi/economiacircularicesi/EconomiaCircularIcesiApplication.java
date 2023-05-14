package com.icesi.economiacircularicesi;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
@AllArgsConstructor
public class EconomiaCircularIcesiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EconomiaCircularIcesiApplication.class, args);
	}

	



}
