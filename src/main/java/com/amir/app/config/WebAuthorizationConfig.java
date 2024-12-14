package com.amir.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

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
		// http.authenticationProvider(authProvider); // uncomment when setting the auth provider
		// not basic http auth or form login are allowed
		http.httpBasic(c->c.disable()).formLogin(c->c.disable());
		http.authorizeHttpRequests(c->c.anyRequest().permitAll());
		return http.build();
	}
	
}
