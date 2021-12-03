package com.kolyanlock.afk_heroes_crud.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@EnableWebSecurity
@RequiredArgsConstructor
public class RESTSecurityConfig extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/api/heroes/**").hasRole("MANAGER")
                .antMatchers("/api/**").hasRole("DEVELOPER")
                .antMatchers("/actuator/beans", "/actuator/mappings").hasRole("DEVELOPER")
                .antMatchers("/actuator/health", "/actuator/info").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic();
    }
}
