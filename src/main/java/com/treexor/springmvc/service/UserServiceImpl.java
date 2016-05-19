package com.treexor.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.treexor.springmvc.model.User;
import com.treexor.springmvc.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	public User create(User user) {
		return userRepository.save(user);
	}

	public void delete(int id) {
		 userRepository.delete(id);;
	}

	public List<User> findAll() {
		 		return userRepository.findAll();
	}

	public User update(User user) {
		return userRepository.saveAndFlush(user);
	}

	public User findById(int id) {
		
		return userRepository.findOne(id);
	}

	
	public Page<User> findAll(Pageable pageable) {
 		return userRepository.findAll(pageable);
}

	public void save(User user) {
			userRepository.save(user);
	}
	
	
	

}
