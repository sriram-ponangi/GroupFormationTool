package com.group14.app.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.group14.app.models.AppUser;



@Repository
public class AppUserRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final Logger LOG = LoggerFactory.getLogger(AppUserRepository.class);

	public AppUser findByUserName(String id) {
		final String SQL_GET_USER = "SELECT * FROM Users WHERE USER_ID= :id";
		final String SQL_GET_SYSTEM_ROLE = "SELECT  ROLE_ID FROM SystemRoleMapper WHERE USER_ID= :id";
		final String SQL_GET_COURSES_ROLES = "SELECT  COURSE_ID, ROLE_ID FROM CourseRoleMapper WHERE USER_ID= :id";
		
		final Map<String, Object> params = new HashMap<>();
		params.put("id", id);
 
		AppUser appUser = jdbcTemplate.queryForObject(SQL_GET_USER, params,
				(rs, rowNum) -> new AppUser(rs.getString("user_id"), rs.getString("password"), rs.getString("email"),
						rs.getString("first_name"), rs.getString("last_name"), rs.getInt("enabled")));
		try {
			String systemRole = jdbcTemplate.queryForObject(SQL_GET_SYSTEM_ROLE, params, String.class);
			appUser.setSystemRole(systemRole);	
		}catch(EmptyResultDataAccessException e) {
			LOG.error("Database is inconsistent User available in Users table without even having the default 'Guest' role in SystemRoleMapper table");
			return null;
		}			

		List<Map<String, Object>> courseRolesList = jdbcTemplate.queryForList(SQL_GET_COURSES_ROLES, params);
		if(!courseRolesList.isEmpty()) {
			Map<String, String> courseRoles = courseRolesList.stream()
					.collect(Collectors.toMap(s -> (String) s.get("COURSE_ID"), s -> (String) s.get("ROLE_ID")));
			appUser.setCourseRoles(courseRoles);
		}
		

		return appUser;

	}

}
