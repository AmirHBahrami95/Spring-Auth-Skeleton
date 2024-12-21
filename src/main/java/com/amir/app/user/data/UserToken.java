package com.amir.app.user.data;

public class UserToken {
	
	private DomainUser du;
	private String token;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public DomainUser getDomainUser() {
		return du;
	}
	public void setDomainUser(DomainUser du) {
		this.du = du;
	}
	
	
}
