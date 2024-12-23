package com.amir.app.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.amir.app.user.UserService;
import com.amir.app.user.data.DomainUser;
import com.amir.app.user.impl.UserImpl;

/**
 * Simple token based authentication provider. Supports TokenAuthentication Objects
 * and upon authentication generates a new TokenAuthentication. @see TokenAuthentication 
 * */
@Component
public class TokenAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private UserService userService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Optional<DomainUser> du=userService.getByCredentials(authentication.getCredentials().toString());
		TokenAuthentication ta=null;
		if(du.isPresent()) {
			ta=new TokenAuthentication("yeah,sure buddy!",new UserImpl(du.get()));
			ta.setAuthenticated(true);
		}
		return ta;
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(TokenAuthentication.class);
	}
	
};