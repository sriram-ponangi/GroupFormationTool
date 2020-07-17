package com.group14.app.controllers;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.group14.app.models.AppUser;
import com.group14.app.models.PasswordValidatorRules;
import com.group14.app.models.UserPrincipal;
import com.group14.app.services.IPasswordService;

@Controller
public class PasswordController {

	private IPasswordService pvs;
	private static final Logger LOG = LoggerFactory.getLogger(PasswordController.class);

	public PasswordController(IPasswordService pvs) {
		this.pvs = pvs;
	}

	@GetMapping("/guest/updatePassword")
	public String updatePasswordPage(Model model) {
		model.addAttribute("user", new AppUser());
		return "UpdateUserPassword";
	}

	@PostMapping("/guest/updatePassword")
	public String updatePassword(@ModelAttribute AppUser userPassword, Model model) throws SQLException {
		String newPassword = userPassword.getPassword();
		if (newPassword == null) {
			return "UpdatePasswordError";
		}
		AppUser user = AppUser.getCurrentUser();
		System.out.println(user);
		List<PasswordValidatorRules> failedRulesList = pvs.validatePassword(user, newPassword);
		LOG.info("Updating password for a user.");
		if (failedRulesList != null && failedRulesList.size() == 0) {
			// Updating Password in users table and passwordHistory table
			boolean updateSuccessful = pvs.updatePassword(user.getUserId(), newPassword);
			if (updateSuccessful) {
				return "UpdatePasswordSuccess";
			}
		}
		model.addAttribute("failedRulesList", failedRulesList);
		return "UpdatePasswordError";
	}
}
