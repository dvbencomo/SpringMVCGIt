package com.treexor.springmvc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.treexor.springmvc.model.User;


public interface UserService {
	
	public User create(User user);
	public void delete(int id) ;
	public List<User> findAll();
	public User update(User user) ;
	public User findById(int id);
	public Page<User> findAll(Pageable pageable);
	public void save(User user);

}