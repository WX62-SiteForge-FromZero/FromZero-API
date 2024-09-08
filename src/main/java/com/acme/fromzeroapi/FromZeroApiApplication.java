package com.acme.fromzeroapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FromZeroApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(FromZeroApiApplication.class, args);
	}
}
