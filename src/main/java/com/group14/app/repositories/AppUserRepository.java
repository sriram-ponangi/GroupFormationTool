package com.group14.app.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.group14.app.models.AppUser;
import com.group14.app.models.SQLInput;
import com.group14.app.utils.MySQLDBOperations;
import com.group14.app.utils.CRUDRepository;

@Repository
public class AppUserRepository implements IAppUserRepository {

	private static final Logger LOG = LoggerFactory.getLogger(AppUserRepository.class);

	private CRUDRepository<SQLInput> db;

	public AppUserRepository(CRUDRepository<SQLInput> db) {
		this.db = db;
	}

	@Override
	public AppUser findByUserName(String id) throws SQLException {
		final AppUser appUser = new AppUser();
		List<Object> params = new ArrayList<>();
		params.add(id);
		final String SQL_GET_USER = "SELECT * FROM Users WHERE user_id= ?";
		List<HashMap<String, Object>> usersData = db.readData(new SQLInput(SQL_GET_USER, params));

		if (usersData != null)
			usersData.stream().forEach(row -> {
				appUser.setUserId((String) row.get("user_id"));
				appUser.setPassword((String) row.get("password"));
				appUser.setEmail((String) row.get("email"));
				appUser.setFirstName((String) row.get("first_name"));
				appUser.setLastName((String) row.get("last_name"));
				appUser.setEnabled((Integer) row.get("enabled"));
			});
		else {
			LOG.error("Could not Execute: " + SQL_GET_USER);
			return null;
		}

		final String SQL_GET_SYSTEM_ROLE = "SELECT  ROLE_ID FROM SystemRoleMapper WHERE user_id= ?";
		List<HashMap<String, Object>> systemRolesData = db.readData(new SQLInput(SQL_GET_SYSTEM_ROLE, params));
		if (systemRolesData != null)
			systemRolesData.stream().forEach(row -> {
				appUser.setSystemRole((String) row.get("role_id"));
			});
		else {
			LOG.error(
					"Database is inconsistent User available in Users table without even having the default 'Guest' role in SystemRoleMapper table");
			return null;
		}

		final String SQL_GET_COURSES_ROLES = "SELECT  course_id, role_id FROM CourseRoleMapper WHERE user_id= ?";
		List<HashMap<String, Object>> courseRolesData = db.readData(new SQLInput(SQL_GET_COURSES_ROLES, params));
		if (courseRolesData != null) {
			Map<String, String> courseRoles = new HashMap<>();
			courseRolesData.stream()
					.forEach(row -> courseRoles.put((String) row.get("course_id"), (String) row.get("role_id")));
			appUser.setCourseRoles(courseRoles);
		} else {
			LOG.error("Could not Execute: " + SQL_GET_COURSES_ROLES);
			return null;
		}

		return appUser;
	}

}
