package com.amir.app.user;

import org.springframework.security.core.userdetails.UserDetails;

import com.amir.app.user.data.DomainUser;
import com.amir.app.user.data.DomainUserDto;

public interface User extends UserDetails{
	public DomainUser getDomainUser();
	public DomainUserDto toDto();
}
