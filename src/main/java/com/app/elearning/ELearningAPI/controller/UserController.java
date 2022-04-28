package com.app.elearning.ELearningAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.elearning.ELearningAPI.dao.UserRepository;
import com.app.elearning.ELearningAPI.model.User;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	@PostMapping("users/save")
	public void save(@RequestBody User user) {
		userRepository.save(user);
	}

	@GetMapping("users/list")
	public List<User> findAll() {
		List<User> userList = userRepository.findAll();
		return userList;
	}

	@DeleteMapping("users/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		userRepository.deleteById(id);
	}

	@PutMapping("users/change-password/{id}")
	public void changePassword(@PathVariable("id") Integer id, @RequestParam("password") String password) {
		userRepository.changePassword(id, password);
	}

	@GetMapping("users/login")
	public String findByEmailAndPassword(@RequestParam("email") String email,@RequestParam("password") String password)
	{
		String message=null;
		User user = null;
		try {
		    user=userRepository.findByEmailAndPassword(email, password);
		 if(user!=null)
		{
			message="Sucess";
		}
		else
		{
			throw new Exception("invalid");
		}
		}
		catch(Exception e) {
			message = e.getMessage();
		}
		
		return message;
	}

}
