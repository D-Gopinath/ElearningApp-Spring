package com.elearningapp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elearningapp.dto.Message;
import com.elearningapp.model.Course;
import com.elearningapp.model.CourseMenu;
import com.elearningapp.model.UserCourse;
import com.elearningapp.service.CourseService;
@RestController
public class CourseController {
		
	@Autowired
	CourseService courseService;
	
	@GetMapping("course/list")
	public ResponseEntity<?> showAll(){
		try {
			List<Course> courses = courseService.showCourses();
			return new ResponseEntity<>(courses,HttpStatus.OK);
		}
		catch(Exception e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("course/search/{cname}")
	public ResponseEntity<?> search(@PathVariable("cname") String cname){
		Course course = null;
		try {
			course = courseService.searchByName(cname);
			if(course!=null) {
				return new ResponseEntity<>(course,HttpStatus.OK);
			}
			else {
				throw new Exception("Course Not Available");
			}
			
		}
		catch(Exception e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("course/enroll/{cid}/{uid}")
	public ResponseEntity<?> enrollCourse(@PathVariable("cid") Integer cid, @PathVariable("uid") Integer uid){
		
		UserCourse uc = new UserCourse();
		uc.setUserId(uid);
		uc.setCourseId(cid);
		uc.setDate(LocalDate.now());
		
		try {
			courseService.enroll(uc);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("courses/enrolledlist/{uid}")
	public ResponseEntity<?> viewEnrolledCourses(@PathVariable("uid") Integer uid){
		try {
			List<Course> eCourses = courseService.enrolledCourses(uid);
			return new ResponseEntity<>(eCourses,HttpStatus.OK);
		}
		catch(Exception e){
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("courses/viewcourse/{cid}")
	public ResponseEntity<?> viewCourse(@PathVariable("cid") Integer cid){
		try {
			Course courseDetails = courseService.viewCourse(cid);
			return new ResponseEntity<>(courseDetails,HttpStatus.OK);
		}
		catch(Exception e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("courses/CourseCategory")
	public ResponseEntity<?> showCategory(){
		try {
			List<CourseMenu> courses = courseService.showCourseCategory();
			return new ResponseEntity<>(courses,HttpStatus.OK);
		}
		catch(Exception e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("courses/viewbytype")
	public ResponseEntity<?> viewByType(@RequestParam("cId") Integer cId){
		try {
			List<Course> courses = courseService.viewCoursesByType(cId);
			return new ResponseEntity<>(courses,HttpStatus.OK);
		}
		catch(Exception e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}
		
		
		
	}
	
}
