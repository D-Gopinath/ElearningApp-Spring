package com.elearningapp.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.elearningapp.model.Course;
import com.elearningapp.model.User;

@Repository
public interface AdminRepository extends JpaRepository<User, Integer> {
	
	//For Admin Login
	@Query("select u from com.elearningapp.model.User u where u.email=:email and role='admin' ")
	User findByEmail(@Param("email") String email);
	
	//To view all the user
	List<User> findAll();
	
	//To delete the user
	@Modifying
	@Query("delete from com.elearningapp.model.User u where u.id=:id")
	void deleteById(@Param("id") Integer id);
	
	//To insert course
	Course save(Course course);
	
	//To delete course
	@Transactional
	@Modifying
	@Query("delete from com.elearningapp.model.Course c where c.id=:cid")
	void deleteCourseById(@Param("cid") Integer cid);

}
