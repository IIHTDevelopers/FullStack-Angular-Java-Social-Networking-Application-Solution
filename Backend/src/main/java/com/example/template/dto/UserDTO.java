package com.example.template.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.template.entity.Gender;

public class UserDTO {

	private Long id;

	@NotNull
	@Size(min = 3, max = 50)
	private String name;

	@NotNull
	@Size(min = 3, max = 20)
	private String username;

	@NotNull
	@Size(min = 0, max = 100)
	@Email
	private String email;

	@NotNull
	@Size(min = 8, max = 20)
	private String password;

	private String profilePicture;

	@Size(min = 0, max = 200)
	private String bio;

	@Size(min = 0, max = 100)
	private String location;

	private LocalDate dateOfBirth;

	@NotNull
	private Gender gender;

	@Size(min = 0, max = 200)
	private String interests;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDTO(Long id, String name, String username, String email, String password, String profilePicture,
			String bio, String location, LocalDate dateOfBirth, Gender gender, String interests) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.profilePicture = profilePicture;
		this.bio = bio;
		this.location = location;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.interests = interests;
	}

	public UserDTO() {
		super();
	}

}
