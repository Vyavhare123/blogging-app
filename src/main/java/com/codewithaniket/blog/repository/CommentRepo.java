package com.codewithaniket.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithaniket.blog.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

}
