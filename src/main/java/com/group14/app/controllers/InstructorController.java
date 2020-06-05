package com.group14.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group14.app.models.AppUser;
import com.group14.app.models.Student;
import com.group14.app.repositories.AppUserRepository;
import com.group14.app.repositories.InstructorActions;

@Controller
public class InstructorController {

	@Autowired
	private InstructorActions instructorActions;
	
	@Autowired
	private AppUserRepository appUserRepo;

	private String courseID;
	private String bannerId;
	private String role;
	private String roleTA = "TA";
	private String bannerIdStudent;
	
	@GetMapping("/instructor/assignta")
	public String AssignTaGET(Model model, @RequestParam(name="id") String courseId) {
		AppUser appUser = new AppUser();
		AppUser student = new AppUser();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) 
		{
			bannerId = ((UserDetails)principal).getUsername();
		} 
		else 
		{	
			bannerId = principal.toString();
		}
		System.out.println("Current instructor's bannerId found" + bannerId);
		appUser = appUserRepo.findByUserName(bannerId);
		role = appUser.getCourseRoles().get(courseId);
		model.addAttribute("studentUser", new Student());
		this.courseID = courseId;
		System.out.println(role);
		if(role != null && role.equalsIgnoreCase("Instructor")) 
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
		
		System.out.println("Student's bannerId : " + studentUser.getBannerId());
		AppUser user;
		
		user = instructorActions.AddStudentToTAList(courseID, studentUser.getBannerId());
		bannerIdStudent = studentUser.getBannerId();
		
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
		int res = instructorActions.GiveTaPermission(bannerIdStudent, roleTA, courseID);
		if(res > 1)
		{
			System.out.println("result" + res);
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
		int res = instructorActions.GiveTaPermission(bannerIdStudent, roleTA, courseID);
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
