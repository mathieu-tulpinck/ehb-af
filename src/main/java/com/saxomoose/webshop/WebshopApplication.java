package com.saxomoose.webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// The @SpringBootApplication annotation is equivalent to using @Configuration, @EnableAutoConfiguration, and @ComponentScan with their default attributes.
@SpringBootApplication
public class WebshopApplication {

	public static void main(String[] args) {
		// Runs the application from the specified source.
		SpringApplication.run(WebshopApplication.class, args);
	}
}
