package com.group14.app.services;

import java.sql.SQLException;
import java.util.List;

import com.group14.app.models.Courses;

public interface ICourseService {

	public void addCourse(Courses courses);

	public void deleteCourse(Courses courses);

	public List<Courses> list() throws SQLException;

}
