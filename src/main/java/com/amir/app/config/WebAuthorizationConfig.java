package com.amir.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class WebAuthorizationConfig {
	
	/*
	private AuthenticationProvider authProvider;
	
	public WebAuthorizationConfig(AppAuthenticationProvider aup) {
		this.authProvider=aup;
	}
	*/
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http.authorizeHttpRequests(c -> c
	        .requestMatchers("/api/user/**").permitAll()
	        .anyRequest().authenticated()
	    )
	    .httpBasic(c -> c.disable())
	    .formLogin(c -> c.disable())
	    .csrf(c->c.disable())
	    .cors(c->c.disable());
	    return http.build();
	}
	
}
