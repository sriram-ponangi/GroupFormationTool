package com.group14.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.group14.app.models.AppUser;
import com.group14.app.services.EmailService;

@Controller
public class ForgotPasswordController {
	private static final Logger LOG = LoggerFactory.getLogger(ForgotPasswordController.class);

	@Autowired
	private EmailService emailService;

	@GetMapping("/forgotpassword")
	public String forgotForm(Model model) {
		LOG.info("Displaying forgot password page.");
		model.addAttribute("forgotpassword", new AppUser());
		return "forgotpassword";
	}

	@PostMapping("/forgotpassword")
	public String forgotSubmit(@ModelAttribute AppUser forgotpassword) {
		try {
			emailService.sendMail(forgotpassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOG.info("Submitting forgot password request.");
		return "RecoverPass";
	}
}
