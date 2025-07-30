package com.example.demo.services;
import java.util.*;

import com.example.demo.payloads.CategoryDto;

public interface CategoryService {

	//GET
	public CategoryDto getCategoryById(Integer Id);
	
	//GetAll
	
	public List<CategoryDto> getAllCategory();
	//Create
	
	public CategoryDto createCategory(CategoryDto category);
	//Update
	
	public CategoryDto updateCategory(CategoryDto category , Integer Id);
	//Delete
	public void deleteCategory(Integer Id);
}
