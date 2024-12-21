package com.amir.app.user.impl;

import java.util.List;
import java.util.Optional;

import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.amir.app.user.UserTokenRepo;
import com.amir.app.user.data.UserToken;
import com.amir.app.user.data.rowmappers.UserTokenRowMapper;

@Transactional
public class UserTokenRepoImpl implements UserTokenRepo{
	
	private final static String USER_TOKEN_TABLE = "usr_token";
	private final static String USER_TOKEN_VIEW  = "usr_token_view";
	
	@Autowired
	private Jdbi db;

	@Override
	@Transactional
	public Optional<UserToken> getByToken(String token) {
		List<UserToken> us=db.withHandle(h->h.createQuery("SELECT * FROM "+USER_TOKEN_VIEW+" WHERE token=:token"))
			.bind("token",token)
			.map(new UserTokenRowMapper())
			.list();
		return us.size()>0?Optional.of(us.get(0)):Optional.empty();
	}
	
	@Override
	@Transactional
	public Optional<UserToken> getByUname(String uname) {
		List<UserToken> us=db.withHandle(h->h.createQuery("SELECT * FROM "+USER_TOKEN_VIEW+" WHERE uname=:uname"))
			.bind("uname",uname)
			.map(new UserTokenRowMapper())
			.list();
		return us.size()>0?Optional.of(us.get(0)):Optional.empty();
	}
	
	@Override
	@Transactional // XXX ALWAYS add this annotation when insert/updating 
	public boolean put(String username,String token) {
		// update if existing, insert if not
		// p.s. you HAVE TO write it like a retard, otherwise it'll get fucked
		if(updateToken(username,token))return true;
		else if(insert(username,token))return true; 
		return false;
	}

	@Override
	@Transactional
	public boolean deleteByUname(String username) {
		return db.withHandle(h->h.createUpdate("DELETE FROM "+USER_TOKEN_TABLE+" WHERE uname=:uname"))
			.bind("uname",username)
			.execute()>0;
	}

	@Transactional
	private boolean insert(String username, String token) {
		return db.withHandle(h->h.createUpdate("INSERT INTO "+USER_TOKEN_TABLE+"(usr_uname,token) VALUES(:usr_uname,:token)"))
			.bind("usr_uname",username)
			.bind("token",token)
			.execute()>0;
	}
	
	@Transactional
	private boolean updateToken(String username, String token) {
		return db.withHandle(h->h.createUpdate("UPDATE "+USER_TOKEN_TABLE+" SET token=:token WHERE usr_uname=:usr_uname"))
				.bind("usr_uname",username)
				.bind("token",token)
				.execute()>0;
	}

}
