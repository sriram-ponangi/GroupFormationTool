package com.group14.app.services;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group14.app.models.CourseRoleMapper;
import com.group14.app.repositories.CourseRoleMapperRepository;
import com.group14.app.repositories.ICourseRepository;
import com.group14.app.repositories.ICourseRoleMapperRepository;

@Service
public class CourseRoleMapperService implements ICourseRoleMapperService {

	ICourseRoleMapperRepository cRMP;

	public CourseRoleMapperService(ICourseRoleMapperRepository cRMP) {
		this.cRMP = cRMP;

	}

	public void addCourseInstructor(String banner, String cid) {
		try {
			cRMP.addCourseI(banner, cid);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<CourseRoleMapper> getInstructorId(String courseId) {

		try {
			ArrayList<CourseRoleMapper> roles = new ArrayList<CourseRoleMapper>();
			roles = cRMP.getInstructorId(courseId);
			return roles;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
}
