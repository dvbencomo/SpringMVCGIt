package com.treexor.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.treexor.springmvc.model.User;
import com.treexor.springmvc.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



@RestController
@RequestMapping(value = "/apiSecure")
@Api(value = "infos", description = "Métodos Disponibles", produces = "application/json")
public class AppControllerRestSecure {

	@Autowired
	UserService userService;  //Service which will do all data retrieval/manipulation work
	
	@Autowired
	MessageSource messageSource;
	//-------------------Retrieve All Users--------------------------------------------------------
	
	//http://localhost:8080/TreexorPruebaTecnica/listUsers/
	@RequestMapping(value = "/listUsers/", method = RequestMethod.GET ,produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get Usuarios", notes = "Retorna todos los usuarios ")
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.findAll();
		if(users.isEmpty()){
			return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	//http://localhost:8080/TreexorPruebaTecnica/pages?page={page}&size={size}
	//http://localhost:8080/TreexorPruebaTecnica/pages?page=1&size=5
	
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "get User", notes = "Obtiene Información de un Usuario")
	public ResponseEntity<User> getUser(@PathVariable("id") int id) {
		User user = userService.findById(id);
		if (user == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	//------------------- Delete a User --------------------------------------------------------
	@RequestMapping(value = "/deleteUser/{id}",  method = RequestMethod.DELETE)
	@ApiOperation(value = "delete User", notes = "Elimina un Usuario")
	public ResponseEntity<User> deleteUser(@PathVariable("id") int id) {
		User user = userService.findById(id);
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.OK);
	}
		
	//-------------------Create a User--------------------------------------------------------
		
	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	@ApiOperation(value = "Create User", notes = "Crea un Usuario")
	public ResponseEntity<Void> createUser(@RequestBody User user, 	UriComponentsBuilder ucBuilder) {
		User userAux = userService.findBySSO(user.getSsoId());
		
		if (userAux != null ) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		userService.saveUser(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	//------------------- Update a User --------------------------------------------------------
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Update User", notes = "Actuliza un Usuario")
	public ResponseEntity<User> updateUser(@PathVariable("id") String sso, @RequestBody User user) {

		User currentUser = userService.findBySSO(sso);
		
		if (currentUser==null) {
			System.out.println("User with id " + sso + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		currentUser.setEmail(user.getEmail());
		currentUser.setFirstName(user.getFirstName());
		currentUser.setLastName(user.getLastName());
		currentUser.setPassword(user.getPassword());
		userService.updateUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}
}
