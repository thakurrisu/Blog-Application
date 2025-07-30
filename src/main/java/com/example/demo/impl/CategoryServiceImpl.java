package com.example.demo.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Repositories.CategoryRepo;
import com.example.demo.models.Category;

import com.example.demo.payloads.CategoryDto;
import com.example.demo.services.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CategoryDto getCategoryById(Integer Id) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepo.findById(Id).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", Id));
		CategoryDto categoryDtoById = this.modelMapper.map(category, CategoryDto.class);
		
		return categoryDtoById;
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		// TODO Auto-generated method stub
		List<Category> allCategory = this.categoryRepo.findAll();
		List<CategoryDto> allCategoryDto = allCategory.stream().map(category -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		return allCategoryDto;
	}

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category categoryToSave = this.modelMapper.map(categoryDto, Category.class);
		Category savedCategory = this.categoryRepo.save(categoryToSave);
		return this.modelMapper.map(savedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer Id) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepo.findById(Id).orElseThrow(()->new ResourceNotFoundException("Category","Category ID",Id));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setDescriptionString(categoryDto.getDescriptionString());
		this.categoryRepo.save(category);
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer Id) {
		// TODO Auto-generated method stub
		Category cat = this.categoryRepo.findById(Id).orElseThrow(()->new ResourceNotFoundException("Category","category Id",Id));
	
	    this.categoryRepo.delete(cat);
	}

}
