package com.group14.app.repositories;

import com.group14.app.models.AppUser;

public interface IInstructorActionsRepository {

	public AppUser AddStudentToTAList(String courseId, String bannerId);
	
	public int GiveTaPermission(String bannerId, String role, String courseId);
}
