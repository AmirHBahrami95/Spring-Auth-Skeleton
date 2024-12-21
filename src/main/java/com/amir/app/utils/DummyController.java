package com.amir.app.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amir.app.user.data.DomainUserDto;
import com.amir.app.user.data.DomainUser;

@RestController
@RequestMapping(path="/dummy")
public class DummyController {
	
	@GetMapping(path="/dip",produces="application/json")
	public ResponseEntity<String> shit(){
		return ResponseEntity.ok("DIP-TEST");
	}
	
	@GetMapping(path="/whoami",produces="application/json")
	public ResponseEntity<DomainUserDto> whoami(Authentication a){
		return ResponseEntity.ofNullable(((DomainUser)a.getDetails()).toDto());
	}
	
}