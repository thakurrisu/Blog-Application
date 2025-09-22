package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.impl.CustomUserDetailService;
import com.example.demo.models.User;
import com.example.demo.payloads.AuthRequest;
import com.example.demo.payloads.AuthResponse;
import com.example.demo.services.JWTService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/auth/")
@Tag(name = "Category APIs")
public class AuthController {
	@Autowired
	public AuthenticationManager authManager;
	@Autowired
	public CustomUserDetailService userDetailService;
	@Autowired
	public JWTService jwtUtil;
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest){
		
			 Authentication authenticate = authManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                    		authRequest.getUserName(),
	                    		authRequest.getPassword()
	                    )
	            );
			    User userDetails = (User) authenticate.getPrincipal();
			    String jwt = jwtUtil.GenerateToken(userDetails);
			    AuthResponse authResponse = new AuthResponse(userDetails.getUsername(),jwt);
			    return ResponseEntity.ok(authResponse);
	      
		 
    

	}
	

}
