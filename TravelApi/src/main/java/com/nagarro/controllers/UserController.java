package com.nagarro.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import com.nagarro.entities.User;
import com.nagarro.repositories.UserRepository;

@RestController
@RequestMapping("/travelApi/v1")
public class UserController {
  @Autowired
  private UserRepository userRepository;
  /** 
   * Get all users list.
   *
   * @return the list
   */
  @GetMapping("/users")
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }
  
  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUser(@PathVariable(value = "id") Long userId) throws ResourceAccessException{
	  User user =
		        userRepository
		            .findById(userId)
		            .orElseThrow(() -> new ResourceAccessException("User not found on :: " + userId));
		    return ResponseEntity.ok().body(user);  }
  

  @PostMapping("/users")
  public User createUser(@Valid @RequestBody User user) {
	
    return userRepository.save(user);
  }
}
