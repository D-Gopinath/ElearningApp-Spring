package com.elearningapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elearningapp.dao.UserRepository;
import com.elearningapp.model.User;
import com.elearningapp.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;

	@PostMapping("users/register")
	public ResponseEntity<String> register(@RequestBody User user) {
		
		try {
			userService.register(user);
			return new ResponseEntity<String>("Successfully Registered",HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("users/list")
	public List<User> findAll() {
		List<User> userList = userRepository.findAll();
		return userList;
	}

	@DeleteMapping("users/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		try {
		userRepository.deleteById(id);
		System.out.println("User Deleted");
		return "User deleted";
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return "Unable to delete User";
		}
	}

	@PutMapping("users/change-password/{id}")
	public String changePassword(@PathVariable("id") Integer id, @RequestParam("password") String password) {
		try {
			userRepository.changePassword(id, password);
			System.out.println("password changed...");
			return "Password Changed Successfully";
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
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
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

}
