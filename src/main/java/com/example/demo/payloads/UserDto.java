package com.example.demo.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class UserDto {

	private int id;
	private String name;
	private String email;
	 @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	
	
}
