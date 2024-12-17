package com.amir.app.user.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.amir.app.user.UserRepo;
import com.amir.app.user.UserService;
import com.amir.app.user.data.DomainUserDto;
import com.amir.app.user.data.DomainUser;

public class UserServiceImpl implements UserService{
	
	@Autowired 
	private UserRepo userRepo;
	
	@Autowired 
	private PasswordEncoder pe;
	
	@Override
	public Optional<DomainUser> getDomainUser(String userName) {
		return userRepo.getDomainUser(userName);
	}

	@Override
	public Optional<DomainUserDto> getDomainUserDto(String username) {
		return Optional.ofNullable(userRepo.getDomainUser(username).get().toDto());
	}

	@Override
	public boolean addUser(DomainUser ue) {
		ue.setPass(pe.encode(ue.getPass())); // encode the fucker
		return userRepo.add(ue);
	}

}
