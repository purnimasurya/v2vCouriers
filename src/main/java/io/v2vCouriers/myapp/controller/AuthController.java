package io.v2vCouriers.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.v2vCouriers.myapp.dto.LoginRequest;
import io.v2vCouriers.myapp.dto.RegisterRequest;
import io.v2vCouriers.myapp.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService; 
	
	@PostMapping("/signup")
	public ResponseEntity<Object> signup(@RequestBody RegisterRequest registerRequest) {
		authService.signup(registerRequest);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	

	@PostMapping("/login")
	public String login(@RequestBody LoginRequest loginRequest) throws Exception {
		String msg = authService.login(loginRequest);
		if (msg != null)
		{
			return "Welcome";
		}
		else {
			
			return "Invalid Credentials";
		}
	}
}
