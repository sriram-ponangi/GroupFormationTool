package com.group14.app.services;

import java.util.ArrayList;

import com.group14.app.models.CourseRoleMapper;

public interface ICourseRoleMapperService {

	public void addCourseInstructor(String banner, String cid);

	public ArrayList<CourseRoleMapper> getInstructorId(String courseId);

}
