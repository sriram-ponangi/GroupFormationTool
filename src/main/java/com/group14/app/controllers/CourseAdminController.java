package com.group14.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.group14.app.models.Courses;

@Controller
public class CourseAdminController {

	private static final Logger LOG = LoggerFactory.getLogger(CourseAdminController.class);

	@GetMapping("/ta/courseAdmin")
	public String assignStudent(Model model) {
		model.addAttribute("courseAdmin", new Courses());
		LOG.info("Displaying course Admin page.");
		return "courseAdmin";
	}

	@PostMapping("/ta/courseAdmin/assignstudent")
	public String assignStudent(@ModelAttribute Courses courseAdmin) {
		LOG.info("Assigning students to a course with courseId: {}", courseAdmin.getCid());
		return "redirect:/ta/upload-csv?courseId=" + courseAdmin.getCid();
	}

	@PostMapping("/ta/courseAdmin/createSurvey")
	public String createSurvey(@ModelAttribute Courses courseAdmin) {
		LOG.info("Creating a survey for courseId: {}", courseAdmin.getCid());
		return "redirect:/ta/createSurvey?courseId=" + courseAdmin.getCid();
	}

	@PostMapping("/instructor/courseAdmin/assignta")
	public String assignta(@ModelAttribute Courses courseAdmin) {
		LOG.info("Assigning a TA to courseId: {}", courseAdmin.getCid());
		return "redirect:/instructor/assignta?id=" + courseAdmin.getCid();
	}

}
