package com.bezkoder.spring.datajpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.datajpa.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	List<User> findByUsernameContaining(String username);
	

	Optional<User> findByusernameAndPassword(String username, String password);


	//Optional<User> findByuserId(Integer userId);
	
}
