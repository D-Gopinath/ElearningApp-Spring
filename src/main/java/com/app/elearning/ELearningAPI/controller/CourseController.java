package com.app.elearning.ELearningAPI.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.elearning.ELearningAPI.dao.CourseRepository;
import com.app.elearning.ELearningAPI.model.Course;
import com.app.elearning.ELearningAPI.model.User;
import com.app.elearning.ELearningAPI.model.UserCourse;
@RestController
public class CourseController {
	
	@Autowired
	CourseRepository courseRepository;
	
	@GetMapping("course/list")
	public List<Course> findAll(){
		List<Course> courseList = courseRepository.findAll();
		return courseList;
	}
	
	@GetMapping("course/search")
	public Course findByCourseName(String cname){
		Course course = null;
		try {
			course = courseRepository.findByCourseName(cname);
			if(course!=null) {
				return course;
			}
			else {
				throw new Exception("Course Not Available");
			}
			
		}
		catch(Exception e) {
			return null;
		}
	}
	
	@PostMapping("course/enroll/{cid}/{uid}")
	public String enrollCourse(@PathVariable("cid") Integer cid, @PathVariable("uid") Integer uid){
		
		UserCourse uc = new UserCourse();
		
		User user = new User();
		user.setId(uid);
		
		Course course = new Course();
		course.setId(cid);
		
		uc.setUser(user);
		uc.setCourse(course);
		uc.setDate(LocalDate.now());
		
		try {
			courseRepository.save(uc);
			return "Enrolled Successfully";
		}
		catch(Exception e) {
			return e.getMessage();
		}
		
	}
	
	

}
