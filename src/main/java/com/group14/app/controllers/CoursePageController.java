package com.group14.app.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.group14.app.models.Courses;
import com.group14.app.models.UserPrincipal;
import com.group14.app.repositories.ICourseStudRepository;

@Controller
public class CoursePageController {
	
	private static final Logger LOG = LoggerFactory.getLogger(CoursePageController.class);
	private ICourseStudRepository ICourseStudRepository;

	public CoursePageController(ICourseStudRepository ICourseStudRepository) {
		this.ICourseStudRepository = ICourseStudRepository;
	}

	@GetMapping("/student/mycourses")
	public String listStudent(Model model) throws SQLException {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserPrincipal userPrincipal = (UserPrincipal) principal;
		String uid = userPrincipal.getUser().getUserId();
		ArrayList<Courses> courseList = ICourseStudRepository.getAssignedCourse(uid);
		model.addAttribute("courseList", courseList);
		LOG.info("Displaying courses of Instructor with bannerId: {}", uid);
		return "TAandInstructorCourses";
	}

	@GetMapping("/guest/allcourses")
	public String listTA(Model model) throws SQLException {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserPrincipal userPrincipal = (UserPrincipal) principal;
		String uid = userPrincipal.getUser().getUserId();
		ArrayList<Courses> courseList = ICourseStudRepository.getAllCourse(uid);
		model.addAttribute("courseList", courseList);
		LOG.info("Displaying all courses to bannerId: {}", uid);
		return "StudentCourses";
	}

}
