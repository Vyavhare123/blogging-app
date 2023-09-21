package com.codewithaniket.blog.service;

import com.codewithaniket.blog.playload.CommentDto;

public interface CommentService {
	
	public CommentDto createComment(CommentDto commentDto ,Integer postId);
	
	void deleteComment(Integer commentId);

}
