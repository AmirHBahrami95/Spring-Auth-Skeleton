package com.amir.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.amir.app.filters.IPLoggerFilter;
import com.amir.app.filters.TokenAuthenticationFilter;

@Configuration
// @EnableWebSecurity(debug=true)
public class WebAuthorizationConfig {
	
	@Autowired
	private TokenAuthenticationFilter tokAuthFilth;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
    .authorizeHttpRequests(c -> c    		
    		.requestMatchers("/api/user/logout").authenticated()
        .requestMatchers("/api/user/**").permitAll()
        .anyRequest().authenticated()
    )
    .httpBasic(c->c.disable())
    .formLogin(c -> c.disable())
    .logout(c->c.disable())
    .csrf(c->c.disable())
    .cors(c->c.disable())
    .addFilterBefore(new IPLoggerFilter(), ChannelProcessingFilter.class)
    .addFilterBefore(tokAuthFilth, AnonymousAuthenticationFilter.class)
    .anonymous(c->c.disable())
    ;
    return http.build();
	}
	
}
