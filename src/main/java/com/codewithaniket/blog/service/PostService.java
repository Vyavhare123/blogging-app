package com.codewithaniket.blog.service;

import java.util.List;

import com.codewithaniket.blog.entity.Post;
import com.codewithaniket.blog.playload.PostDto;
import com.codewithaniket.blog.playload.PostResponse;

public interface PostService {
	
	public PostDto createPost(PostDto posDto,Integer userId,Integer categoryId);
	
	public PostDto updatePost(PostDto posDto,Integer postId);
	
	public void deletePost(Integer postId);
	
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	public PostDto getPostById(Integer postId);
	
	List<PostDto> getPostByCategory(Integer categoryId);
	
	public List<PostDto> getPostByUser(Integer userId);
	
	List<PostDto> SearchPosts(String keyword);

	//public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

	

}
