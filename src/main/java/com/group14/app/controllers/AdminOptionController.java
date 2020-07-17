package com.group14.app.controllers;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group14.app.exceptions.GlobalExceptionHandler;
import com.group14.app.models.CourseRoleMapper;
import com.group14.app.models.Courses;
import com.group14.app.repositories.CourseRepository;
import com.group14.app.repositories.CourseRoleMapperRepository;
import com.group14.app.repositories.ICourseRepository;
import com.group14.app.repositories.ICourseRoleMapperRepository;
import com.group14.app.services.CourseRoleMapperService;
import com.group14.app.services.CourseService;
import com.group14.app.services.ICourseRoleMapperService;
import com.group14.app.services.ICourseService;

@Controller
@RequestMapping("/admin")
public class AdminOptionController {

	ICourseRoleMapperRepository courseMapper;
	ICourseService courseService;
	ICourseRoleMapperService CourseRoleMapperService;
	private static final Logger LOG = LoggerFactory.getLogger(AdminOptionController.class);

	@GetMapping("/createCourse")
	public String createCourses(Model model) {

		LOG.info("Displaying create course page.");
		model.addAttribute("createCourse", new Courses());
		return "createCourse";
	}

	@PostMapping("/createCourse")
	public String createCourses(@ModelAttribute Courses courses) {
		courseService.addCourse(courses);
		LOG.info("Creating a course for course ID: {}", courses.getCid());
		return "redirect:/admin/allCourse";
	}

	@GetMapping("/deleteCourse")
	public String deleteCourses(Model model) {
		try {
			List<Courses> courseL = courseService.list();
			LOG.info("Displaying delete course page.");
			model.addAttribute("allCourse", courseL);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		model.addAttribute("deleteCourse", new Courses());
		return "deleteCourse";

	}

	@PostMapping("/deleteCourse")
	public String deleteCourses(@ModelAttribute Courses allCourse) {

		LOG.info("Deleting course with courseId: {}", allCourse.getCid());
		courseService.deleteCourse(allCourse);
		return "redirect:/admin/allCourse";
	}

	@GetMapping("/assignI")
	public String assignInstructor(Model model) {
		try {
			List<CourseRoleMapper> courseL = courseMapper.list();
			model.addAttribute("viewI", courseL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			List<Courses> courseL = courseService.list();
			model.addAttribute("allCourse", courseL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		model.addAttribute("assignI", new CourseRoleMapper());
		LOG.info("Displaying Assign Instructor page.");
		return "assignI";

	}

	@PostMapping("/assignI")
	public String assignInstructor(@ModelAttribute CourseRoleMapper assignI) {
		LOG.info("Assigning the Instructor with bannerId: {} to course with courseId: {}", assignI.getUser_id(),
				assignI.getCourse_id());
		CourseRoleMapperService.addCourseInstructor(assignI.getUser_id(), assignI.getCourse_id());
		return "redirect:/admin/viewI";
	}

	@GetMapping("/viewI")
	public String viewI(Model model) {
		try {
			List<CourseRoleMapper> courseL = courseMapper.list();
			model.addAttribute("viewI", courseL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "viewI";
	}

	@PostMapping("viewI")
	public String viewI(@ModelAttribute CourseRoleMapper viewI) {
		return "viewI";
	}

}
