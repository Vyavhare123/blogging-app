package com.codewithaniket.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithaniket.blog.entity.Category;
import com.codewithaniket.blog.exception.ResourceNotFoundException;
import com.codewithaniket.blog.playload.CategoryDto;
import com.codewithaniket.blog.repository.CategoryRepo;
import com.codewithaniket.blog.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCatogery(CategoryDto categoryDto) {
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		Category save = this.categoryRepo.save(cat);
		return this.modelMapper.map(save, CategoryDto.class);

	}

	@Override
	public CategoryDto updateCatogery(CategoryDto categoryDto, Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));
		cat.setCatogeryTitle(categoryDto.getCatogeryTitle());
		cat.setCotegoryDiscription(categoryDto.getCotegoryDiscription());

		Category updateCat = categoryRepo.save(cat);
		return this.modelMapper.map(updateCat, CategoryDto.class);
	}

	@Override
	public void deleteCatogery(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));

		this.categoryRepo.delete(cat);

	}

	@Override
	public CategoryDto getCatogeryById(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));

		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCatogery() {
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> catDto = categories.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class))
				.collect(Collectors.toList());
		return catDto;
	}

}
