package com.group14.app.repositories;

import java.sql.SQLException;
import java.util.List;

import com.group14.app.models.Courses;

public interface ICourseRepository {

	public List<Courses> list() throws SQLException;

	void addCourse(Courses courses) throws SQLException;

	void deleteCourse(Courses courses) throws SQLException;

}
