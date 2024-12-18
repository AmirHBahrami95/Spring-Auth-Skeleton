package com.amir.app.user.data;

import java.time.LocalDateTime;

public class UserStats {
	
	private String uname;
	private LocalDateTime lastLogin;
	private LocalDateTime lastOnline; // needs a filter to be updated
	private LocalDateTime joinedAt;
	private boolean validated;
	
	public String getUname() {
		return uname;
	}
	public LocalDateTime getLastLogin() {
		return lastLogin;
	}
	public LocalDateTime getLastOnline() {
		return lastOnline;
	}
	public LocalDateTime getJoinedAt() {
		return joinedAt;
	}
	public boolean isValidated() {
		return validated;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}
	public void setLastOnline(LocalDateTime lastOnline) {
		this.lastOnline = lastOnline;
	}
	public void setJoinedAt(LocalDateTime joinedAt) {
		this.joinedAt = joinedAt;
	}
	public void setValidated(boolean validated) {
		this.validated = validated;
	}
}
