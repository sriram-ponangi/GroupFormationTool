package com.group14.app.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


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
import com.group14.app.utils.MySQLDBOperations;

public class AppUserRepositoryTest {
	


	@InjectMocks
	private AppUserRepository appUserRepository;

	@Mock
	private MySQLDBOperations mockDB;
	


	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findByUserNameTest_basic() {		
		
		List<HashMap<String,Object>> usersData = new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> row = new HashMap<String,Object>();
		row.put("user_id","userid");
		row.put("password","password");
		row.put("email","email");
		row.put("first_name","firstName");
		row.put("last_name","lastName");
		row.put("enabled",1);		
		usersData.add(row);
		
		
		List<HashMap<String,Object>> systemRolesData = new ArrayList<HashMap<String,Object>>();
		row = new HashMap<String,Object>();
		row.put("role_id","GUEST");
		systemRolesData.add(row);
		
		List<HashMap<String,Object>> courseRolesData = new ArrayList<HashMap<String,Object>>();
		row = new HashMap<String,Object>();
		row.put("course_id","CSCI1001");
		row.put("role_id","STUDENT");
		courseRolesData.add(row);
		row = new HashMap<String,Object>();
		row.put("course_id","CSCI1002");
		row.put("role_id","TA");
		courseRolesData.add(row);
		
		when(mockDB.readData(any(SQLInput.class)))
		.thenReturn(usersData)
		.thenReturn(systemRolesData)
		.thenReturn(courseRolesData);	
		
		final AppUser appUser = this.appUserRepository.findByUserName("userid");
		
		assertEquals("userid", appUser.getUserId());
		assertEquals("password", appUser.getPassword());
		assertEquals("email", appUser.getEmail());
		assertEquals("firstName", appUser.getFirstName());
		assertEquals("lastName", appUser.getLastName());
		assertEquals(1, appUser.getEnabled());
		assertEquals("GUEST", appUser.getSystemRole());
		assertEquals(true,appUser.getCourseRoles().containsKey("CSCI1001"));
		assertEquals("STUDENT",appUser.getCourseRoles().get("CSCI1001"));
		assertEquals(true,appUser.getCourseRoles().containsKey("CSCI1001"));
		assertEquals("TA",appUser.getCourseRoles().get("CSCI1002"));
	}
	
	@Test
	public void enrollStudentToCourseTest() {
		AppUser appUser = new AppUser("userId", "password", "email", "firstName", "lastName", 1);

		when(mockDB.existsById(any(SQLInput.class)))
		.thenReturn(false)
		.thenReturn(false)
		.thenReturn(false);
		int[] expectedResponse = new int[] {1,1,1};
		when(mockDB.saveTransaction(any()))
		.thenReturn(expectedResponse);
		
		 int[] response = this.appUserRepository.enrollStudentToCourse(appUser, any(String.class));
		 assertEquals(expectedResponse.length, response.length);
		 
		 when(mockDB.existsById(any(SQLInput.class)))
			.thenReturn(true)
			.thenReturn(true)
			.thenReturn(true);
		 expectedResponse = new int[] {1,1,1};
			when(mockDB.saveTransaction(any()))
			.thenReturn(expectedResponse);
		response = this.appUserRepository.enrollStudentToCourse(appUser, any(String.class));
		 assertEquals(expectedResponse.length, response.length);
	}
	
	
	
	/*
	 
	@Mock
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Test
	public void findByUserNameTest_basic() {
		AppUser appUser = new AppUser("userId", "password", "email", "firstName", "lastName", 1);

		List<Map<String, Object>> courseRolesList = new ArrayList<Map<String, Object>>();
		Map<String, Object> row = new HashMap<>();
		row.put("COURSE_ID", "CSCI1001");
		row.put("ROLE_ID", "STUDENT");
		courseRolesList.add(row);
		row = new HashMap<>();
		row.put("COURSE_ID", "CSCI1002");
		row.put("ROLE_ID", "TA");
		courseRolesList.add(row);
		
		

		when(this.jdbcTemplate.queryForObject(Mockito.anyString(), ArgumentMatchers.<Map<String, Object>>any(),
				ArgumentMatchers.<RowMapper<AppUser>>any())).thenReturn(appUser);

		when(jdbcTemplate.queryForObject(Mockito.anyString(), Mockito.anyMap(), Mockito.eq(String.class)))
				.thenReturn("GUEST");

		when(jdbcTemplate.queryForList(Mockito.anyString(), Mockito.anyMap())).thenReturn(courseRolesList);

		AppUser result = appUserRepository.findByUserName("userId");

		assertEquals("userId", result.getUserId());
		assertEquals("password", result.getPassword());
		assertEquals("email", result.getEmail());
		assertEquals("firstName", result.getFirstName());
		assertEquals("lastName", result.getLastName());
		assertEquals(1, result.getEnabled());
		assertEquals("GUEST", result.getSystemRole());
		assertEquals("STUDENT", result.getCourseRoles().get("CSCI1001"));
		assertEquals("TA", result.getCourseRoles().get("CSCI1002"));

	}
	
	@Test
	public void findByUserNameTest_userWithNoSystemRoles() {
		AppUser appUser = new AppUser("userId", "password", "email", "firstName", "lastName", 1);

		List<Map<String, Object>> courseRolesList = new ArrayList<Map<String, Object>>();
		

		when(this.jdbcTemplate.queryForObject(Mockito.anyString(), ArgumentMatchers.<Map<String, Object>>any(),
				ArgumentMatchers.<RowMapper<AppUser>>any())).thenReturn(appUser);

		when(jdbcTemplate.queryForObject(Mockito.anyString(), Mockito.anyMap(), Mockito.eq(String.class)))
		.thenThrow(EmptyResultDataAccessException.class);


		when(jdbcTemplate.queryForList(Mockito.anyString(), Mockito.anyMap())).thenReturn(courseRolesList);

		AppUser result = appUserRepository.findByUserName("userId");

		
		assertEquals(null, result);

	}
	
	
	@Test
	public void findByUserNameTest_userWithNoCourseRoles() {
		AppUser appUser = new AppUser("userId", "password", "email", "firstName", "lastName", 1);

		List<Map<String, Object>> courseRolesList = new ArrayList<Map<String, Object>>();
		

		when(this.jdbcTemplate.queryForObject(Mockito.anyString(), ArgumentMatchers.<Map<String, Object>>any(),
				ArgumentMatchers.<RowMapper<AppUser>>any())).thenReturn(appUser);

		when(jdbcTemplate.queryForObject(Mockito.anyString(), Mockito.anyMap(), Mockito.eq(String.class)))
				.thenReturn("GUEST");

		when(jdbcTemplate.queryForList(Mockito.anyString(), Mockito.anyMap())).thenReturn(courseRolesList);

		AppUser result = appUserRepository.findByUserName("userId");

		assertEquals("userId", result.getUserId());
		assertEquals("password", result.getPassword());
		assertEquals("email", result.getEmail());
		assertEquals("firstName", result.getFirstName());
		assertEquals("lastName", result.getLastName());
		assertEquals(1, result.getEnabled());
		assertEquals("GUEST", result.getSystemRole());
		assertEquals(null, result.getCourseRoles());

	}*/

}
