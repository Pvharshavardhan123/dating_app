package com.mydating.dating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mydating.dating.entity.User;
import com.mydating.dating.util.UserGender;


public interface UserRepository extends JpaRepository<User, Integer>{

	// In UserRepository.java
	
	List<User> findByGender(UserGender male);
	
	List<User> findByName(String name);

	List<User> findByEmailIgnoreCase(String email);

	List<User> findByPhone(long phone);

	List<User> findByAge(int age);


		
}