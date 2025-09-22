package com.example.demo.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.impl.CustomUserDetailService;
import com.example.demo.models.User;
import com.example.demo.services.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{

	private CustomUserDetailService customUserDetailService;
	private JWTService jwtService;
	

    @Autowired
    public JwtAuthFilter(CustomUserDetailService customUserDetailService, JWTService jwtService) {
        this.customUserDetailService = customUserDetailService;
        this.jwtService = jwtService;
    }
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authHeader = request.getHeader("Authorization");
		String username = null;
		String jwt = null;

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
		    jwt = authHeader.substring(7); // remove "Bearer " prefix
		    username = jwtService.extractUsername(jwt);
		}
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
		    User userDetails = (User) customUserDetailService.loadUserByUsername(username);
		    	
		    if (jwtService.isTokenValid(jwt, userDetails)) {
		    	UsernamePasswordAuthenticationToken authToken = 
		            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		        SecurityContextHolder.getContext().setAuthentication(authToken);
		    }
		}
		filterChain.doFilter(request, response);
		
		
		// TODO Auto-generated method stub
		
	}

}
