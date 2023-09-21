package com.codewithaniket.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.codewithaniket.blog.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	Optional<User>findByEmail(String email);
}
