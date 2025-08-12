package com.example.demo.payloads;

import com.example.demo.models.Category;
import com.example.demo.models.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private String content;
	
    private String title;
    
    private CategoryDto category;
    
    private UserDto user;
    
}
