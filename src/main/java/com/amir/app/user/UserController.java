package com.amir.app.user;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/hello")
	public ResponseEntity<String> hello(){
		return ResponseEntity.of(Optional.of("Hello User!"));
	}
	
	@GetMapping("/example")
	public ResponseEntity<User> example(){
		return ResponseEntity.of(Optional.of(new User()));
	}
	
}
