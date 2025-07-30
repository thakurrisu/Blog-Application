package com.example.demo.Exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException{
  
	String resourceName;
	String fieldName;
	long fiealdValue;
	public ResourceNotFoundException(String resourceName, String fieldName , long fieladValue) {
		super(String.format("%s not found with %s : %s",resourceName,fieldName,fieladValue));
		this.resourceName = resourceName;
		this.fiealdValue = fieladValue;
		this.fieldName= fieldName;
	}
}
