package com.group14.app.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.group14.app.models.AppUser;
import com.group14.app.models.SQLInput;
import com.group14.app.utils.CRUDRepository;

public class AppUserRepositoryTest {

	@InjectMocks
	private AppUserRepository appUserRepository;

	@Mock
	private CRUDRepository<SQLInput> mockDB;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findByUserNameTest_basic() throws SQLException {

		List<HashMap<String, Object>> usersData = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> row = new HashMap<String, Object>();
		row.put("user_id", "userid");
		row.put("password", "password");
		row.put("email", "email");
		row.put("first_name", "firstName");
		row.put("last_name", "lastName");
		row.put("enabled", 1);
		usersData.add(row);

		List<HashMap<String, Object>> systemRolesData = new ArrayList<HashMap<String, Object>>();
		row = new HashMap<String, Object>();
		row.put("role_id", "GUEST");
		systemRolesData.add(row);

		List<HashMap<String, Object>> courseRolesData = new ArrayList<HashMap<String, Object>>();
		row = new HashMap<String, Object>();
		row.put("course_id", "CSCI1001");
		row.put("role_id", "STUDENT");
		courseRolesData.add(row);
		row = new HashMap<String, Object>();
		row.put("course_id", "CSCI1002");
		row.put("role_id", "TA");
		courseRolesData.add(row);
		System.out.println(usersData);
		when(mockDB.readData(any(SQLInput.class))).thenReturn(usersData).thenReturn(systemRolesData)
				.thenReturn(courseRolesData);

		final AppUser appUser = this.appUserRepository.findByUserName("userid");

		assertEquals("userid", appUser.getUserId());
		assertEquals("password", appUser.getPassword());
		assertEquals("email", appUser.getEmail());
		assertEquals("firstName", appUser.getFirstName());
		assertEquals("lastName", appUser.getLastName());
		assertEquals(1, appUser.getEnabled());
		assertEquals("GUEST", appUser.getSystemRole());
		assertEquals(true, appUser.getCourseRoles().containsKey("CSCI1001"));
		assertEquals("STUDENT", appUser.getCourseRoles().get("CSCI1001"));
		assertEquals(true, appUser.getCourseRoles().containsKey("CSCI1001"));
		assertEquals("TA", appUser.getCourseRoles().get("CSCI1002"));
	}
}
