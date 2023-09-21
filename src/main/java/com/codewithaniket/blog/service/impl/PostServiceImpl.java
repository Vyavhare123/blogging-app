package com.codewithaniket.blog.service.impl;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.codewithaniket.blog.entity.Category;
import com.codewithaniket.blog.entity.Post;
import com.codewithaniket.blog.entity.User;
import com.codewithaniket.blog.exception.ResourceNotFoundException;
import com.codewithaniket.blog.playload.PostDto;
import com.codewithaniket.blog.playload.PostResponse;
import com.codewithaniket.blog.repository.CategoryRepo;
import com.codewithaniket.blog.repository.PostRepo;
import com.codewithaniket.blog.repository.UserRepo;
import com.codewithaniket.blog.service.PostService;



@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private UserRepo userRepo;

	@Override
	public PostDto createPost(PostDto posDto, Integer userId, Integer categoryId) {
		Post savepost = this.modelMapper.map(posDto, Post.class);
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

		savepost.setImageName("default.png");
		savepost.setAddedDate(new Date());
		savepost.setUser(user);
		savepost.setCategory(category);
		Post addPost = this.postRepo.save(savepost);
		return this.modelMapper.map(addPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto posDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId",postId));
		post.setTitle(posDto.getTitle());
		post.setContent(posDto.getContent());
		post.setImageName(posDto.getImageName());
		
		Post updatedPost=this.postRepo.save(post);
		return this.modelMapper.map(updatedPost,PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId",postId));

		 this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		
//		int pageSize=5;
//		int pageNumber=1;
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort=Sort.by(sortBy).ascending();
			
		}else {
			
			sort=Sort.by(sortBy).descending();
		}
		Pageable sortedByName=PageRequest.of(pageNumber,pageSize,sort);
		 Page<Post> pagePost = this.postRepo.findAll(sortedByName);

		List<PostDto> postDto =pagePost.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		 postResponse.setContent(postDto);
		 postResponse.setPageNumber(pagePost.getNumber());
		 postResponse.setPageSize(pagePost.getSize());
		 postResponse.setTotalElement (pagePost.getTotalElements());
		 postResponse.setTotalPages( pagePost.getTotalPages());
		 postResponse.setLastPage( pagePost.isLast());
		 return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
	Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
		List<Post> posts = this.postRepo.findByCategory(cat);
		List<PostDto> postDto = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostDto> SearchPosts(String keyword) {
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
         List<PostDto> postDt = posts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDt;
		
		
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postDto = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDto;
	}
}
