package com.example.template.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.template.dto.UserDTO;
import com.example.template.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<UserDTO> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	public UserDTO getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}

	@PostMapping
	public UserDTO createUser(@Valid @RequestBody UserDTO userDTO) {
		return userService.createUser(userDTO);
	}

	@PutMapping("/{id}")
	public UserDTO updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
		userDTO.setId(id);
		return userService.updateUser(id, userDTO);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}

	@GetMapping("/username/{username}")
	public UserDTO getUserByUsername(@PathVariable String username) {
		return userService.getUserByUsername(username);
	}

	@GetMapping("/email/{email}")
	public UserDTO getUserByEmail(@PathVariable String email) {
		return userService.getUserByEmail(email);
	}
}
