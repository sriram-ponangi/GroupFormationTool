package com.group14.app.repositories;

import java.util.ArrayList;

import com.group14.app.models.Course;

public interface ICourseStudRepository {
	public ArrayList<Course> getAllCourse(String id);

	public ArrayList<Course> getAssignedCourse(String id);

}
