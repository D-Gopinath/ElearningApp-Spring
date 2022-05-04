package com.elearningapp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.elearningapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	
	User save(User user);
	
	List<User> findAll();
	 
	@Query("select u from com.elearningapp.model.User u where u.email=:userEmail")
	User findByEmail(@Param("userEmail") String userEmail);
	
	
	@Modifying
	@Query("delete from com.elearningapp.model.User u where u.id=:id")
	void deleteById(@Param("id") Integer id);
	
	@Transactional
	@Modifying
	@Query("update com.elearningapp.model.User u set u.password = :password where u.id=:id")
	void changePassword(@Param("id") Integer id, @Param("password") String password);

}
