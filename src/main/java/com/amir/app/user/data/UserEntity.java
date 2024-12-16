package com.amir.app.user.data;

import java.util.List;

/** Class containing All the fileds a user has.
 * It eases the interaction with database and 
 * at the same time can turn into 'User'.
 * can also be used as a 'UserDto' (by extending it).
 * Usages:
 * 1-Registration/Login
 * 2-Changing User's Password 
 * 3-Used in RowMappers
 * */
public class UserEntity extends UserDto{
	
	private String pass;
	private String natCode; // nullable
	private List<String> auths; // nullable
	
	public User toUser() {
		return new User(super.uname,pass,auths);
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}	

	public String getNatCode() {
		return natCode;
	}

	public void setNatCode(String natCode) {
		this.natCode = natCode;
	}

	public List<String> getAuths() {
		return auths;
	}

	public void setAuths(List<String> auths) {
		this.auths = auths;
	}
	
}
