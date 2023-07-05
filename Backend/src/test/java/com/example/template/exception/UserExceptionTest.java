package com.example.template.exception;

import static com.example.template.utils.MasterData.getUser;
import static com.example.template.utils.MasterData.getUserDTO;
import static com.example.template.utils.MasterData.randomStringWithSize;
import static com.example.template.utils.TestUtils.currentTest;
import static com.example.template.utils.TestUtils.exceptionTestFile;
import static com.example.template.utils.TestUtils.testReport;
import static com.example.template.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.template.controller.UserController;
import com.example.template.dto.UserDTO;
import com.example.template.entity.User;
import com.example.template.service.UserService;
import com.example.template.utils.MasterData;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserExceptionTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	UserService userService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateUserInvalidDataException() throws Exception {
		UserDTO userDTO = getUserDTO();
		userDTO.setName(null);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users").content(MasterData.asJsonString(userDTO))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testUpdateUserInvalidDataException() throws Exception {
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(randomStringWithSize(21));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/" + userDTO.getId())
				.content(MasterData.asJsonString(userDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testGetUserByIdResourceNotFoundException() throws Exception {
		UserDTO userDTO = getUserDTO();
		GlobalExceptionHandler.ErrorResponse exResponse = new GlobalExceptionHandler.ErrorResponse(
				HttpStatus.BAD_REQUEST.value(), "User not found with id: " + userDTO.getId());

		when(this.userService.getUserById(userDTO.getId()))
				.thenThrow(new ResourceNotFoundException("User not found with id: " + userDTO.getId()));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/" + userDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testGetUserByUserNameResourceNotFoundException() throws Exception {
		UserDTO userDTO = getUserDTO();
		GlobalExceptionHandler.ErrorResponse exResponse = new GlobalExceptionHandler.ErrorResponse(
				HttpStatus.BAD_REQUEST.value(), "User not found with username: " + userDTO.getUsername());

		when(this.userService.getUserByUsername(userDTO.getUsername()))
				.thenThrow(new ResourceNotFoundException("User not found with username: " + userDTO.getUsername()));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/username/" + userDTO.getUsername())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testGetUserByEmailResourceNotFoundException() throws Exception {
		UserDTO userDTO = getUserDTO();
		GlobalExceptionHandler.ErrorResponse exResponse = new GlobalExceptionHandler.ErrorResponse(
				HttpStatus.BAD_REQUEST.value(), "User not found with email: " + userDTO.getEmail());

		when(this.userService.getUserByEmail(userDTO.getEmail()))
				.thenThrow(new ResourceNotFoundException("User not found with email: " + userDTO.getEmail()));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/email/" + userDTO.getEmail())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testUpdateUserByIdResourceNotFoundException() throws Exception {
		UserDTO userDTO = getUserDTO();
		GlobalExceptionHandler.ErrorResponse exResponse = new GlobalExceptionHandler.ErrorResponse(
				HttpStatus.BAD_REQUEST.value(), "User not found with id: " + userDTO.getId());

		when(this.userService.updateUser(eq(userDTO.getId()), any()))
				.thenThrow(new ResourceNotFoundException("User not found with id: " + userDTO.getId()));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/" + userDTO.getId())
				.content(MasterData.asJsonString(userDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testDeleteUserByIdResourceNotFoundException() throws Exception {
		UserDTO userDTO = getUserDTO();
		GlobalExceptionHandler.ErrorResponse exResponse = new GlobalExceptionHandler.ErrorResponse(
				HttpStatus.BAD_REQUEST.value(), "User not found with id: " + userDTO.getId());
		when(this.userService.deleteUser(userDTO.getId()))
				.thenThrow(new ResourceNotFoundException("User not found with id: " + userDTO.getId()));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/users/" + userDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testCreateUserWithUserNameAlreadyExistsException() throws Exception {
		User user = getUser();
		UserDTO userDTO = getUserDTO();
		userDTO.setUsername(user.getUsername());
		GlobalExceptionHandler.ErrorResponse exResponse = new GlobalExceptionHandler.ErrorResponse(
				HttpStatus.BAD_REQUEST.value(), "User already exists with this username : " + user.getUsername());
		when(userService.createUser(any())).thenThrow(
				new UserNameAlreadyExistsException("User already exists with this username : " + user.getUsername()));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users").content(MasterData.asJsonString(userDTO))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testUpdateUserWithUserNameAlreadyExistsException() throws Exception {
		User user = getUser();
		UserDTO userDTO = getUserDTO();
		GlobalExceptionHandler.ErrorResponse exResponse = new GlobalExceptionHandler.ErrorResponse(
				HttpStatus.BAD_REQUEST.value(), "User already exists with this username : " + user.getUsername());

		when(this.userService.updateUser(eq(userDTO.getId()), any())).thenThrow(
				new UserNameAlreadyExistsException("User already exists with this username : " + user.getUsername()));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/" + userDTO.getId())
				.content(MasterData.asJsonString(userDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}
}
