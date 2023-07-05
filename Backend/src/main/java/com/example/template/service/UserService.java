package com.example.template.service;

import java.util.List;

import com.example.template.dto.UserDTO;

public interface UserService {

	List<UserDTO> getAllUsers();

	UserDTO getUserById(Long id);

	UserDTO createUser(UserDTO userDTO);

	UserDTO updateUser(Long id, UserDTO userDTO);

	boolean deleteUser(Long id);

	UserDTO getUserByUsername(String username);

	UserDTO getUserByEmail(String email);
}
