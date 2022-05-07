package com.elearningapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.elearningapp.dao.AdminRepository;
import com.elearningapp.model.Course;
import com.elearningapp.model.User;
import com.elearningapp.validation.UserValidation;
@RestController
public class AdminService {
	
	@Autowired
	AdminRepository adminRepository;
	
	public User adminLogin(String email,String password) throws Exception {
		UserValidation.validateLoginData(email, password);
		User user = null;
		try {
			user = adminRepository.findByEmail(email);
			if(user == null) {
				System.out.println("Unregistered User");
				throw new Exception("Access Denied");
			}
			else if(user.getPassword().equals(password)) {
				System.out.println(user.getName()+"Access Granted");
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

	public List<User> showUsers() throws Exception{
		try {
			List<User> usersList = adminRepository.findAll();
			return usersList;
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
			
		}
	}
	
	public void removeUser(Integer id) throws Exception {
		try {
			adminRepository.deleteById(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void insertCourse(Course course)throws Exception{
		
		try {
			adminRepository.save(course);
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}
	
	public void removeCourse(Integer cid)throws Exception{
		try {
			adminRepository.deleteCourseById(cid);
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
