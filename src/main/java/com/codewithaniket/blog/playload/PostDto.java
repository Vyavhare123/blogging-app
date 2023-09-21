package com.codewithaniket.blog.playload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ManyToOne;

import com.codewithaniket.blog.entity.Category;
import com.codewithaniket.blog.entity.Comment;
import com.codewithaniket.blog.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

	private Integer postId;
	private String title;
	private String content;

	private String imageName;

	private Date addedDate;

	private CategoryDto category;

	private UserDto user;
	
	private Set<CommentDto> comment=new HashSet<CommentDto>();

}
