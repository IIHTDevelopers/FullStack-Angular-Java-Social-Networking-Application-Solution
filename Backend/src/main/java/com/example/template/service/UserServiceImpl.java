package com.example.template.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.template.dto.UserDTO;
import com.example.template.entity.User;
import com.example.template.exception.ResourceNotFoundException;
import com.example.template.exception.UserNameAlreadyExistsException;
import com.example.template.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public UserDTO getUserById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
		return convertToDTO(user);
	}

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		validateUniqueUsername(userDTO);
		User user = convertToEntity(userDTO);
		User savedUser = userRepository.save(user);
		return convertToDTO(savedUser);
	}

	@Override
	public UserDTO updateUser(Long id, UserDTO userDTO) {
		validateUniqueUsername(userDTO);
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

		updateEntity(user, userDTO);
		User updatedUser = userRepository.save(user);
		return convertToDTO(updatedUser);
	}

	@Override
	public boolean deleteUser(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
		userRepository.delete(user);
		return true;
	}

	@Override
	public UserDTO getUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new ResourceNotFoundException("User not found with username: " + username);
		}
		return convertToDTO(user);
	}

	@Override
	public UserDTO getUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new ResourceNotFoundException("User not found with email: " + email);
		}
		return convertToDTO(user);
	}

	private UserDTO convertToDTO(User user) {
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(user, userDTO);
		return userDTO;
	}

	private User convertToEntity(UserDTO userDTO) {
		User user = new User();
		BeanUtils.copyProperties(userDTO, user);
		return user;
	}

	private void updateEntity(User user, UserDTO userDTO) {
		BeanUtils.copyProperties(userDTO, user);
	}

	private void validateUniqueUsername(UserDTO userDTO) {
		User user = userRepository.findByUsername(userDTO.getUsername());
		if (user != null && user.getId() != userDTO.getId()) {
			throw new UserNameAlreadyExistsException("User already exists with this username : " + user.getUsername());
		}
	}

}
