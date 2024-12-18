package com.amir.app.user.impl;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.amir.app.user.User;
import com.amir.app.user.data.DomainUser;
import com.amir.app.user.data.DomainUserDto;

/** Used for authentications inside a session AND for redis. 
 * Objects of this class are used ENTIRELY FOR SPRING SECURITY 
 * and not for communicating with the front-end.
 * For front-end communications 'UserDto' and 'UserRegister' are there. 
 * */
public class UserImpl implements User{
	
	// user-details 
	private DomainUser domainUser;
	
	public UserImpl(DomainUser du) {
		this.domainUser=du;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return domainUser.getAuths();
	}

	@Override
	public String getPassword() {
		return domainUser.getPass();
	}

	@Override
	public String getUsername() {
		return domainUser.getUname();
	}

	@Override
	public DomainUser getDomainUser() {
		return this.domainUser;
	}

	@Override
	public DomainUserDto toDto() {
		return domainUser.toDto();
	}

}
