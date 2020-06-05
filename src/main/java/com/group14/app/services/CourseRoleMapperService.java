package com.group14.app.services;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group14.app.repositories.CourseRoleMapperRepository;

@Service
public class CourseRoleMapperService {
	@Autowired
	CourseRoleMapperRepository cRMP;
	
	public void addCourseInstructor(String banner, String cid)
	{
		try 
		{
			cRMP.addCourseI(banner, cid);;
			
		}
		catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
}
