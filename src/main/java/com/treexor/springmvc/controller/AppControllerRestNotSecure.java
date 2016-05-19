package com.treexor.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.treexor.springmvc.model.User;
import com.treexor.springmvc.service.UserService;



@RestController
@RequestMapping(value = "/api")
public class AppControllerRestNotSecure {

	@Autowired
	UserService userService;  //Service which will do all data retrieval/manipulation work
	
	@Autowired
	MessageSource messageSource;
	//-------------------Retrieve All Users--------------------------------------------------------
	
	//http://localhost:8080/TreexorPruebaTecnica/listUsers/
	@RequestMapping(value = "/listUsers/", method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.findAll();
		if(users.isEmpty()){
			return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value="/pages",method=RequestMethod.GET)
	@ResponseBody 
	public PageResource<User> contactsPages(@RequestParam int page,@RequestParam int size) {
		Pageable pageable = new PageRequest(
			page,size,new Sort("id")
		);
		Page<User> pageResult = userService.findAll(pageable);
		return new PageResource<User>(pageResult,"page","size");
	}
	
	
	
}
