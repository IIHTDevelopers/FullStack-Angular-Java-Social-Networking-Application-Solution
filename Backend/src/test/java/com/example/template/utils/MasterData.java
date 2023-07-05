package com.example.template.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.template.dto.UserDTO;
import com.example.template.entity.Gender;
import com.example.template.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class MasterData {

	public static UserDTO getUserDTO() {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(1L);
		userDTO.setName("sidd");
		userDTO.setDateOfBirth(LocalDate.now());
		userDTO.setUsername("siddd");
		userDTO.setEmail("sidd@gmail.com");
		userDTO.setPassword("password");
		userDTO.setBio("develoer");
		userDTO.setLocation("Housing board");
		userDTO.setInterests("cricket");
		userDTO.setGender(Gender.MALE);
		return userDTO;

	}

	public static List<UserDTO> getUserDTOList() {
		List<UserDTO> userDTOs = new ArrayList<>();

		UserDTO userDTO = new UserDTO();
		userDTO.setId(1L);
		userDTO.setName("sidd");
		userDTO.setDateOfBirth(LocalDate.now());
		userDTO.setUsername("siddd");
		userDTO.setEmail("sidd@gmail.com");
		userDTO.setPassword("password");
		userDTO.setBio("develoer");
		userDTO.setLocation("Housing board");
		userDTO.setInterests("cricket");
		userDTO.setGender(Gender.MALE);
		userDTOs.add(userDTO);

		userDTO = new UserDTO();
		userDTO.setId(2L);
		userDTO.setName("abhi");
		userDTO.setDateOfBirth(LocalDate.now());
		userDTO.setUsername("abhi");
		userDTO.setEmail("abhi@gmail.com");
		userDTO.setPassword("password");
		userDTO.setBio("develoer");
		userDTO.setLocation("cricket stadium ");
		userDTO.setInterests("cricket");
		userDTO.setGender(Gender.MALE);
		userDTOs.add(userDTO);

		return userDTOs;
	}

	public static User getUser() {
		User user = new User();
		user.setId(1L);
		user.setName("sidd");
		user.setDateOfBirth(LocalDate.now());
		user.setUsername("siddd");
		user.setEmail("sidd@gmail.com");
		user.setPassword("password");
		user.setBio("develoer");
		user.setLocation("Housing board");
		user.setInterests("cricket");
		return user;
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			final String jsonContent = mapper.writeValueAsString(obj);

			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String randomStringWithSize(int size) {
		String s = "";
		for (int i = 0; i < size; i++) {
			s.concat("A");
		}
		return s;
	}
}
