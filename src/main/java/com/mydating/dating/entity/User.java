package com.mydating.dating.entity;
import java.util.List;

import com.mydating.dating.util.UserGender;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private long phone;
	private int age;
	@Enumerated(EnumType.STRING)
	private UserGender gender;
	
	@ElementCollection
//if we want to make one to many if both are entity classes if one class also not an entity we should use Element collection 
	private List<String> interests;
}