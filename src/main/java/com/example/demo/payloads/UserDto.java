package com.example.demo.payloads;

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
	private String password;
	
	
	
}
