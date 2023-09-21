package com.codewithaniket.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithaniket.blog.entity.Category;
import com.codewithaniket.blog.entity.Post;
import com.codewithaniket.blog.entity.User;
import com.codewithaniket.blog.playload.PostDto;

public interface PostRepo extends JpaRepository<Post, Integer>{
	
	List<Post> findByUser(User user);
	
	List<Post>findByCategory(Category category);
	List<Post>findByTitleContaining(String title);

}
