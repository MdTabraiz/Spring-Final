package com.zemoso.springdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/blogs/user/**").hasRole("USER")
                .antMatchers("/blogs/admin/**").hasRole("ADMIN")
                .and().formLogin().loginPage("/blogs/showMyLoginPage")
                .loginProcessingUrl("/authenticateTheUser").defaultSuccessUrl("/blogs/success", true)
                .permitAll().and().logout().permitAll()
                .and().exceptionHandling().accessDeniedPage("/blogs/access-denied");
    }
}














