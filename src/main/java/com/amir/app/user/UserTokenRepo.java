package com.amir.app.user;

import java.util.Optional;

import com.amir.app.user.data.DomainUser;
import com.amir.app.user.data.UserToken;

public interface UserTokenRepo {
	public Optional<UserToken> getByToken(String token);
	public Optional<UserToken> getByUname(String uname);
	public boolean put(String username,String token);
	public boolean deleteByUname(String username);
	// public boolean deleteByToken(String username); // TODO
	// XXX if you decided you're gonna let user have more than a session, consider these mofo's:
	// public boolean insert(String username,String token);
	// public boolean updateToken(String username,String token);
}
