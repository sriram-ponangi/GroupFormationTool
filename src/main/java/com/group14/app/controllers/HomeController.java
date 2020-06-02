package com.group14.app.controllers;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.group14.app.models.Courses;
import com.group14.app.repositories.CourseRepository;


@Controller
public class HomeController {
	
	CourseRepository course = new CourseRepository();
	
	@GetMapping("/adminDashboard")
	public String landingPage()
	{
		return "admin";
		
	}
	
	@GetMapping("/myCourse")
	public String myCourses() 
	{ 
		
		return "adminDashboard";
	}

	@GetMapping("/allCourse")
	public String allCourses(Model model)
	{
		try {
			List<Courses> courseL = course.list();
			
			model.addAttribute("allCourse", courseL);
			course.closeConnection();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return "allCourse";
		
	}
	
	@PostMapping("/allCourse")
	public String fallCoursesSubmit(@ModelAttribute Courses allCourse) {
	System.out.println("In post: "+allCourse.getName());
	return "courses";	
}
}
