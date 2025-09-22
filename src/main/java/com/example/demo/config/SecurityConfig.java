package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.impl.CustomUserDetailService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.config.Customizer;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{

	
	private final JwtAuthFilter jwtAuthFilter;
	private final CustomUserDetailService userDetailsService;
	
	//Describes what filter to use
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		  http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth->auth .requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
                        // Allow POST request only for /api/v1/users/create
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/create").permitAll().anyRequest().authenticated())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider());
              //Creates with default basic authentication
              return http.build();
	}
	
	  @Bean
	    public AuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(userDetailsService);
	        authProvider.setPasswordEncoder(passwordEncoder());
	        return authProvider;
	    }

	    @Bean
	    public AuthenticationManager authenticationManager(
	            AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }
	@Bean
	public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	}
	
	
}
