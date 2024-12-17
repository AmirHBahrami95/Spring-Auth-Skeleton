package com.amir.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import com.amir.app.user.UserRepo;
import com.amir.app.user.UserService;
import com.amir.app.user.impl.UserDetailsServiceImpl;
import com.amir.app.user.impl.UserRepoImpl;
import com.amir.app.user.impl.UserServiceImpl;

@Configuration
public class UserConfig {
	
	@Bean
	public UserDetailsService userDetailsService(){
		return new UserDetailsServiceImpl(); // uses UserService Bean
	}
	
	@Bean
	public UserService userService() { // also used by UserDetailsServiceImpl
		return new UserServiceImpl();
	}
	
	@Bean
	public UserRepo userRepo() {
		return new UserRepoImpl();
	}
	
	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
