package com.example.demo.services;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import com.example.demo.models.User;

@Component
public class JWTService {

	@Value("${jwt.secret}")
	private String jwtSecret; 
	
	@Value("${jwt.expiration-ms}")
	private long expiration_time;
	
	 private Key getSignInKey() {
	        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
	    }
	 
	 
	public String GenerateToken(User user)
	{
		return Jwts.builder()
				.setSubject(user.getName())
				.setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration_time))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
	}
	
	 public String extractUsername(String token) {
	      //  log.info("The uuser is : {}",extractClaim(token, Claims::getSubject));
	        return extractClaim(token, Claims::getSubject);
	    }
	 
	 public boolean isTokenValid(String token, User user) {
	        final String username = extractUsername(token);
	        return username.equals(user.getName()) && !isTokenExpired(token);
	    }

	    private boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }

	    private Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }
	 public <T> T extractClaim(String token, Function<Claims, T> resolver) {
	        final Claims claims = extractAllClaims(token);
	        return resolver.apply(claims);
	    }

	    private Claims extractAllClaims(String token) {
	        return Jwts.parserBuilder()
	                .setSigningKey(getSignInKey())
	                .build()
	                .parseClaimsJws(token)
	                .getBody();
	    }
}
