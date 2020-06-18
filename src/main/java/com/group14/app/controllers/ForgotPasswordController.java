package com.group14.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group14.app.models.AppUser;
import com.group14.app.models.Forgotpassword;
import com.group14.app.services.EmailService;

@Controller
public class ForgotPasswordController {

	@Autowired
	private EmailService emailService;

	@GetMapping("/forgotpassword")
	public String forgotForm(Model model) {
		model.addAttribute("forgotpassword", new Forgotpassword());
		return "forgotpassword";
	}

	@PostMapping("/forgotpassword")
	public String forgotSubmit(@ModelAttribute Forgotpassword forgotpassword) {
		try {
			emailService.sendMail(forgotpassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "RecoverPass";
	}
}
