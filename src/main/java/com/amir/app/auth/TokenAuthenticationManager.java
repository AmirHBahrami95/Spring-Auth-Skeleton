package com.amir.app.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthenticationManager implements AuthenticationManager {
	
	@Autowired // TODO make this active again, this isn't working rn 
	private TokenAuthenticationProvider authProv;
	
	/*public TokenAuthenticationManager() {
		this.authProv=new TokenAuthenticationProvider();
	}*/

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		return authProv.authenticate(authentication);
	}
}
