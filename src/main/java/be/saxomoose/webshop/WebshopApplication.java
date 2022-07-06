package be.saxomoose.webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// Comment out annotation to enable basic auth.
@SpringBootApplication/*(exclude={SecurityAutoConfiguration.class})*/
public class WebshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebshopApplication.class, args);
	}
}
