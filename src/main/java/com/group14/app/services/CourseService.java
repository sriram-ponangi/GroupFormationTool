package com.group14.app.services;

import java.sql.SQLException;
import org.springframework.stereotype.Service;
import com.group14.app.models.Courses;
import com.group14.app.repositories.CourseRepository;

@Service
public class CourseService {

	CourseRepository courseRepository = new CourseRepository();
	
	public void addCourse(Courses courses)
	{
		try 
		{
			courseRepository.addCourse(courses);
		}
		catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	
	public void deleteCourse(Courses courses)
	{
		try 
		{
			courseRepository.deleteCourse(courses);
		}
		catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
}
