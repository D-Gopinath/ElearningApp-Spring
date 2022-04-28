package com.app.elearning.ELearningAPI.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.elearning.ELearningAPI.model.Course;
import com.app.elearning.ELearningAPI.model.UserCourse;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
	
	List<Course> findAll();
	
	@Query("select new com.app.elearning.ELearningAPI.model.Course(c.cName,c.tutor,c.duration,c.imageurl) from com.app.elearning.ELearningAPI.model.Course c where c.cName=:cname")
	Course findByCourseName(@Param("cname") String cname);

	 void save(UserCourse uc);
	
	
}
