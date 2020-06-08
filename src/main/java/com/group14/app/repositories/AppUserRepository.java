package com.group14.app.repositories;

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
public class AppUserRepository {

	private CRUDRepository<SQLInput> db = new MySQLDBOperations();

	private static final Logger LOG = LoggerFactory.getLogger(AppUserRepository.class);

	public AppUser findByUserName(String id) {
		final AppUser appUser = new AppUser();
		List<String> params = new ArrayList<>();
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

	public int[] enrollStudentToCourse(AppUser user, String courseId) {
		List<SQLInput> transactionsQueries = new ArrayList<>();

		String isExistingUser = "SELECT user_id FROM Users WHERE user_id= ?";
		List<String> params1 = new ArrayList<>();
		params1.add(user.getUserId());
		String sqlCreateOrUpdateUser;
		if (db.existsById(new SQLInput(isExistingUser, params1)))
			sqlCreateOrUpdateUser = " UPDATE Users " + " SET password = ?, first_name= ?, last_name= ?, email = ? "
					+ " WHERE user_id = ? ";
		else
			sqlCreateOrUpdateUser = " INSERT INTO Users " + " ( password, first_name, last_name, email, user_id ) "
					+ " VALUES (?, ?, ?, ?, ?)";

		List<String> transactionParams1 = new ArrayList<>();
		transactionParams1.add(user.getPassword());
		transactionParams1.add(user.getFirstName());
		transactionParams1.add(user.getLastName());
		transactionParams1.add(user.getEmail());
		transactionParams1.add(user.getUserId());

		transactionsQueries.add(new SQLInput(sqlCreateOrUpdateUser, transactionParams1));
		LOG.info("Completed Framing the Query for Creating new Users ");

		String hasSystemRole = "SELECT * FROM SystemRoleMapper WHERE user_id= ?";
		List<String> params2 = new ArrayList<>();
		params2.add(user.getUserId());

		String sqlCreateOrUpdateSystemRole;
		if (db.existsById(new SQLInput(hasSystemRole, params2)))
			sqlCreateOrUpdateSystemRole = " UPDATE SystemRoleMapper SET role_id = ? WHERE user_id = ? ";
		else
			sqlCreateOrUpdateSystemRole = " INSERT INTO SystemRoleMapper (  role_id, user_id ) VALUES (?,?)";
		List<String> transactionParams2 = new ArrayList<>();
		transactionParams2.add("GUEST");
		transactionParams2.add(user.getUserId());

		transactionsQueries.add(new SQLInput(sqlCreateOrUpdateSystemRole, transactionParams2));
		LOG.info("Completed Framing the Query for Creating System role ");

		String hasCourseRole = "SELECT user_id, course_id FROM CourseRoleMapper WHERE user_id= ? AND course_id= ?";
		List<String> params3 = new ArrayList<>();
		params3.add(user.getUserId());
		params3.add(courseId);

		String sqlCreateOrUpdateCourseRole;
		if (db.existsById(new SQLInput(hasCourseRole, params3)))
			sqlCreateOrUpdateCourseRole = " UPDATE CourseRoleMapper SET role_id = ? WHERE course_id=? and user_id = ? ";
		else
			sqlCreateOrUpdateCourseRole = " INSERT INTO CourseRoleMapper ( role_id, course_id, user_id ) VALUES (?, ?, ?) ";
		List<String> transactionParams3 = new ArrayList<>();
		transactionParams3.add("STUDENT");
		transactionParams3.add(courseId);
		transactionParams3.add(user.getUserId());

		transactionsQueries.add(new SQLInput(sqlCreateOrUpdateCourseRole, transactionParams3));
		LOG.info("Completed Framing the Query for Creating Course role ");

		LOG.info("Transaction Details: \n " + transactionsQueries);
		int[] rowsUpdated = db.saveTransaction(transactionsQueries);

		return rowsUpdated;
	}

}
