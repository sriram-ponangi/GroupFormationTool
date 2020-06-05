package com.group14.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mock.web.MockMultipartFile;

import com.group14.app.models.AppUser;
import com.group14.app.repositories.AppUserRepository;

public class CourseAdminServiceTest {

	@InjectMocks
	CourseAdminService courseAdminService;
	
	@Mock
	AppUserRepository appUserRepositoryMock;	
	
	@Mock
	private JavaMailSender javaMailSender;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void enrollStudentsToCourseTest_basic() {
		List<AppUser> expectedResult = new ArrayList<>();
		List<AppUser> invalidUsersList = null ;
		
		when(appUserRepositoryMock.enrollStudentToCourse(Mockito.any(), Mockito.anyString()))
		.thenReturn(new int[] {1,1,1});
		
		File f = new File("./src/test/java/com/group14/app/testResources/AppUsersCSV.csv");
		
		MockMultipartFile file;
		try {
			file = new MockMultipartFile(
			        "file",
			        "AppUsersCSV",
			        "application/text",
			        new FileInputStream(f));
			
			invalidUsersList = this.courseAdminService.enrollStudentsToCourse(file , "CSCI1001");
			System.out.println(invalidUsersList);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		assertNotEquals(null, invalidUsersList);
		assertEquals(expectedResult.size(), invalidUsersList.size());
	}
	
	@Test
	public void enrollStudentsToCourseTest_invaliData() {		
		List<AppUser> invalidUsersList = null ;
		
		when(appUserRepositoryMock.enrollStudentToCourse(Mockito.any(), Mockito.anyString()))
		.thenReturn(new int[] {1,1,1});
		
		File f = new File("./src/test/java/com/group14/app/testResources/AppUsersCSV - Invalid Data.csv");
		
		MockMultipartFile file;
		try {
			file = new MockMultipartFile(
			        "file",
			        "AppUsersCSV",
			        "application/text",
			        new FileInputStream(f));
			
			invalidUsersList = this.courseAdminService.enrollStudentsToCourse(file , "CSCI1001");			
		} catch (IOException e) {			
			e.printStackTrace();
		}
		assertNotEquals(null, invalidUsersList);
		assertEquals(1, invalidUsersList.size());
		assertEquals("B00100001",invalidUsersList.get(0).getUserId());
		assertEquals("User",invalidUsersList.get(0).getFirstName());
		assertEquals("One",invalidUsersList.get(0).getLastName());
		assertEquals("sriram.ponangi@gmail.com",invalidUsersList.get(0).getEmail());
	}
	
	
}
