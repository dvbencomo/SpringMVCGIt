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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
	@ApiOperation(value = "listAllUsers", notes = "Retorna todos los usuarios ")
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.findAll();
		if(users.isEmpty()){
			return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	
	//http://localhost:8080/TreexorPruebaTecnica/api/pages?page=13&size=1
	
	@RequestMapping(value="/pages",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	@ApiOperation(value = "userPages", notes = "Lista Usuarios paginados")
	public PageResource<User> userPages(@ApiParam(value = "Pagina a la que se desea acceder", required = true) @RequestParam int page,
											@ApiParam(value = "Número de registros por página", required = true)@RequestParam int size) {
		Pageable pageable = new PageRequest(
			page,size,new Sort("id")
		);
		Page<User> pageResult = userService.findAll(pageable);
		return new PageResource<User>(pageResult,"page","size");
	}
	
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "getUser", notes = "Obtiene información de un usuario")
	public ResponseEntity<User> getUser(@ApiParam(value = "id del usuario", required = true) @PathVariable("id") int id) {
		User user = userService.findById(id);
		if (user == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteUser/{id}",  method = RequestMethod.DELETE)
	@ApiOperation(value = "deleteUser", notes = "Elimina un usuario")
	public ResponseEntity<User> deleteUser(@ApiParam(value = "id del usuario") @PathVariable("id") int id) {
		User user = userService.findById(id);
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		userService.delete(id);
		return new ResponseEntity<User>(HttpStatus.OK);
	}
				
	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	@ApiOperation(value = "createUser", notes = "Creación de un usuario")
	public ResponseEntity<Void> createUser( @ApiParam(value = "Entidad Usuario")
											@RequestBody User user, 	UriComponentsBuilder ucBuilder) {
		User userAux = userService.findById(user.getId());
		
		if (userAux != null ) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		userService.save(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
		
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "updateUser", notes = "Actualizacion de Usuario")
	public ResponseEntity<User> updateUser(@ApiParam(value = "id de Usuario") @PathVariable("id") int id, 
											@ApiParam(value = "Entidad Usuario") @RequestBody User user) {

		User currentUser = userService.findById(id);
		
		if (currentUser==null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		currentUser.setEmail(user.getEmail());
		currentUser.setFirstName(user.getFirstName());
		currentUser.setLastName(user.getLastName());
		currentUser.setPassword(user.getPassword());
		userService.update(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}
	
	
}
