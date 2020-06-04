package com.group14.app.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.group14.app.models.AppUser;
import com.group14.app.models.UserPrincipal;
import com.group14.app.services.CourseAdminService;

@Controller
public class CourseAdminController {

	private static final Logger LOG = LoggerFactory.getLogger(CourseAdminController.class);
	
	@Autowired
	CourseAdminService courseAdminService;

	@GetMapping("/ta/upload-csv")
	public String readUsersFromCSV(@RequestParam("courseId") String courseId, Model model) {
		model.addAttribute("courseId", courseId);
		
		if(!hasAdminAccessToCourse(courseId)) {
			model.addAttribute("errorMessage", "Sorry you do not have the permission to perform this action.");
			return "StudentEnrollmentFormError";
		}
		
		return "StudentEnrollmentFormWithCSV";
	}

	@PostMapping("/ta/upload-users")
	public String enrollStudentsFromCSV(@RequestParam("file") MultipartFile file, @RequestParam("courseId") String courseId, Model model) {
		model.addAttribute("courseId", courseId);

		if (!hasAdminAccessToCourse(courseId)) {
			model.addAttribute("errorMessage", "Sorry you do not have the permission to perform this action.");
			return "StudentEnrollmentFormError";
		}
		
		if (!file.isEmpty()) {
	        try {	        	
	        	List<AppUser> invalidUsersList = courseAdminService.enrollStudentsToCourse(file, courseId);
	            
	    		model.addAttribute("invalidUsersList", invalidUsersList);
	    		if(invalidUsersList.isEmpty())
	    			model.addAttribute("successMessage","Enrolling Students Successfully Completed");
	    		else
	    			model.addAttribute("warningMessage","Could not enroll a few students with invalid data.");
	    		return "StudentEnrollmentFormResponse";
	        }catch (Exception e) {
	        	model.addAttribute("errorMessage", "Invalid File. Please Upload the correct file");
	    		return "StudentEnrollmentFormError";
			}
		}		
		model.addAttribute("errorMessage", "Empty File. Please Upload the correct file");
		return "StudentEnrollmentFormError";
	}
	
	public boolean hasAdminAccessToCourse(String courseId) {
		// Proceed only if Principal has current courseId with TA or INSTRUCTOR ROLE
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserPrincipal userPrincipal = (UserPrincipal) principal;
		String courseRole = userPrincipal.getUser().getCourseRoles().get(courseId);
		if(courseRole == null) {
			LOG.info(userPrincipal.getUser().getUserId() + " is accessing course admin feature with no ROLE for the course " + courseId);
			return false;
		}
		else {
			LOG.info(userPrincipal.getUser().getUserId() + " is accessing course admin feature with " + courseRole
					+ " ROLE for the course " + courseId);
			if (!(courseRole.equalsIgnoreCase("TA") || courseRole.equalsIgnoreCase("INSTRUCTOR"))) {			
				return false;
			}
		}		
		return true;
	}
	
}
