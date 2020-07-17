package com.group14.app.repositories;

import java.sql.SQLException;

import com.group14.app.models.AppUser;

public interface IStudentEnrollmentRepository {
	int[] enrollStudentToCourse(AppUser user, String courseId) throws SQLException;
}
