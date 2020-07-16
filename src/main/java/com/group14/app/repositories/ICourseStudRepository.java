package com.group14.app.repositories;

import java.sql.SQLException;
import java.util.ArrayList;

import com.group14.app.models.Courses;

public interface ICourseStudRepository {
	public ArrayList<Courses> getAllCourse(String id) throws SQLException;

	public ArrayList<Courses> getAssignedCourse(String id) throws SQLException;

}
