package com.saxomoose.webshop.config;

import com.saxomoose.webshop.services.ApplicationUserService;
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
    private final ApplicationUserService applicationUserService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public WebshopSecurityConfig(DataSource dataSource, ApplicationUserService accountService, PasswordEncoder passwordEncoder)
    {
        this.dataSource = dataSource;
        this.applicationUserService = accountService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder)
                .authoritiesByUsernameQuery("select username,role from users where username = ?");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/", "/items*", "/items/**", "/register", "/logout").permitAll()
                .mvcMatchers("/css/**", "/img/**", "/js/**", "/svg/**", "/webjars/**").permitAll()
                .anyRequest().authenticated().expressionHandler(expressionHandler()).and()
                .formLogin().loginPage("/login").loginProcessingUrl("/perform_login").permitAll().defaultSuccessUrl("/", true).and()
//                .rememberMe().userDetailsService(accountService).key("remember-me").and()
                .logout().logoutUrl("/perform_logout").invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessUrl("/").permitAll().and()
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
