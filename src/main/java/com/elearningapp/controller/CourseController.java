package com.elearningapp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearningapp.model.Course;
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
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("course/search")
	public ResponseEntity<?> search(String cname){
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
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("course/enroll/{cid}/{uid}")
	public ResponseEntity<String> enrollCourse(@PathVariable("cid") Integer cid, @PathVariable("uid") Integer uid){
		
		UserCourse uc = new UserCourse();
		uc.setUserId(uid);
		uc.setCourseId(cid);
		uc.setDate(LocalDate.now());
		
		try {
			courseService.enroll(uc);
			return new ResponseEntity<String>("Enrolled Successfully",HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("courses/enrolledlist/{uid}")
	public ResponseEntity<?> viewEnrolledCourses(@PathVariable("uid") Integer uid){
		try {
			List<Course> eCourses = courseService.enrolledCourses(uid);
			return new ResponseEntity<>(eCourses,HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("courses/viewcourse")
	public ResponseEntity<?> viewCourse(Integer cid){
		try {
			Course courseDetails = courseService.viewCourse(cid);
			return new ResponseEntity<>(courseDetails,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
}
