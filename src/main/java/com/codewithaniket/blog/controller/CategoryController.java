package com.codewithaniket.blog.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.codewithaniket.blog.playload.ApiResponse;
import com.codewithaniket.blog.playload.CategoryDto;
import com.codewithaniket.blog.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createCatogery= this.categoryService.createCatogery(categoryDto);
		
		return new ResponseEntity<CategoryDto>( createCatogery,HttpStatus.CREATED);
		
		
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("userId") Integer userId  ){
		CategoryDto updatedCatogery= this.categoryService.updateCatogery(categoryDto, userId);
		
		return new ResponseEntity<CategoryDto>( updatedCatogery,HttpStatus.OK);
		
		
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("userId") Integer userId  ){
		this.categoryService.deleteCatogery(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted SeccesFully",true),HttpStatus.OK );
		
		
		
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("userId") Integer userId ){
	CategoryDto getCategoryById1=this.categoryService.getCatogeryById(userId);
		return new ResponseEntity<CategoryDto>(getCategoryById1,HttpStatus.FOUND);
		
		
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory( ){
	List<CategoryDto> getAllCategory=this.categoryService.getAllCatogery();
		return new ResponseEntity<List<CategoryDto>>(getAllCategory,HttpStatus.FOUND);
		
	}
}
