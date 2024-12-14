package com.amir.app.config;

import java.util.Arrays;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class AppAuthenticationProvider{};

/*
@Deprecated
public class AppAuthenticationProvider implements AuthenticationProvider{

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// .authentication.set
		String username=authentication.getName();
		String password=String.valueOf(authentication.getCredentials());
		if(username.equals("amir") && password.equals("FuckYou"))
			return new UsernamePasswordAuthenticationToken(username, password,Arrays.asList());
		else throw new AuthenticationCredentialsNotFoundException("Wrong Password,Bitch!");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return false;
	}

}
*/