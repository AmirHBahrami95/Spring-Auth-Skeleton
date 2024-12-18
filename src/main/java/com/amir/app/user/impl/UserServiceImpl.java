package com.amir.app.user.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.amir.app.user.UserRepo;
import com.amir.app.user.UserService;
import com.amir.app.user.UserStatsRepo;
import com.amir.app.user.data.DomainUserDto;
import com.amir.app.user.data.UserStats;
import com.amir.app.user.data.DomainUser;

public class UserServiceImpl implements UserService{
	
	@Autowired 
	private UserRepo userRepo;
	
	@Autowired 
	private UserStatsRepo userStatsRepo;
	
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
	@Transactional
	public boolean addUser(DomainUser ue) {
		ue.setPass(pe.encode(ue.getPass())); // encode the fucker
		if(!userRepo.add(ue)) return false;
		markRegisterTime(ue);
		return true;
	}
	
	private void markRegisterTime(DomainUser ue) {
		UserStats us=new UserStats();
		us.setUname(ue.getUname());
		us.setJoinedAt(LocalDateTime.now());
		userStatsRepo.put(us);
	}

}
