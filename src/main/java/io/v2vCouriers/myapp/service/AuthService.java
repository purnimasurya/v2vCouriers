package io.v2vCouriers.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.v2vCouriers.myapp.dto.LoginRequest;
import io.v2vCouriers.myapp.dto.RegisterRequest;
import io.v2vCouriers.myapp.model.User;
import io.v2vCouriers.myapp.repository.UserRepository;
//import io.v2vCouriers.myapp.security.JwtProvider;
import io.v2vCouriers.myapp.util.JwtUtil;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	public void signup(RegisterRequest registerRequest) {
		User user = new User();
		user.setFirstName(registerRequest.getFirstName());
		user.setLastName(registerRequest.getLastName());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(encodePassword(registerRequest.getPassword()));
		userRepository.save(user);
	}
	
	public String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}
	
	public String login(LoginRequest loginRequest) throws Exception {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
					(loginRequest.getEmail(), loginRequest.getPassword()));
		}
		catch (Exception e) {
			throw new Exception("Invalid Username/Password");
		}
		return jwtUtil.generateToken(loginRequest.getEmail());
		
	}
}
