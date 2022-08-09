package com.saxomoose.webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Comment out annotation to enable basic auth.
@SpringBootApplication/*(exclude={SecurityAutoConfiguration.class})*/
public class WebshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebshopApplication.class, args);
	}
}
