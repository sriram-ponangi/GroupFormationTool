package com.group14.app.controllers;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;

import com.group14.app.services.CourseAdminService;


public class CourseAdminControllerTest {
	
	@Spy
	CourseAdminController courseAdminController;
	
	@Mock
	CourseAdminService courseAdminService;
	
	@Mock
	Model model;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}	
	
	@Test
	public void readUserFromCSVTest_UserHasAccess() {		
		when(model.addAttribute(Mockito.anyString(), Mockito.anyString())).thenReturn(model).thenReturn(model);		
		Mockito.doReturn(true).when(courseAdminController).hasAdminAccessToCourse(Mockito.anyString());		
		assertEquals("StudentEnrollmentFormWithCSV", courseAdminController.readUsersFromCSV("", model));
		
	}
	@Test
	public void readUserFromCSVTest_UserDoesNotHaveAccess() {		
		when(model.addAttribute(Mockito.anyString(), Mockito.anyString())).thenReturn(model).thenReturn(model);		
		Mockito.doReturn(false).when(courseAdminController).hasAdminAccessToCourse(Mockito.anyString());		
		assertEquals("StudentEnrollmentFormError", courseAdminController.readUsersFromCSV("", model));
		
	}
	@Test
	public void enrollStudentsFromCSV_UserDoesNotHaveAccess(){	
		String response = null;
		when(model.addAttribute(Mockito.anyString(), Mockito.anyString())).thenReturn(model).thenReturn(model);		
		Mockito.doReturn(false).when(courseAdminController).hasAdminAccessToCourse(Mockito.anyString());
		File f = new File("./src/test/java/com/group14/app/testResources/AppUsersCSV.csv");
		MockMultipartFile file;
		try {
			file = new MockMultipartFile(
			        "file",
			        "AppUsersCSV",
			        "application/text",
			        new FileInputStream(f));
			
			response = courseAdminController.enrollStudentsFromCSV(file, "", model);			
		} catch (IOException e) {			
			e.printStackTrace();
		}
		assertNotNull(response);
		assertEquals("StudentEnrollmentFormError", response );		
	}
	
	@Test
	public void enrollStudentsFromCSV_UserHasAccessButUploadedInvalidFile(){	
		String response = null;
		when(model.addAttribute(Mockito.anyString(), Mockito.anyString())).thenReturn(model).thenReturn(model);		
		Mockito.doReturn(false).when(courseAdminController).hasAdminAccessToCourse(Mockito.anyString());
		File f = new File("./src/test/java/com/group14/app/testResources/AppUsersCSV - Invalid.csv");
		MockMultipartFile file;
		try {
			file = new MockMultipartFile(
			        "file",
			        "AppUsersCSV",
			        "application/text",
			        new FileInputStream(f));
			
			response = courseAdminController.enrollStudentsFromCSV(file, "", model);			
		} catch (IOException e) {			
			e.printStackTrace();
		}
		assertNotNull(response);
		assertEquals("StudentEnrollmentFormError", response );
		
		
	}
}









