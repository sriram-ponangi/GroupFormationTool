package com.group14.app.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.group14.app.models.AppUser;
import com.group14.app.models.SQLInput;
import com.group14.app.utils.CRUDRepository;

@Repository
public class StudentEnrollmentRepository implements IStudentEnrollmentRepository {

	private static final Logger LOG = LoggerFactory.getLogger(StudentEnrollmentRepository.class);

	private CRUDRepository<SQLInput> db;

	public StudentEnrollmentRepository(CRUDRepository<SQLInput> db) {
		this.db = db;
	}

	@Override
	public int[] enrollStudentToCourse(AppUser user, String courseId) throws SQLException {
		List<SQLInput> transactionsQueries = new ArrayList<>();

		String isExistingUser = "SELECT user_id FROM Users WHERE user_id= ?";
		List<Object> params1 = new ArrayList<>();
		params1.add(user.getUserId());
		String sqlCreateOrUpdateUser;
		if (db.existsById(new SQLInput(isExistingUser, params1))) {
			sqlCreateOrUpdateUser = " UPDATE Users " + " SET password = ?, first_name= ?, last_name= ?, email = ? "
					+ " WHERE user_id = ? ";
		} else {
			sqlCreateOrUpdateUser = " INSERT INTO Users " + " ( password, first_name, last_name, email, user_id ) "
					+ " VALUES (?, ?, ?, ?, ?)";
		}

		List<Object> transactionParams1 = new ArrayList<>();
		transactionParams1.add(user.getPassword());
		transactionParams1.add(user.getFirstName());
		transactionParams1.add(user.getLastName());
		transactionParams1.add(user.getEmail());
		transactionParams1.add(user.getUserId());

		transactionsQueries.add(new SQLInput(sqlCreateOrUpdateUser, transactionParams1));
		LOG.info("Completed Framing the Query for Creating new Users ");

		String hasSystemRole = "SELECT * FROM SystemRoleMapper WHERE user_id= ?";
		List<Object> params2 = new ArrayList<>();
		params2.add(user.getUserId());

		String sqlCreateOrUpdateSystemRole;
		if (db.existsById(new SQLInput(hasSystemRole, params2))) {
			sqlCreateOrUpdateSystemRole = " UPDATE SystemRoleMapper SET role_id = ? WHERE user_id = ? ";
		} else {
			sqlCreateOrUpdateSystemRole = " INSERT INTO SystemRoleMapper (  role_id, user_id ) VALUES (?,?)";
		}
		List<Object> transactionParams2 = new ArrayList<>();
		transactionParams2.add("GUEST");
		transactionParams2.add(user.getUserId());

		transactionsQueries.add(new SQLInput(sqlCreateOrUpdateSystemRole, transactionParams2));
		LOG.info("Completed Framing the Query for Creating System role ");

		String hasCourseRole = "SELECT user_id, course_id FROM CourseRoleMapper WHERE user_id= ? AND course_id= ?";
		List<Object> params3 = new ArrayList<>();
		params3.add(user.getUserId());
		params3.add(courseId);

		String sqlCreateOrUpdateCourseRole;
		if (db.existsById(new SQLInput(hasCourseRole, params3))) {
			sqlCreateOrUpdateCourseRole = " UPDATE CourseRoleMapper SET role_id = ? WHERE course_id=? and user_id = ? ";
		} else {
			sqlCreateOrUpdateCourseRole = " INSERT INTO CourseRoleMapper ( role_id, course_id, user_id ) VALUES (?, ?, ?) ";
		}
		List<Object> transactionParams3 = new ArrayList<>();
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
