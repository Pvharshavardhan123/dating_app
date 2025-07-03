package com.mydating.dating.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.mydating.dating.entity.User;
import com.mydating.dating.service.UserService;

@RestController //used to access the client request
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping("/users")
	public ResponseEntity<?> saveUser(@RequestBody User user){
		return userService.saveUser(user);
	}
	
	@GetMapping("/users/gender/male")
	public ResponseEntity<?> findAllMaleUsers(){
		return userService.findAllMaleUsers();
	}
	@GetMapping("/users/gender/female")
	public ResponseEntity<?> findAllFemaleUsers(){
		return userService.findAllFemaleUsers();
	}
			
//	@GetMapping("/users/")
	//find by age id name email pohone is assignment
	@GetMapping("/users/best-match/{id}/{top}")
//	which user id and how many top matches to find
	public ResponseEntity<?> findBestMatch(@PathVariable int id,@PathVariable int top){
		return userService.findBestMatch(id,top);
	}
	@GetMapping("/users/{id}")
	public ResponseEntity<?> findbyId(@PathVariable int id){
		return userService.findById(id);
	}
	@GetMapping("/users/name/{name}")
	public ResponseEntity<?>findByName(@PathVariable String name){
		return userService.findByName(name);
	}
	@GetMapping("/users/email")
	public ResponseEntity<?> findByEmail(@RequestParam String email){
		return userService.findByEmail(email);
	}
	@GetMapping("/users/phone/{phone}")
	public ResponseEntity<?>findByPhone(@PathVariable long phone){
		return userService.findByPhone(phone);
	}
	@GetMapping("/users/age/{age}")  //localhost:8080/users/age/45
	public ResponseEntity<?>findByAge(@PathVariable int age){
		return userService.findByAge(age);
	}
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable int id) {
	    return userService.deleteUser(id);
	}


	

	
}