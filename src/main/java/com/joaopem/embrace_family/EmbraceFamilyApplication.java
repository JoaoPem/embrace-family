package com.joaopem.embrace_family;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EmbraceFamilyApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmbraceFamilyApplication.class, args);
	}

}
