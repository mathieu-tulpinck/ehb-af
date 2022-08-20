package com.saxomoose.webshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebshopSecurityConfig extends WebSecurityConfigurerAdapter
{
    private final DataSource dataSource;
    private final PasswordEncoder passwordEncoder;

    // This annotation is needed to use dependency injection.
    @Autowired
    public WebshopSecurityConfig(DataSource dataSource, PasswordEncoder passwordEncoder)
    {
        this.dataSource = dataSource;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .passwordEncoder(passwordEncoder)
            // Sets the query to be used for finding a user's authorities by their username.
            .authoritiesByUsernameQuery("select username,role from users where username = ?");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // Whitelist of public urls.
                .mvcMatchers("/", "/items*", "/items/**", "/register", "/logout").permitAll()
                .mvcMatchers("/css/**", "/img/**", "/js/**", "/svg/**", "/webjars/**").permitAll()
                .anyRequest().authenticated().expressionHandler(expressionHandler()).and()
                .formLogin().loginPage("/login").loginProcessingUrl("/perform_login").permitAll().defaultSuccessUrl("/", true).and()
                .logout().logoutUrl("/perform_logout")
                .logoutSuccessUrl("/").permitAll().and()
                .httpBasic();
    }

    private SecurityExpressionHandler<FilterInvocation> expressionHandler()
    {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");

        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setRoleHierarchy(roleHierarchy);

        return handler;
    }
}
