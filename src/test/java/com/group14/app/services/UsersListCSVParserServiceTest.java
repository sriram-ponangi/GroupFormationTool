package com.group14.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import com.group14.app.models.AppUser;

public class UsersListCSVParserServiceTest {

	@InjectMocks
	private UsersListCSVParserService usersListCSVParserService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void parseFileTest_testingWithValidFile() {
		File f = new File("./src/test/java/com/group14/app/testResources/AppUsersCSV.csv");
		List<AppUser> usersList = null;
		MockMultipartFile file;
		try {
			file = new MockMultipartFile("file", "AppUsersCSV", "application/text", new FileInputStream(f));
			usersList = usersListCSVParserService.parseFile(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertNotNull(usersList);
		assertEquals(8, usersList.size());
		assertEquals("B00110001", usersList.get(0).getUserId());
		assertEquals("User", usersList.get(0).getFirstName());
		assertEquals("One", usersList.get(0).getLastName());
		assertEquals("sriram.ponangi@dal.ca", usersList.get(0).getEmail());
		assertEquals(true, usersList.get(0).getPassword().length() > 0);
		assertEquals(1, usersList.get(0).getEnabled());

	}

	@Test
	public void parseFileTest_testingWithInValidFile() {
		File f = new File("./src/test/java/com/group14/app/testResources/AppUsersCSV - Invalid Data.csv");
		List<AppUser> usersList = null;
		MockMultipartFile file;
		try {
			file = new MockMultipartFile("file", "AppUsersCSV", "application/text", new FileInputStream(f));
			usersList = usersListCSVParserService.parseFile(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertNotNull(usersList);
		assertEquals(8, usersList.size());
		assertEquals("B00100001", usersList.get(0).getUserId());
		assertEquals("User", usersList.get(0).getFirstName());
		assertEquals("One", usersList.get(0).getLastName());
		assertEquals("sriram.ponangi@gmail.com", usersList.get(0).getEmail());
		assertEquals(true, usersList.get(0).getPassword().length() > 0);
		assertEquals(1, usersList.get(0).getEnabled());

	}

}
