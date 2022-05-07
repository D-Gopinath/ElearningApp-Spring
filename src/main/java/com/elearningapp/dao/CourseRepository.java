package com.elearningapp.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elearningapp.model.Course;
import com.elearningapp.model.UserCourse;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
	@Query("select new com.elearningapp.model.Course(c.cName,c.tutor,c.duration,c.imageurl) from com.elearningapp.model.Course c where c.id=:cid")
	Course viewCourse(Integer cid);
	
	@Query("select new com.elearningapp.model.Course(c.cName,c.tutor,c.duration,c.imageurl) from com.elearningapp.model.Course c where c.cName=:cname")
	Course findByCourseName(@Param("cname") String cname);

	 void save(UserCourse uc);
	 
	 @Query("select new com.elearningapp.model.Course(c.cName,c.tutor,c.duration,c.imageurl) from com.elearningapp.model.Course c")
	 List<Course> viewAllCourse();
}
