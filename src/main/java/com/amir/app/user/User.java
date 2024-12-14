package com.amir.app.user;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails{
	
	private String uname; // to avoid 'username' field conflicts in maria-db
	private String pass; // same as uname

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList();
	}

	@Override
	public String getPassword() {
		return "FuckYou";
	}

	@Override
	public String getUsername() {
		return "Amir";
	}

}
