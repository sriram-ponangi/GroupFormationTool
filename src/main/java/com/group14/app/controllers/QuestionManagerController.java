package com.group14.app.controllers;

import java.util.ArrayList;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.group14.app.models.AllQuestions;
import com.group14.app.models.Questions;
import com.group14.app.repositories.QuestionManagerRepository;

@Controller
public class QuestionManagerController {
	
	QuestionManagerRepository questionManagerRepository;
	
	private String bannerId;

	@GetMapping("/instructor/allquestions")
	public String AssignTaGET(Model model) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) 
		{
			bannerId = ((UserDetails)principal).getUsername();
		} 
		else 
		{	
			bannerId = principal.toString();
		}
		System.out.println("Current instructor's bannerId found = " + bannerId);
		ArrayList<AllQuestions> questionsList=questionManagerRepository.getAllQuestions(bannerId);
		model.addAttribute("questionsList", questionsList);
		model.addAttribute("question", new Questions());
		return "allQuestions";
	}
	
	@PostMapping("/instructor/createquestion")
	public String assignStudent() {
		return "redirect:/instructor/createquestion";
	}

	@PostMapping("/instructor/deletequestion")
	public String assignta(@ModelAttribute Questions question) {
		return "redirect:/instructor/deletequestion?id=" + question.getQid();
	}

}
