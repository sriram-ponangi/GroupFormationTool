package com.group14.app.controllers;

import java.util.ArrayList;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.group14.app.models.Courses;
import com.group14.app.models.UserPrincipal;
import com.group14.app.repositories.ICourseStudRepository;

@Controller
public class CoursePageController {

	private ICourseStudRepository ICourseStudRepository;

	public CoursePageController(ICourseStudRepository ICourseStudRepository) {
		this.ICourseStudRepository = ICourseStudRepository;
	}

	@GetMapping("/student/mycourses")
	public String listStudent(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserPrincipal userPrincipal = (UserPrincipal) principal;
		String uid = userPrincipal.getUser().getUserId();
		ArrayList<Courses> courseList = ICourseStudRepository.getAssignedCourse(uid);
		model.addAttribute("courseList", courseList);
		return "TAandInstructorCourses";
	}

	@GetMapping("/guest/allcourses")
	public String listTA(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserPrincipal userPrincipal = (UserPrincipal) principal;
		String uid = userPrincipal.getUser().getUserId();
		ArrayList<Courses> courseList = ICourseStudRepository.getAllCourse(uid);
		model.addAttribute("courseList", courseList);

		return "StudentCourses";
	}

}
