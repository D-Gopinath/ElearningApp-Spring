package com.elearningapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RestController;
import com.elearningapp.dao.CourseRepository;
import com.elearningapp.model.Course;
import com.elearningapp.model.CourseMenu;
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
	
	public List<CourseMenu> showCourseCategory() throws Exception{
		try {
			List<CourseMenu> courses = courseRepository.courseCategory();
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
			String query = "SELECT courses_list.course_name as cName,courses_list.tutor,courses_list.duration,courses_list.video_url as url,courses_list.description FROM courses_list INNER JOIN user_course_enrollment ON courses_list.id=user_course_enrollment.course_id WHERE user_id='"+userId+"' ";
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
	
	public List<Course> viewCoursesByType(Integer cId) throws Exception{
		try {
			List<Course> courses = courseRepository.viewCourseByType(cId);
			return courses;
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
