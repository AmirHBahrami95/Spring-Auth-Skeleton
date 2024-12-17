package com.amir.app.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amir.app.user.data.DomainUserDto;
import com.amir.app.user.data.DomainUser;

@RestController
@RequestMapping(path="/api/user")
public class UserController {
	
	@Autowired 
	private UserService userService;
	
	@GetMapping("/hello")
	public ResponseEntity<String> hello(){
		return ResponseEntity.of(Optional.of("Hello User!"));
	}
	
	@PostMapping(path = "/register",name = "/register",produces = "application/json",consumes = "application/json")
	public ResponseEntity<String> register(@RequestBody DomainUser ue){ // TODO add "where's the problem support"
		return userService.addUser(ue)?ResponseEntity.ok().build():ResponseEntity.badRequest().build();
	}
	
	@GetMapping(path="/{username}",produces="application/json")
	public ResponseEntity<DomainUserDto> getUserDto(@PathVariable(value="username") String username){
		return ResponseEntity.of(userService.getDomainUserDto(username));
	}
	
}
