package com.example.template.boundary;

import static com.example.template.utils.MasterData.getUserDTO;
import static com.example.template.utils.MasterData.randomStringWithSize;
import static com.example.template.utils.TestUtils.boundaryTestFile;
import static com.example.template.utils.TestUtils.currentTest;
import static com.example.template.utils.TestUtils.testReport;
import static com.example.template.utils.TestUtils.yakshaAssert;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.template.dto.UserDTO;

@ExtendWith(SpringExtension.class)
public class BoundaryTest {

	private static Validator validator;

	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testNameNotNull() throws Exception {
		UserDTO userDTO = getUserDTO();
		userDTO.setName(null);
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testNameMinThree() throws Exception {
		UserDTO userDTO = getUserDTO();
		userDTO.setName(randomStringWithSize(2));
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testNameMaxFifty() throws Exception {
		UserDTO userDTO = getUserDTO();
		userDTO.setName(randomStringWithSize(51));
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testUserNameNotNull() throws Exception {
		UserDTO userDTO = getUserDTO();
		userDTO.setUsername(null);
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testUserNameMinThree() throws Exception {
		UserDTO userDTO = getUserDTO();
		userDTO.setUsername(randomStringWithSize(2));
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testUserNameMaxTwenty() throws Exception {
		UserDTO userDTO = getUserDTO();
		userDTO.setUsername(randomStringWithSize(21));
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	// test email
	@Test
	public void testEmailNotNull() throws Exception {
		UserDTO userDTO = getUserDTO();
		userDTO.setEmail(null);
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	// test password
	@Test
	public void testPasswordNotNull() throws Exception {
		UserDTO userDTO = getUserDTO();
		userDTO.setPassword(null);
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testPasswordMinEight() throws Exception {
		UserDTO userDTO = getUserDTO();
		userDTO.setPassword(randomStringWithSize(7));
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testPasswordMaxTwenty() throws Exception {
		UserDTO userDTO = getUserDTO();
		userDTO.setPassword(randomStringWithSize(21));
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testInterestMaxTwoHundred() throws Exception {
		UserDTO userDTO = getUserDTO();
		userDTO.setInterests(
				"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testGenderNotNull() throws Exception {
		UserDTO userDTO = getUserDTO();
		userDTO.setGender(null);
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testUserEmailValidFormat() throws Exception {
		UserDTO userDTO = getUserDTO();
		userDTO.setEmail("abc");
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
}
