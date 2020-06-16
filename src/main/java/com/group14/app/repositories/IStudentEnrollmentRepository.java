package com.group14.app.repositories;

import com.group14.app.models.AppUser;

public interface IStudentEnrollmentRepository {
	int[] enrollStudentToCourse(AppUser user, String courseId);
}
