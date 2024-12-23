package com.amir.app.user.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.amir.app.user.UserRepo;
import com.amir.app.user.UserService;
import com.amir.app.user.UserStatsRepo;
import com.amir.app.user.UserTokenRepo;
import com.amir.app.user.data.DomainUserDto;
import com.amir.app.user.data.UserStats;
import com.amir.app.user.data.UserToken;
import com.amir.app.utils.StrUtils;
import com.amir.app.user.data.DomainUser;

public class UserServiceImpl implements UserService{
	
	private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
			
	@Autowired 
	private UserRepo userRepo;
	
	@Autowired
	private UserTokenRepo userTokenRepo;
	
	@Autowired 
	private UserStatsRepo userStatsRepo;
	
	@Autowired 
	private PasswordEncoder pe;
	
	@Override
	public Optional<DomainUser> getByUname(String uname) {
		return userRepo.getDomainUser(uname);
	}
	
	@Override
	@Transactional
	public Optional<String> login(DomainUser ue) throws BadCredentialsException{
		checkCredentials(ue);
		return createUserSession(ue.getUname());
	}
	
	@Override
	public Optional<DomainUser> getByCredentials(String credentials){
		Optional<UserToken> du=userTokenRepo.getByToken(credentials);
		List<String> auths=userRepo.getAuthorities(du.get().getDomainUser().getUname());
		du.get().getDomainUser().setAuths(auths);
		return du.isPresent()?Optional.of(du.get().getDomainUser()):Optional.empty();
	}

	@Override
	public Optional<DomainUserDto> getDtoByUname(String uname) {
		return Optional.ofNullable(userRepo.getDomainUser(uname).get().toDto());
	}

	@Override
	@Transactional
	public Optional<String> register(DomainUser ue) {
		String encoded=pe.encode(ue.getPass());
		ue.setPass(encoded);
		if(!userRepo.add(ue)) return Optional.empty();
		markRegisterTime(ue);
		return createUserSession(ue.getUname());
	}

	@Override
	@Transactional
	public boolean logout(String uname) {
		return userTokenRepo.deleteByUname(uname);
	}
	
	// ========== PVT.PARTS ;)
	
	@Transactional
	private Optional<String> createUserSession(String uname){
		String rand=StrUtils.toHex(StrUtils.generateSecureRandom(30));
		return userTokenRepo.put(uname,rand)?Optional.of(rand):Optional.empty();
	}
	
	private void checkCredentials(DomainUser ue) throws BadCredentialsException {
		Optional<DomainUser> saved=userRepo.getDomainUser(ue.getUname());
		if(saved.isEmpty()) throw new BadCredentialsException("no such user found");
		else if(!pe.matches(ue.getPass(),saved.get().getPass()))throw new BadCredentialsException("invalid password"); ;
	}
	
	private void markRegisterTime(DomainUser ue) {
		UserStats us=new UserStats();
		us.setUname(ue.getUname());
		us.setJoinedAt(LocalDateTime.now());
		userStatsRepo.put(us);
	}

}
