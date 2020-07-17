package com.group14.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import com.group14.app.models.AppUser;
import com.group14.app.repositories.IStudentEnrollmentRepository;

public class StudentEnrollmentServiceTest {
	@InjectMocks
	StudentEnrollmentService studentEnrollmentService;

	@Mock
	private IStudentEnrollmentRepository studentEnrollmentRepository;
	@Mock
	private IParseUploadedFile<AppUser> parseCSV;
	@Mock
	private IEmailSenderService emailSenderService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void enrollStudentsToCourseTest_basic() throws SQLException {
		List<AppUser> expectedResult = new ArrayList<>();
		List<AppUser> invalidUsersList = null;

		// Using Mockito.any() because the focus of this Class is to test the service
		// class not the repository.
		when(parseCSV.parseFile(Mockito.any())).thenReturn(getValidMockData());
		when(studentEnrollmentRepository.enrollStudentToCourse(Mockito.any(), Mockito.anyString()))
				.thenReturn(new int[] { 1, 1, 1 });

		File f = new File("./src/test/java/com/group14/app/testResources/AppUsersCSV.csv");

		MockMultipartFile file;
		try {
			file = new MockMultipartFile("file", "AppUsersCSV", "application/text", new FileInputStream(f));
			invalidUsersList = this.studentEnrollmentService.enrollStudentsToCourseFromFile(file, "CSCI1001");
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertNotEquals(null, invalidUsersList);
		assertEquals(expectedResult.size(), invalidUsersList.size());
	}

	@Test
	public void enrollStudentsToCourseTest_invaliData() throws SQLException {
		List<AppUser> invalidUsersList = null;
		// Using Mockito.any() because the focus of this Class is to test the service
		// class not the repository.
		when(parseCSV.parseFile(Mockito.any())).thenReturn(getInvalidMockData());
		when(studentEnrollmentRepository.enrollStudentToCourse(Mockito.any(), Mockito.anyString()))
				.thenReturn(new int[] { 1, 1, 1 });

		File f = new File("./src/test/java/com/group14/app/testResources/AppUsersCSV - Invalid Data.csv");

		MockMultipartFile file;
		try {
			file = new MockMultipartFile("file", "AppUsersCSV", "application/text", new FileInputStream(f));

			invalidUsersList = this.studentEnrollmentService.enrollStudentsToCourseFromFile(file, "CSCI1001");
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertNotEquals(null, invalidUsersList);
		assertEquals(1, invalidUsersList.size());
		assertEquals("B00100001", invalidUsersList.get(0).getUserId());
		assertEquals("User", invalidUsersList.get(0).getFirstName());
		assertEquals("One", invalidUsersList.get(0).getLastName());
		assertEquals("sriram.ponangi@gmail.com", invalidUsersList.get(0).getEmail());
	}

	private List<AppUser> getValidMockData() {
		List<AppUser> usersList = new ArrayList<>();
		AppUser invalidUser = new AppUser("B00100001", "User", "One", "sriram.ponangi@dal.ca");
		usersList.add(invalidUser);
		return usersList;
	}

	private List<AppUser> getInvalidMockData() {
		List<AppUser> usersList = new ArrayList<>();
		AppUser invalidUser = new AppUser("B00100001", "User", "One", "sriram.ponangi@gmail.com");
		usersList.add(invalidUser);
		return usersList;
	}
}
