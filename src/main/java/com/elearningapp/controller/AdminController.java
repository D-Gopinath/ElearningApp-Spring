package com.elearningapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.elearningapp.model.Course;
import com.elearningapp.model.User;
import com.elearningapp.service.AdminService;

@RestController
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@PostMapping("admin/login")
	public ResponseEntity<?> login(@RequestBody User user)
	{
		User res;
		try {
			res=adminService.adminLogin(user.getEmail(),user.getPassword());
			return new ResponseEntity<>(res,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("admin/userslist")
	public ResponseEntity<?> viewUsers() {
		try {
			List<User> userList = adminService.showUsers();
			return new ResponseEntity<>(userList,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}

	@DeleteMapping("admin/deleteuser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id) {
		try {
		adminService.removeUser(id);
		System.out.println("User Deleted");
		return new ResponseEntity<String>("User deleted",HttpStatus.OK);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>("Unable to delete User",HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("admin/deletecourse/{cid}")
	public ResponseEntity<String> deleteCourse(@PathVariable("cid") Integer cid){
		try {
			adminService.removeCourse(cid);
			return new ResponseEntity<String>("Course deleted",HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>("Unable to delete course",HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("admin/addcourse")
	public ResponseEntity<String> register(@RequestBody Course course) {
		
		try {
			adminService.insertCourse(course);
			return new ResponseEntity<String>("Course Added Successfully",HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

}
