package com.group14.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.group14.app.models.Courses;
import com.group14.app.repositories.CourseRepository;

public class CourseServiceTest {
	
	CourseService cs = new CourseService();
	CourseRepository courseRepository = new CourseRepository();
	
	
	@Test
	public void addCourseTest()
	{
		Courses c = new Courses();
		c.setCid("CSCI1111");
		c.setName("Course Any");
		c.setTerm("Summer");
		c.setYear("2020");
		c.setDescription("Course offered in summer");
		
		
		try
		{
			courseRepository.addCourse(c);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		assertNotNull(cs);
		assertEquals("CSCI1111", c.getCid());
		assertEquals("Course Any", c.getName());
		assertEquals("Summer", c.getTerm());
		assertEquals("2020", c.getYear());
		assertEquals("Course offered in summer", c.getDescription());
		
	}
	
	@Test
	public void deleteCourseTest()
	{
		Courses c = new Courses();
		c.setCid("CSCI1111");
		
		try
		{
			courseRepository.deleteCourse(c);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		
		assertNotNull(cs);
		assertEquals("CSCI1111", c.getCid());
		
		
		
	}

}
