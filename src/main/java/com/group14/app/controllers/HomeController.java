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
import com.group14.app.models.Forgotpassword;
import com.group14.app.repositories.CourseRepository;


@Controller
public class HomeController {
	
	@Autowired
	CourseRepository course;

	
	@GetMapping("/guest/dashboard")
	public String landingPage()
	{
		return "admin";
		
	}	
	
//	@GetMapping("/student/myCourse")
//	public String myCourses() 
//	{ 
//		
//		return "adminDashboard";
//	}

	@GetMapping("/admin/allCourse")
	public String allCourses(Model model)
	{
		try {
			List<Courses> courseL = course.list();
			
			model.addAttribute("allCourse", courseL);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return "allCourse";
		
	}
	
	@PostMapping("/admin/allCourse")
	public String fallCoursesSubmit(@ModelAttribute Courses allCourse) {
	
	return "courses";	
	}
	
	@GetMapping("/admin/adminopt")
	public String adminOpt() 
	{ 
		return "adminOptions";
	}
	
}
