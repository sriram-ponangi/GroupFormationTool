package com.group14.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.group14.app.models.AppUser;
import com.group14.app.repositories.IUserRepository;

@Controller
public class RegisterController {

	private IUserRepository IUserRepository;

	public RegisterController(IUserRepository IUserRepository) {
		this.IUserRepository = IUserRepository;
	}

	@GetMapping("/register")
	public String greetingForm(Model model) {
		model.addAttribute("greeting", new AppUser());
		return "register";
	}

	@PostMapping("/confirm")
	public String greetingSubmit(@ModelAttribute AppUser greeting) {

		IUserRepository.addUser(greeting.getUserId(), greeting.getFirstName(), greeting.getLastName(),
				greeting.getEmail(), greeting.getPassword());
		return "result";
	}

}