package com.group14.app.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group14.app.models.CourseRoleMapper;

public interface ICourseRoleMapperRepository {

	public List<CourseRoleMapper> list() throws SQLException;

	public void addCourseI(String banner, String cid) throws SQLException;

	public ArrayList<CourseRoleMapper> getInstructorId(String courseId) throws SQLException;
}
