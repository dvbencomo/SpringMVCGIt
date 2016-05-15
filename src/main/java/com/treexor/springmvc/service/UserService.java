package com.treexor.springmvc.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;

import com.treexor.springmvc.model.User;


public interface UserService <T extends Serializable> {
	
	User findById(int id);
	
	User findBySSO(String sso);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserBySSO(String sso);
	
	void deleteUserById(int id);

	Page<T> findAllUsers(int page, int size);
	
	List<User> findAll(); 
	
	boolean isUserSSOUnique(Integer id, String sso);

}