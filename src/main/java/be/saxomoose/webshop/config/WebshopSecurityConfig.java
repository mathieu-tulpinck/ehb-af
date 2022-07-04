package be.saxomoose.webshop.config;

import be.saxomoose.webshop.services.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebshopSecurityConfig extends WebSecurityConfigurerAdapter
{
    private DataSource dataSource;
    private ApplicationUserService accountService;
    private PasswordEncoder passwordEncoder;



    @Autowired
    public WebshopSecurityConfig(DataSource dataSource, ApplicationUserService accountService, PasswordEncoder passwordEncoder)
    {
        this.dataSource = dataSource;
        this.accountService = accountService;
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
                .mvcMatchers("/", "/items*", "/items/**", "/register").permitAll()
                .mvcMatchers("/css/**", "/img/**", "/js/**", "/svg/**", "/webjars/**").permitAll()
                .anyRequest().authenticated().expressionHandler(expressionHandler()).and()
                .formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/", true).and()
                .rememberMe().userDetailsService(accountService).key("remember-me").and()
                .logout().permitAll().and()
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
