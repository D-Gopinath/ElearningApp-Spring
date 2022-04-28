package com.app.elearning.ELearningAPI.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.elearning.ELearningAPI.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	
	User save(User user);
	
	List<User> findAll();
	 
	@Query("select new com.app.elearning.ELearningAPI.model.User(u.name,u.email,u.password) from com.app.elearning.ELearningAPI.model.User u where u.email=:userEmail and u.password=:userPassword")
	User findByEmailAndPassword(@Param("userEmail") String userEmail, @Param("userPassword") String userPassword);
	
	Optional<User> findById(Integer id);
	
	@Modifying
	@Query("delete from User u where u.id=:id")
	void deleteById(@Param("id") Integer id);
	
	@Transactional
	@Modifying
	@Query("update User u set u.password = :password where u.id=:id")
	void changePassword(@Param("id") Integer id, @Param("password") String password);

}
