package com.group14.app.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group14.app.models.AppUser;
import com.group14.app.models.Course;
import com.group14.app.models.Student;
import com.group14.app.repositories.AppUserRepository;
import com.group14.app.services.IInstructorActionsService;

@Controller
public class InstructorController {
	
	private AppUserRepository appUserRepo = new AppUserRepository();
	
	private Course course = new Course();
	private AppUser mAppUser = new AppUser();
	private AppUser mAppUserStudent = new AppUser();
	
	private IInstructorActionsService instructorActionsService;

	public InstructorController(IInstructorActionsService instructorActionsService) {
		this.instructorActionsService = instructorActionsService;
	}
	
	@GetMapping("/instructor/assignta")
	public String AssignTaGET(Model model, @RequestParam(name="id") String courseId) {
		
		AppUser appUser = new AppUser();
		AppUser student = new AppUser();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) 
		{
			mAppUser.setUserId(((UserDetails)principal).getUsername());
		} 
		else 
		{	
			mAppUser.setUserId(principal.toString());
		}
		appUser = appUserRepo.findByUserName(mAppUser.getUserId());
		mAppUser.setCourseRoles(appUser.getCourseRoles());
		model.addAttribute("studentUser", new Student());
		course.setcourseId(courseId);
		if(mAppUser.getCourseRoles().get(course.getcourseId()) != null && mAppUser.getCourseRoles().get(course.getcourseId()).equalsIgnoreCase("Instructor")) 
		{
			model.addAttribute("student", student);
			return "assignta";
		}
		else
		{
			model.addAttribute("errorMessage", "You are not authorized to assign TA.");
			return "assigntaError";
		}
	}
	
	@PostMapping("instructor/assignta")
	public String AssignTaPost(@ModelAttribute("studentUser") Student studentUser, Model model) {
		
		AppUser user;
		
		user = instructorActionsService.AddStudentToTAList(course.getcourseId(), studentUser.getBannerId());
		mAppUserStudent.setUserId(studentUser.getBannerId());
		
		if(user != null)
		{
			user.setFirstName(user.getFirstName() + " " + user.getLastName());
			model.addAttribute("student", user);
			return "assignta";
		}
		else
		{
			model.addAttribute("error", "Please use correct Banner Id");
			return "assignta";
		}
	}
	
	@PostMapping("/instructor/assigntasubmit")
	public String AssignTaPosition(Model model) {
		int res = instructorActionsService.GiveTaPermission(mAppUserStudent.getUserId(), course.getcourseId());
		if(res > 1)
		{
			model.addAttribute("success", "TA is assigned");
			return "assignta";
		}
		else
		{
			model.addAttribute("error", "We ran into an error while processing your request");
			return "assignta";
		}		
	}
	
	@GetMapping("/instructor/assigntasubmit")
	public String AssignTaPositionGet(Model model) {
		int res = instructorActionsService.GiveTaPermission(mAppUserStudent.getUserId(), course.getcourseId());
		if(res > 0)
		{
			model.addAttribute("success", "TA is assigned");
			return "assignta";
		}
		else
		{
			model.addAttribute("error", "We ran into an error while processing your request");
			return "assignta";
		}
	}
	
	
}
