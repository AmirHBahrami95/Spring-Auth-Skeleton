package com.amir.app.user.data;

import java.util.LinkedList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

/** Class containing All the fileds a user has.
 * It eases the interaction with database and 
 * at the same time can turn into 'User'.
 * can also be used as a 'UserDto' (by extending it).
 * Usages:
 * 1-Registration/Login
 * 2-Changing User's Password 
 * 3-Used in RowMappers
 * */
public class DomainUser{
	
	protected String uname;
	protected String fname;
	protected String lname;
	protected String email;
	protected String phoneNo;
	protected boolean enabled;
	protected String pass;
	protected String natCode; // nullable
	protected List<String> auths; // nullable
	
	public DomainUserDto toDto() {
		DomainUserDto u=new DomainUserDto();
		u.setUname(this.uname);
		u.setFname(this.fname);
		u.setLname(this.lname);
		u.setEmail(this.email);
		u.setPhoneNo(this.phoneNo);
		u.setEnabled(this.enabled);
		return u;
	}
	
	public List<SimpleGrantedAuthority> getAuths() {
		List<SimpleGrantedAuthority> sgas=new LinkedList<>();
		for(int i=0;i<auths.size();i++)
			sgas.add(new SimpleGrantedAuthority(auths.get(i)));
		return sgas;
	}
	
	public String getPass() {return pass;}
	public String getNatCode() {return natCode;}
	public String getUname() {return uname;}
	public String getFname() {return fname;}
	public String getLname() {return lname;}
	public String getEmail() {return email;}
	public String getPhoneNo() {return phoneNo;}
	public boolean isEnabled() {return enabled;}
	
	public void setAuths(List<String> auths) {this.auths = auths;}
	public void setNatCode(String natCode) {this.natCode = natCode;}
	public void setPass(String pass) {this.pass = pass;}
	public void setUname(String uname) {this.uname = uname;}
	public void setFname(String fname) {this.fname = fname;}
	public void setLname(String lname) {this.lname = lname;}
	public void setEmail(String email) {this.email = email;}
	public void setPhoneNo(String phoneNo) {this.phoneNo = phoneNo;}
	public void setEnabled(boolean enabled) {this.enabled = enabled;}
	
}
