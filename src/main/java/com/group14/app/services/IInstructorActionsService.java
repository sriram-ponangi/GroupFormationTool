package com.group14.app.services;

import com.group14.app.models.AppUser;

public interface IInstructorActionsService {

	public AppUser AddStudentToTAList(String courseId, String bannerId);
	
	public int GiveTaPermission(String bannerId, String role, String courseId);
}
