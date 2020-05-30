package com.group14.app.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.group14.app.models.AppUser;

public class AppUserRepositoryTest {

	@InjectMocks
	private AppUserRepository appUserRepository;

	@Mock
	private NamedParameterJdbcTemplate jdbcTemplate;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

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

	}

}
