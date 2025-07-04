package com.mydating.dating.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.mydating.dating.dao.UserDao;
import com.mydating.dating.dto.MatchingUser;
import com.mydating.dating.entity.User;
import com.mydating.dating.repository.UserRepository;
import com.mydating.dating.util.UserGender;
import com.mydating.dating.util.Usersorting;

@Service
public class UserService {
	@Autowired
	UserDao userDao;
	@Autowired
	private UserRepository userRepository;
	public ResponseEntity<?> saveUser(User user) {
		User saveUser = userDao.saveUser(user);
		return ResponseEntity.status(HttpStatus.OK).body(saveUser);
	}
	public ResponseEntity<?> findAllMaleUsers() {
		List<User> maleUsers = userDao.findAllMaleUsers();
		if(maleUsers.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no male users present");
		}	
		else {
			return ResponseEntity.status(HttpStatus.OK).body(maleUsers);
		}
	}
	public ResponseEntity<?> findAllFemaleUsers() {
		
		List<User> femaleUsers = userDao.findAllFemaleUsers();
		if(femaleUsers.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no female users present");
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(femaleUsers);
		}
	}
	public ResponseEntity<?> findBestMatch(int id, int top) {
		Optional<User> optional	= userDao.findUserById(id);	
		if(optional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid id unable to find...");
		}
		User  user = optional.get();
		List<User> users = null;
		if(user.getGender().equals(UserGender.MALE))
		{
			users = userDao.findAllFemaleUsers();
		}
		else {
			users = userDao.findAllMaleUsers();
		}
		
		for (User u : users) {
			System.out.println(u);
		}
		
		List<MatchingUser> matchingUsers  = new ArrayList<>();
		
		for(User u:users) {
			MatchingUser mu = new MatchingUser();
			mu.setId(u.getId());
			mu.setName(u.getName());
			mu.setEmail(u.getEmail());
			mu.setAge(u.getAge());
			mu.setInterests(u.getInterests());

		    mu.setGender(u.getGender());
			mu.setAgeDiff(Math.abs(user.getAge()-u.getAge()));
			List<String> intrests1 = user.getInterests();
			List<String> intrests2 = u.getInterests();
			int mic= 0;
			for(String s:intrests1) {
				if(intrests2.contains(s)) {
					mic++;
				}
			}
			mu.setMic(mic);
			matchingUsers.add(mu);
		}
		Comparator<MatchingUser> c = new Usersorting();
		Collections.sort(matchingUsers,c);
		List<MatchingUser> result = new ArrayList<>();
		for(MatchingUser mu : matchingUsers) {
			if(top ==0) {
				break;
			}
			else{
				result.add(mu);
				top--;
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
//FIND BY ID
	public ResponseEntity<?> findById(int id) {
	    Optional<User> user = userDao.findById(id);
	    if (user.isEmpty()) {
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	    } else {
	        return ResponseEntity.status(HttpStatus.OK).body(user);
	    }
	}
	public ResponseEntity<?> findByName(String name) {
		List<User> users=userDao.findByName(name);
		if(users.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(users);
		}
	}
	public ResponseEntity<?> findByEmail(String email) {
		List<User> users=userDao.findByEmail(email);
		if(users.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(users);
		}
	}
	public ResponseEntity<?> findByPhone(long phone) {
		List<User> users=userDao.findByPhone(phone);
		if(users.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(users);
		}
	}
	public ResponseEntity<?> findByAge(int age) {
		List<User> users=userDao.findByAge(age);
		if(users.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(users);
		}
	}
	public ResponseEntity<?> deleteUser(int id) {
	    Optional<User> user = userRepository.findById(id);
	    if (user.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with id: " + id);
	    }
	    userRepository.deleteById(id);
	    return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully with id: " + id);
	}
	
	public ResponseEntity<?> searchBYName(String letters) {
		List<User> users = userDao.searchBYName("%"+letters+"%");
		if(users.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO user found with letters : "+letters);
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(users);
		}
	}
	//searchByEmail in single letter
	public ResponseEntity<?> searchByEmail(String letter) {
		List<User> users = userDao.searchByEmail("%"+letter+"%");
		if(users.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO user found with letters : "+letter);
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(users);
		}
		
	}
	
}
