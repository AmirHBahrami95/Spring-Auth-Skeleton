package com.amir.app.user;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amir.app.user.data.DomainUserDto;
import com.amir.app.user.data.UserToken;
import com.amir.app.user.impl.UserServiceImpl;
import com.amir.app.user.data.DomainUser;

@RestController
@RequestMapping(path="/api/user")
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired 
	private UserService userService;
	//private UserServiceImpl userService;
	
	@GetMapping("/hello")
	public ResponseEntity<String> hello(){
		return ResponseEntity.of(Optional.of("Hello User!"));
	}
	
	@PostMapping(path = "/register",produces = "application/json",consumes = "application/json")
	public ResponseEntity<String> register(@RequestBody DomainUser ue){ 
		Optional<String> token=userService.register(ue);
		return token.isPresent()?
			ResponseEntity.of(token)
			:ResponseEntity.badRequest().build()
		;
	}
	
	@PostMapping(path = "/login",produces = "application/json",consumes = "application/json")
	@Transactional
	public ResponseEntity<UserToken> login(@RequestBody DomainUser ue) {
		try {
			Optional<String> token=userService.login(ue);
			UserToken ut=new UserToken();
			if(token.isPresent()) ut.setToken(token.get());
			return ResponseEntity.ofNullable(ut);
		}catch(BadCredentialsException bce) { 
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping(path = "/logout",produces = "application/json" )
	public ResponseEntity<String> logout(Authentication a){
		// TODO test
		return userService.logout(a.getName())?ResponseEntity.ok().build():ResponseEntity.badRequest().body("You are not logged in :|");
	} 
	
	@GetMapping(path="/{username}",produces="application/json")
	public ResponseEntity<DomainUserDto> getUserDto(@PathVariable(value="username") String username){
		return ResponseEntity.of(userService.getDtoByUname(username));
	}
	
}
