package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.impl.CustomUserDetailService;

import org.springframework.security.config.Customizer;


@Configuration
@EnableWebSecurity
public class SecurityConfig{

	
	//Describes what filter to use
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		  http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth->auth .requestMatchers(HttpMethod.GET, "/api/v1/users/**").permitAll()
                        // Allow POST request only for /api/v1/users/create
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/create").permitAll().anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());
              //Creates with default basic authentication
              return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	}
	
	
}
