package com.mydating.dating.dto;

import java.util.List;

import com.mydating.dating.util.UserGender;

import lombok.Data;

//import jakarta.persistence.ElementCollection;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
@Data
public class MatchingUser {
	private int id;
	private String name;
	private String email;
	private long phone;
	private int age;
//	@Enumerated(EnumType.STRING)
	private UserGender gender;
	
//	@ElementCollection
//if we want to make one to many if both are entity classes if one class also not an entity we should use Element collection 
	private List<String> interests;
	private int ageDiff;
	private int mic;
}