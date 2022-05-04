package com.elearningapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.RestController;

import com.elearningapp.dao.UserRepository;
import com.elearningapp.exception.ServiceException;
import com.elearningapp.exception.ValidationException;
import com.elearningapp.model.User;
import com.elearningapp.validation.UserValidation;

@RestController
public class UserService {

	@Autowired
	UserRepository userRepository;

	public void register(User user) throws ValidationException, ServiceException {

		
		try {
			UserValidation.validateRegistrationData(user);
			User userCheck = userRepository.findByEmail(user.getEmail());
			if(userCheck==null){
				userRepository.save(user);
			}
			else {
				throw new ValidationException("Already Registered");
			}
		}catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
	}

	public User login(String email, String password) throws Exception {
		UserValidation.validateLoginData(email, password);
		User user = null;
		try {
			user = userRepository.findByEmail(email);
			if(user == null) {
				System.out.println("Unregistered User");
				throw new Exception("Not a Registered User");
			}
			else if(user.getPassword().equals(password)) {
				System.out.println(user.getName()+" LoggedIn sucessfully");
				return user;
			}
			else {
				throw new Exception("Invalid credentials");
			}
		}
		catch (Exception e) 
		{
			throw new Exception(e.getMessage());
		}
	}

}
