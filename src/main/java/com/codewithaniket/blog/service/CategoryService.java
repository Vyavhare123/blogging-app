package com.codewithaniket.blog.service;

import java.util.List;

import com.codewithaniket.blog.playload.CategoryDto;

public interface CategoryService {
	
	public CategoryDto createCatogery(CategoryDto categoryDto);
	
	public CategoryDto updateCatogery(CategoryDto categoryDto,Integer categoryId);
	
	public void deleteCatogery(Integer categoryId);
	
	public CategoryDto getCatogeryById(Integer categoryId);
	
	public List<CategoryDto> getAllCatogery();

}
