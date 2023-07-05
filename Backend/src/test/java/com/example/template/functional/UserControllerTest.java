package com.example.template.functional;

import static com.example.template.utils.MasterData.getUserDTO;
import static com.example.template.utils.MasterData.getUserDTOList;
import static com.example.template.utils.TestUtils.businessTestFile;
import static com.example.template.utils.TestUtils.currentTest;
import static com.example.template.utils.TestUtils.testReport;
import static com.example.template.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.template.controller.UserController;
import com.example.template.dto.UserDTO;
import com.example.template.service.UserService;
import com.example.template.utils.MasterData;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateUser() throws Exception {
		UserDTO savedUserDTO = getUserDTO();
		when(userService.createUser(any())).thenReturn(savedUserDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users")
				.content(MasterData.asJsonString(savedUserDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedUserDTO)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testCreateUserIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		UserDTO savedUserDto = getUserDTO();
		when(this.userService.createUser(any())).then(new Answer<UserDTO>() {

			@Override
			public UserDTO answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return savedUserDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users")
				.content(MasterData.asJsonString(savedUserDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
	}

	@Test
	public void testGetUserById() throws Exception {
		UserDTO userDTO = getUserDTO();
		when(this.userService.getUserById(userDTO.getId())).thenReturn(userDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/" + userDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(userDTO)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetUserByIdIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		UserDTO userDTO = getUserDTO();
		when(this.userService.getUserById(userDTO.getId())).then(new Answer<UserDTO>() {

			@Override
			public UserDTO answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return userDTO;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/" + userDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testGetAllUsers() throws Exception {
		List<UserDTO> userDTOS = getUserDTOList();

		when(this.userService.getAllUsers()).thenReturn(userDTOS);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(userDTOS)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetAllUsersIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<UserDTO> userDTOS = getUserDTOList();

		when(this.userService.getAllUsers()).then(new Answer<List<UserDTO>>() {

			@Override
			public List<UserDTO> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return userDTOS;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testUpdateUser() throws Exception {
		UserDTO updateUserDTO = getUserDTO();

		when(this.userService.updateUser(eq(updateUserDTO.getId()), any())).thenReturn(updateUserDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/" + updateUserDTO.getId())
				.content(MasterData.asJsonString(updateUserDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(updateUserDTO))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testUpdateUserIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		UserDTO updateUserDTO = getUserDTO();

		when(this.userService.updateUser(eq(updateUserDTO.getId()), any())).then(new Answer<UserDTO>() {

			@Override
			public UserDTO answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return updateUserDTO;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/" + updateUserDTO.getId())
				.content(MasterData.asJsonString(updateUserDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testDeleteUser() throws Exception {
		UserDTO userDTO = getUserDTO();
		when(this.userService.deleteUser(userDTO.getId())).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/users/" + userDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), (result.getResponse().getContentAsString().contentEquals("") ? "true" : "false"),
				businessTestFile);

	}

	@Test
	public void testDeleteUserIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		UserDTO userDTO = getUserDTO();
		when(this.userService.deleteUser(userDTO.getId())).then(new Answer<Boolean>() {

			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return true;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/users/" + userDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testGetUserByUsername() throws Exception {
		UserDTO userDTO = getUserDTO();
		when(this.userService.getUserByUsername(userDTO.getUsername())).thenReturn(userDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/username/" + userDTO.getUsername())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(userDTO)) ? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testGetUserByUserNameIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		UserDTO userDTO = getUserDTO();

		when(this.userService.getUserByUsername(userDTO.getUsername())).then(new Answer<UserDTO>() {

			@Override
			public UserDTO answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return userDTO;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/username/" + userDTO.getUsername())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
	}

	@Test
	public void testGetUserByEmail() throws Exception {
		UserDTO userDTO = getUserDTO();
		when(this.userService.getUserByEmail(userDTO.getEmail())).thenReturn(userDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/email/" + userDTO.getEmail())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(userDTO)) ? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testGetUserByEmailIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		UserDTO userDTO = getUserDTO();

		when(this.userService.getUserByEmail(userDTO.getEmail())).then(new Answer<UserDTO>() {

			@Override
			public UserDTO answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return userDTO;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/email/" + userDTO.getEmail())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
	}

}
