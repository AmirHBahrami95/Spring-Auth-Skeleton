package com.amir.app.user.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.amir.app.user.UserRepo;
import com.amir.app.user.UserService;
import com.amir.app.user.data.User;
import com.amir.app.user.data.UserDto;
import com.amir.app.user.data.UserEntity;

public class UserServiceImpl implements UserService{
	
	@Autowired UserRepo userRepo;

	@Override
	public Optional<UserDto> getUserInfo(String username) {
		return Optional.ofNullable(userRepo.getUserInfo(username).get());
	}

	@Override
	public boolean addUser(UserEntity ue) {
		return userRepo.add(ue);
	}

	@Override
	public Optional<UserEntity> getUserEntity(String userName) {
		return userRepo.getUserInfo(userName);
	}

}
