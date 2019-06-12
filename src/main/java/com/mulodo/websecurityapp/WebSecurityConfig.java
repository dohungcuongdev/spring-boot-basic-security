package com.mulodo.websecurityapp;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            /* unsecure a method with Spring security, login is allowed to access without authentication 
            must add before .anyRequest().authenticated() */
            .antMatchers(HttpMethod.POST, new String [] {"/login"}).permitAll() //grant access to all roles
            //.antMatchers(HttpMethod.POST, new String [] {"/login"}).hasRole("ADMIN") // only allow role ADMIN
                .anyRequest().authenticated()
                .and()
            .httpBasic();
       http.cors(); //disable this line to reproduce the CORS 401

       // fix 403 bug when POST
       http.csrf().disable();
    }
    
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("Cuong")
            .password(passwordEncoder().encode("123"))
            .roles("USER");
        
        auth.inMemoryAuthentication()
        	.withUser("bill")
            .password("abc123")
            .roles("ADMIN");
    }
}