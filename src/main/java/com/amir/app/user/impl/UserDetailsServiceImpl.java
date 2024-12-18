package com.amir.app.user.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.amir.app.user.UserService;
import com.amir.app.user.data.DomainUser;

/** Only to be used by Spring Security internally. */
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired 
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // needed by spring security
		Optional<DomainUser> ud=userService.getDomainUser(username);
		if(ud.isEmpty()) throw new UsernameNotFoundException("User '"+username+"' was not found");
		return new UserImpl(ud.get()); // wrap DomainUser object inside our version of UserDetials 
	}
}
