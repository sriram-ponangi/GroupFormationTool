package com.group14.app.repositories;

import java.util.ArrayList;

import com.group14.app.models.Courses;

public interface ICourseStudRepository {
	public ArrayList<Courses> getAllCourse(String id);

	public ArrayList<Courses> getAssignedCourse(String id);

}
