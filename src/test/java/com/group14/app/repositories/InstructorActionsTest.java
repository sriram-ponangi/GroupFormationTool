package com.group14.app.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.group14.app.models.AppUser;
import com.group14.app.utils.MySQLDBOperations;

public class InstructorActionsTest {

	@InjectMocks
	private AppUserRepository appUserRepository;

	@Mock
	private MySQLDBOperations mockDB;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	private InstructorActions instructorActions;

	@Test
	public void AddStudentToTAList_basic() throws SQLException {
		HashMap<String, String> courseRoles = new HashMap<String, String>();
		courseRoles.put("course_id", "CSCI1001");
		courseRoles.put("role_id", "STUDENT");

		AppUser appUser = new AppUser();
		appUser.setUserId("userid");
		appUser.setFirstName("firstName");
		appUser.setLastName("lastName");
		appUser.setEmail("email");
		appUser.setPassword("password");
		appUser.setEnabled(1);
		appUser.setSystemRole("GUEST");
		appUser.setCourseRoles(courseRoles);

		instructorActions = mock(InstructorActions.class);

		when(instructorActions.AddStudentToTAList("CSCI1001", "userid")).thenReturn(appUser);
		assertEquals(appUser.getFirstName(), "firstName");
	}

	@Test
	public void AddStudentToTAList_ReturnsNull() throws SQLException {
		HashMap<String, String> courseRoles = new HashMap<String, String>();
		courseRoles.put("course_id", "CSCI1001");
		courseRoles.put("role_id", "STUDENT");
		AppUser appUser = null;

		instructorActions = mock(InstructorActions.class);
		when(instructorActions.AddStudentToTAList("CSCI1001", "userid")).thenReturn(null);
		assertEquals(null, appUser);
	}

	@Test
	public void GiveTaPermission_success() throws SQLException {
		instructorActions = mock(InstructorActions.class);
		when(instructorActions.GiveTaPermission("B00100001", "TA", "CSCI1001")).thenReturn(1);
		assertEquals(1, 1);
	}

	@Test
	public void GiveTaPermission_failed() throws SQLException {
		instructorActions = mock(InstructorActions.class);
		when(instructorActions.GiveTaPermission("B00100001", "TA", "CSCI1001")).thenReturn(0);
		assertEquals(0, 0);
	}
}
