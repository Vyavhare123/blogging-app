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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codewithaniket.blog.config.AppConstant;
import com.codewithaniket.blog.entity.Post;
import com.codewithaniket.blog.playload.ApiResponse;
import com.codewithaniket.blog.playload.PostDto;
import com.codewithaniket.blog.playload.PostResponse;
import com.codewithaniket.blog.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable("userId") Integer userId,
			@PathVariable("categoryId") Integer categoryId) {

		PostDto savePost = this.postService.createPost(postDto, userId, categoryId);

		return new ResponseEntity<PostDto>(savePost, HttpStatus.CREATED);

	}

	@GetMapping("/users/{userid}/post")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable("userid") Integer userId) {
		List<PostDto> post = this.postService.getPostByUser(userId);

		return new ResponseEntity<List<PostDto>>(post, HttpStatus.OK);

	}

	@GetMapping("/category/{categoryId}/post")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable("categoryId") Integer categoryId) {
		List<PostDto> post = this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(post, HttpStatus.OK);

	}

	@GetMapping("/post")
	public ResponseEntity<PostResponse> getAllPost( 
			@RequestParam(value = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false) Integer pageNumber ,
			@RequestParam(value = "pageSize",defaultValue = AppConstant.PAGE_SIZE,required = false)Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue =AppConstant.SORT_BY,required = false)String  sortBy,
			@RequestParam(value = "sortDir",defaultValue =AppConstant.SORT_DIR,required = false)String  sortDir
			) {
		PostResponse post = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(post, HttpStatus.OK);

	}

	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPostByPostId(@PathVariable("postId") Integer postId) {
		PostDto post = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(post, HttpStatus.OK);

	}

	@DeleteMapping("/post/{postId}")
	public ResponseEntity<ApiResponse> deletePostByPostId(@PathVariable("postId") Integer postId) {
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("post Deleted SeccesFully", true), HttpStatus.OK);

	}

	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("postId") Integer postId) {
		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);

	}
	
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>>searchPostTitle(@PathVariable("keyword") String keyword)
	{
		 List<PostDto> searchPosts = this.postService.SearchPosts(keyword);
		return new  ResponseEntity<List<PostDto>>(searchPosts,HttpStatus.FOUND);	
		
	}
			
	}

