package com.mydating.dating.dao;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mydating.dating.entity.User;
import com.mydating.dating.repository.UserRepository;
import com.mydating.dating.util.UserGender;

@Repository
public class UserDao {
	@Autowired
	
	UserRepository userRepository;

	public User saveUser(User user) {
		return	userRepository.save(user);	
	}

	public List<User> findAllMaleUsers() {
		return userRepository.findByGender(UserGender.MALE);
	}

	public List<User> findAllFemaleUsers() {
		return	userRepository.findByGender(UserGender.FEMALE);
	}

	public Optional<User> findUserById(int id) {
		return userRepository.findById(id);
	}

	public Optional<User> findById(int id) {
		return userRepository.findById(id);
	}
	public List<User> findByName(String name) {
		return userRepository.findByName(name);
	}
	public List<User> findByEmail(String email) {    //localhost:8080/users/email?email=kohli@gmail.com
		return userRepository.findByEmailIgnoreCase(email.trim());	
		}
	public List<User> findByPhone(long phone) {
		return userRepository.findByPhone(phone);
	}
	public List<User> findByAge(int age) {
		return userRepository.findByAge(age);
	}

	public List<User> searchBYName(String letters) {
		return userRepository.searchBYName(letters);
	}

	public List<User> searchByEmail(String letter) {
		return userRepository.searchByEmail(letter);
	}

	
}