package com.codewithaniket.blog.controller;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.apache.logging.log4j.message.Message;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Producer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codewithaniket.blog.playload.FileResponse;
import com.codewithaniket.blog.playload.PostDto;
import com.codewithaniket.blog.service.FileService;
import com.codewithaniket.blog.service.PostService;

@RestController
@RequestMapping("/file")
public class FileController {
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	@Value("${project.image}")
	private String path;
	
	
	
	@PostMapping("/upload/{postId}")
	public ResponseEntity<PostDto> fileUpload(@RequestParam("image")MultipartFile image,@PathVariable("postId") Integer postId) throws IOException{
		
		String fileName=null;
		    PostDto postDto = this.postService.getPostById(postId);
			fileName = this.fileService.uploadImage(path, image);
			
			postDto.setImageName(fileName);
			PostDto updatePost = postService.updatePost(postDto, postId);
		    return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
		
	}
	
	// method to serve file
	
	@GetMapping(value="/profile/{imageName}",produces=MediaType.IMAGE_JPEG_VALUE)
	public void downloadFile(@PathVariable("imageName")String imageName,HttpServletResponse response) throws IOException {
		
		InputStream resource=this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		
		StreamUtils.copy(resource, response.getOutputStream());
	}

}
