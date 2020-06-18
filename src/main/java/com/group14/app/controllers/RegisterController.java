package com.group14.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

//import com.example.demo.UserRepository;
import com.group14.app.models.RegUser;
import com.group14.app.repositories.IChoicesRepository;
import com.group14.app.repositories.IUserRepository;
import com.group14.app.repositories.UserRepository;

@Controller
public class RegisterController {

	private IUserRepository IUserRepository;

	public RegisterController(IUserRepository IUserRepository) {
		this.IUserRepository = IUserRepository;
	}

	@GetMapping("/register")
	public String greetingForm(Model model) {
		model.addAttribute("greeting", new RegUser());
		return "register";
	}

	@PostMapping("/confirm")
	public String greetingSubmit(@ModelAttribute RegUser greeting) {

		IUserRepository.addUser(greeting.getBannerNo(), greeting.getFirstName(), greeting.getLastName(),
				greeting.getEmail(), greeting.getPassword());
		return "result";
	}

}