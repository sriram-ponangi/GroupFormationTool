package com.group14.app.repositories;

import java.sql.SQLException;

import com.group14.app.models.AppUser;

public interface IInstructorActionsRepository {

	public AppUser AddStudentToTAList(String courseId, String bannerId) throws SQLException;

	public int GiveTaPermission(String bannerId, String role, String courseId) throws SQLException;
}
