package com.amir.app.user;

import java.util.List;
import java.util.Optional;

import com.amir.app.user.data.DomainUserDto;
import com.amir.app.user.data.DomainUser;

public interface UserRepo {
	public Optional<DomainUser> getDomainUser(String uname);
	public List<String> getAuthorities(String uname);
	public boolean add(DomainUser u);
	public boolean disable(String uname);
	public boolean update(DomainUserDto u);
	public boolean changePass(String uname,String newPass);
}
