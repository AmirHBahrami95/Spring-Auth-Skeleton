package com.amir.app.user.data;

/**A class to communicate with Front-end, without 
 * exposing user's Password hash or other sensetive info (like NationalCode).*/
public class UserDto {
	
	protected String uname;
	protected String fname;
	protected String lname;
	protected String email;
	protected String phoneNo;
	private boolean enabled;

	public String getUname() {
		return uname;
	}

	public String getFname() {
		return fname;
	}

	public String getLname() {
		return lname;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
