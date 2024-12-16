package com.amir.app.user.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.amir.app.user.UserService;
import com.amir.app.user.data.User;
import com.amir.app.user.data.UserEntity;

public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // needed by spring security
		Optional<UserEntity> ud=userService.getUserEntity(username);
		if(ud.isEmpty()) throw new UsernameNotFoundException("User '"+username+"' was not found");
		return ud.get().toUser();
	}
}
