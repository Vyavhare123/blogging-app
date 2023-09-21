package com.codewithaniket.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithaniket.blog.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
	
	

}
