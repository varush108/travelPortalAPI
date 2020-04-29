package com.nagarro.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;
import com.nagarro.entities.User;
import com.nagarro.repositories.UserRepository;
import com.nagarro.utils.PasswordGenerator;


@RestController
@RequestMapping("/travelApi/v1")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	/** 
	 * Get all users list.
	 * 
	 * @return the list of all the user objects or empty list if no user exist
	*/
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	/**
	 * Get the user object for a specific user id
	 * @param usserID 
	 * @return the user object of that id or null if doesnt exist
	 * @throws ResourceAccessException
	 */
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUser(@PathVariable(value = "id") Long userId) 
			throws ResourceAccessException{
		User user =
				userRepository
				.findById(userId)
				.orElseThrow(() -> 
				new ResourceAccessException("User not found for id : "+ userId));    
		
		return ResponseEntity.ok().body(user); 
	}
	
	/**
	 * Get the user for a specified email
	 * @param email of the user
	 * @return the user object associated with that email or null if doesnt exist
	 * @throws ResourceAccessException
	 */
	@GetMapping("/userByEmail")
	public ResponseEntity<User> getUserByEmail(@RequestParam(value = "email") 
			String email) throws ResourceAccessException{
		User user =
				userRepository
				.findByEmail(email, PageRequest.of(0, 1)).getContent().get(0);
		
		return ResponseEntity.ok().body(user);  
	}
	
	/**
	 * Posts a new user into the database
	 * @param user object as JSON
	 * @return the newly created user object
	 */
	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User user) {
		user.setPassword(PasswordGenerator.generatePassword(8));
		return userRepository.save(user);
    }
}
