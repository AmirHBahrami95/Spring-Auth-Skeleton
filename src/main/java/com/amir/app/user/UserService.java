package com.amir.app.user;

import java.util.Optional;

import com.amir.app.user.data.UserDto;
import com.amir.app.user.data.UserEntity;

public interface UserService{
	public Optional<UserEntity> getUserEntity(String userName);
	public Optional<UserDto> getUserInfo(String username);
	// public List<String> getUserAuthorities(String uname); // TODO
	public boolean addUser(UserEntity ue);
}
