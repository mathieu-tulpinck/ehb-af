package be.saxomoose.webshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebshopConfig implements WebMvcConfigurer
{
//    @Override
//    public void addViewControllers(final ViewControllerRegistry registry) {
//        registry.addViewController("/login");
//        registry.addViewController("/register");
//    }
}
