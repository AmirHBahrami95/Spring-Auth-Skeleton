package com.amir.app.user;

import java.util.Optional;

import com.amir.app.user.data.User;
import com.amir.app.user.data.UserDto;
import com.amir.app.user.data.UserEntity;

public interface UserRepo {
	public Optional<User> get(String uname);
	public Optional<UserEntity> getUserInfo(String uname);
	public boolean add(UserEntity u);
	public boolean disable(String uname);
	public boolean update(UserDto u);
	public boolean changePass(String uname,String newPass);
}
