package com.example.conferences;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.conferences.*")
@EntityScan("com.example.conferences.*")
public class ConferencesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConferencesApplication.class, args);
	}

}
