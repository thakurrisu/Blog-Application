package com.example.demo.payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class CategoryDto {


	private Integer Id;
	

	private String categoryTitle;
	
	
	private String descriptionString;
}
