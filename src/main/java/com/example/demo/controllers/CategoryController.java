package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.example.demo.payloads.CategoryDto;
import com.example.demo.services.CategoryService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/categories")
@Tag(name = "Category APIs")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryservice;
	//GET
	@GetMapping("/{catID}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer catID){
		CategoryDto category = this.categoryservice.getCategoryById(catID);
		return new ResponseEntity<>(category,HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategories()
	{
		List<CategoryDto> categoryList = this.categoryservice.getAllCategory();
		return new ResponseEntity<>(categoryList,HttpStatus.OK);
		
	}
	//POST
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto categoryDtoCreated = this.categoryservice.createCategory(categoryDto);
		return new ResponseEntity<>(categoryDtoCreated,HttpStatus.OK);
	}
	
	//PUT
	@PutMapping("/{Id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto ,@PathVariable Integer Id){
		CategoryDto updatedCategory = this.categoryservice.updateCategory(categoryDto, Id);
		return new ResponseEntity<>(updatedCategory,HttpStatus.OK);
	}
	//Delete
	@DeleteMapping("/{cateID}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Integer cateID)
	{
		this.categoryservice.deleteCategory(cateID);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
