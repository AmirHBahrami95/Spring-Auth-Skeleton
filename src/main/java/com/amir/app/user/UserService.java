package com.amir.app.user;

import java.util.Optional;

import com.amir.app.user.data.DomainUserDto;
import com.amir.app.user.data.DomainUser;

public interface UserService{
	public Optional<DomainUser> getDomainUser(String userName); // full full
	public Optional<DomainUserDto> getDomainUserDto(String username); // only what the end user needs to seee
	public boolean addUser(DomainUser ue);
	// TODO public String login(String userName,String password);
}
