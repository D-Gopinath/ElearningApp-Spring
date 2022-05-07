package com.elearningapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RestController;
import com.elearningapp.dao.CourseRepository;
import com.elearningapp.model.Course;
import com.elearningapp.model.UserCourse;

@RestController
public class CourseService {
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public List<Course> showCourses() throws Exception{
		try {
			List<Course> courses = courseRepository.viewAllCourse();
			return courses;
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public Course searchByName(String name) throws Exception{
		try {
			Course course = courseRepository.findByCourseName(name);
			return course;
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void enroll(UserCourse uc) throws Exception{
		
		try{
			courseRepository.save(uc);
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}
	
	public List<Course> enrolledCourses(Integer userId)throws Exception{
		try {
			String query = "SELECT course.course_name as cName,course.tutor,course.duration,course.url FROM course INNER JOIN user_course_enrollment ON course.c_id=user_course_enrollment.course_id WHERE user_id='"+userId+"' ";
			return namedParameterJdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(Course.class));
		}
		catch(Exception e){
			throw new Exception(e.getMessage());
		}
		
	}
 
	public Course viewCourse(Integer courseId)throws Exception{
		try {
			Course course = courseRepository.viewCourse(courseId);
			return course;
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
