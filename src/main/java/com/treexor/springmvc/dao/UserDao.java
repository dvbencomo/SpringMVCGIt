package com.treexor.springmvc.dao;

import java.util.List;

import com.treexor.springmvc.model.User;


public interface UserDao {

	User findById(int id);
	
	User findBySSO(String sso);
	
	void save(User user);
	
	void deleteBySSO(String sso);
	
	void deleteById(int id);

	List<User> findAllUsers();

}

