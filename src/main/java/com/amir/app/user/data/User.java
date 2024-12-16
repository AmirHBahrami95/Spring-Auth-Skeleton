package com.amir.app.user.data;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/** Used for authentications inside a session AND for redis. 
 * Objects of this class are used ENTIRELY FOR SPRING SECURITY 
 * and not for communicating with the front-end.
 * For front-end communications 'UserDto' and 'UserRegister' are there. 
 * */
public class User implements UserDetails{
	
	private String uname; // to avoid 'username' field conflicts in maria-db
	private String pass; // same as uname
	private List<String> authorities;
	
	public User(String uname,String pass,List<String> authorities) {
		this.uname=uname;
		this.pass=pass;
		this.authorities=authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// NOTE that for some reason if I put this statement in constructor I'd have
		// gotten an error. idky
		List<SimpleGrantedAuthority> sgas=new LinkedList<>();
		for(int i=0;i<authorities.size();i++)
			sgas.add(new SimpleGrantedAuthority(authorities.get(i)));
		return sgas;
	}

	@Override
	public String getPassword() {
		return pass;
	}

	@Override
	public String getUsername() {
		return uname;
	}

}
