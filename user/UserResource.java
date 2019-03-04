package com.samplespring.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource { //this can also be called UserController
    
	@Autowired
	private UserDaoService service;
	
	//GET /users
	//retreiveAllUsers - get details of all users
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}
	
	//GET /users/{id}
	//retrieveUser(int id) - get details of specific user
	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		
		if (user==null)
			throw new UserNotFoundException("id-" + id);
		
		//"all-users", SERVER_PATH + "/users"
		//retrieveAllUsers
		//you can add other resources and links here as well
		Resource<User> resource = new Resource<User>(user);
		
		//the link you want to use
		ControllerLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		//HATEOAS
		
		return resource;
	}
	
	//GET /users/{id}
	//deleteUser(int id) - delete details of specific user
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);
		if (user==null)
			throw new UserNotFoundException("id-" + id);
	}
	
	//HATEOAS - Hypermedia as the engine of application services
	
	//CREATED
	// input - details of user
	// output - CREATED @ Return the created URI
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		//CREATED
		// /user/{id}  -- savedUser.getId()
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}

}
