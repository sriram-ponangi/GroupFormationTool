package com.group14.app.services;

import java.sql.SQLException;

import com.group14.app.models.AppUser;

public interface IInstructorActionsService {

	public AppUser AddStudentToTAList(String courseId, String bannerId) throws SQLException;

	public int GiveTaPermission(String bannerId, String courseId) throws SQLException;
}
