package com.group14.app.controllers;

import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.group14.app.models.Courses;
import com.group14.app.services.ICourseService;

@Controller
public class HomeController {

	ICourseService course;

	public HomeController(ICourseService course) {
		this.course = course;
	}

	@GetMapping("/guest/dashboard")
	public String landingPage() {
		return "admin";
	}

	@GetMapping("/admin/allCourse")
	public String allCourses(Model model) {
		try {
			List<Courses> courseL = course.list();
			model.addAttribute("allCourse", courseL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "allCourse";
	}

	@PostMapping("/admin/allCourse")
	public String allCoursesSubmit(@ModelAttribute Courses allCourse) {
		return "courses";
	}

	@GetMapping("/admin/adminopt")
	public String adminOpt() {
		return "adminOptions";
	}

}
