package com.elearningapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.elearningapp.dto.Message;
import com.elearningapp.model.User;
import com.elearningapp.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("users/register")
	public ResponseEntity<?> register(@RequestBody User user) {
		
		try {
			userService.register(user);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("users/change-password/{id}")
	public ResponseEntity<?> changePassword(@PathVariable("id") Integer id, @RequestBody User user) {
		try {
			userService.updatePassword(id, user.getPassword());
			System.out.println("password changed...");
			return new ResponseEntity<>(HttpStatus.OK);
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
		
	}

	@PostMapping("users/login")
	public ResponseEntity<?> login(@RequestBody User user)
	{
		User response;
		try {
			response=userService.login(user.getEmail(),user.getPassword());
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		catch(Exception e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
	}

}
