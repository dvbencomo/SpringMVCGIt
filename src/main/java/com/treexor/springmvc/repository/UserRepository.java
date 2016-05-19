package com.treexor.springmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.treexor.springmvc.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	
}
