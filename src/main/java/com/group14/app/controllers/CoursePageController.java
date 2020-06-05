package com.group14.app.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.group14.app.models.Course;
import com.group14.app.models.UserPrincipal;
import com.group14.app.repositories.CoursesStudRepository;

@Controller // This means that this class is a Controller
public class CoursePageController {
	//DisplayCourseRepository dc=new DisplayCourseRepository();
	@Autowired
	CoursesStudRepository cr;
	 
	 @GetMapping("/student/mycourses")
	 public String listStudent(Model model) {
		 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserPrincipal userPrincipal = (UserPrincipal) principal;
		String uid=userPrincipal.getUser().getUserId();
		 //ArrayList<Course> courseList=cr.showCourse();
		 ArrayList<Course> courseList=cr.getAssignedCourse(uid);
	     model.addAttribute("courseList", courseList);
	     //model.addAttribute("courseId","CSCI1001");
	     return "TAandInstructorCourses";
	 }
	 @GetMapping("/guest/allcourses")
	 public String listTA(Model model) {
		 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserPrincipal userPrincipal = (UserPrincipal) principal;
		String uid=userPrincipal.getUser().getUserId();
		 //ArrayList<Course> courseList=cr.showCourse();
		 ArrayList<Course> courseList=cr.getAllCourse(uid);
	     model.addAttribute("courseList", courseList);
	     
	     return "StudentCourses";
	 }


}
