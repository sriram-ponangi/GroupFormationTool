package com.group14.app.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.group14.app.models.AppUser;
//import com.group14.app.models.UserPrincipal;
import com.group14.app.services.IStudentEnrollmentService;

@Controller
@RequestMapping("/ta")
public class StudentEnrollmentController {

	private static final Logger LOG = LoggerFactory.getLogger(StudentEnrollmentController.class);

	private IStudentEnrollmentService studentEnrollmentService;

	public StudentEnrollmentController(IStudentEnrollmentService studentEnrollmentService) {
		this.studentEnrollmentService = studentEnrollmentService;
	}

	@GetMapping("/upload-csv")
	public String readUsersFromCSV(@RequestParam("courseId") String courseId, Model model) {
		model.addAttribute("courseId", courseId);
		LOG.info("Displaying page to upload csv for courseId: {}", courseId);
		if (!AppUser.hasInstructorOrTARoleForCourse(courseId)) {
			model.addAttribute("errorMessage", "Sorry you do not have the permission to perform this action.");
			return "StudentEnrollmentFormError";
		}
		return "StudentEnrollmentFormWithCSV";
	}

	@PostMapping("/upload-users")
	public String enrollStudentsFromCSV(@RequestParam("file") MultipartFile file,
			@RequestParam("courseId") String courseId, Model model) {
		model.addAttribute("courseId", courseId);
		if (!AppUser.hasInstructorOrTARoleForCourse(courseId)) {
			model.addAttribute("errorMessage", "Sorry you do not have the permission to perform this action.");
			return "StudentEnrollmentFormError";
		}
		LOG.info("Uploading csv of users to register them for the course with courseId: {}", courseId);
		if (!file.isEmpty()) {
			try {
				List<AppUser> invalidUsersList = studentEnrollmentService.enrollStudentsToCourseFromFile(file,
						courseId);
				model.addAttribute("invalidUsersList", invalidUsersList);
				if (invalidUsersList.isEmpty()) {
					model.addAttribute("successMessage", "Enrolling Students Successfully Completed");
				} else {
					model.addAttribute("warningMessage", "Could not enroll a few students with invalid data.");
				}
				return "StudentEnrollmentFormResponse";
			} catch (Exception e) {
				model.addAttribute("errorMessage", "Invalid File. Please Upload the correct file");
				return "StudentEnrollmentFormError";
			}
		}
		model.addAttribute("errorMessage", "Empty File. Please Upload the correct file");
		return "StudentEnrollmentFormError";
	}
}
