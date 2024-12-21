package com.amir.app.user;

import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;

import com.amir.app.user.data.DomainUserDto;
import com.amir.app.user.data.DomainUser;

public interface UserService{
	public Optional<DomainUser> getByUname(String uname);
	public Optional<DomainUser> getByCredentials(String credentials); // like token, NOT password! - used by AuthenticationFilter
	public Optional<DomainUserDto> getDtoByUname(String uname);
	public Optional<String> register(DomainUser ue);
	public Optional<String> login(DomainUser ue) throws BadCredentialsException; // input object only needs
	public boolean logout(String uname);
}
