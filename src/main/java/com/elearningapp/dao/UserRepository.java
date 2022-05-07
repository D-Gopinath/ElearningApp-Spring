package com.elearningapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elearningapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	
	//To register user
	User save(User user);
	
	//For Login
	@Query("select u from com.elearningapp.model.User u where u.email=:userEmail")
	User findByEmail(@Param("userEmail") String userEmail);
	
	//To change password
	@Transactional
	@Modifying
	@Query("update com.elearningapp.model.User u set u.password = :password where u.id=:id")
	void changePassword(@Param("id") Integer id, @Param("password") String password);

}
