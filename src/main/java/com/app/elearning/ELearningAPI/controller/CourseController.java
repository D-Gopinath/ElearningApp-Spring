package com.app.elearning.ELearningAPI.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.elearning.ELearningAPI.dao.CourseRepository;
import com.app.elearning.ELearningAPI.model.Course;
import com.app.elearning.ELearningAPI.model.UserCourse;
@RestController
public class CourseController {
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
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
		uc.setUserId(uid);
		uc.setCourseId(cid);
		uc.setDate(LocalDate.now());
		
		try {
			courseRepository.save(uc);
			return "Enrolled Successfully";
		}
		catch(Exception e) {
			return e.getMessage();
		}
		
	}
	
	@GetMapping("courses/enrolledlist/{uid}")
	public List<Course> viewEnrolledCourses(@PathVariable("uid") Integer uid){
		
		String query = "SELECT course.course_name as cName,course.tutor,course.duration,course.url FROM course INNER JOIN user_course_enrollment ON course.c_id=user_course_enrollment.course_id WHERE user_id='"+uid+"' ";
		return namedParameterJdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(Course.class));
		
	}
	
}
