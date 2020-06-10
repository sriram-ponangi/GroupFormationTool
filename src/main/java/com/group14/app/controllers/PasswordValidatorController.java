package com.group14.app.controllers;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.group14.app.models.AppUser;
import com.group14.app.models.PasswordValidatorRules;
import com.group14.app.models.UserPrincipal;
import com.group14.app.services.IPasswordValidatorService;
import com.group14.app.services.PasswordValidatorService;

@Controller
public class PasswordValidatorController {
	
	private IPasswordValidatorService pvs = new PasswordValidatorService();
	
	@GetMapping("/guest/updatePassword")
	public String updatePasswordPage(Model model) {
		 model.addAttribute("user", new AppUser());
		 return "updatePassword";		 
	}
	
	@PostMapping("/guest/updatePassword")
	public String updatePassword(@ModelAttribute AppUser userPassword, Model model) {
		 String newPassword = userPassword.getPassword();
		 if(newPassword == null) 
			 return "UpdatePasswordError";		
		 
		 AppUser user = getCurrentUser();		 
		 List<PasswordValidatorRules> failedRulesList = pvs.validatePassword(user, newPassword);
		 
		 if(failedRulesList!=null && failedRulesList.size() == 0) {
			 // Update Password in userser table and password history table
			 boolean updateSuccessful = pvs.updatePassword(user.getUserId(), newPassword);
			 if(updateSuccessful)
				 return "UpdatePasswordSuccess";
		 }
		 
		 model.addAttribute("failedRulesList", failedRulesList);
		 return "UpdatePasswordError";		 
	}
	
	public AppUser getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserPrincipal userPrincipal = (UserPrincipal) principal;
		return userPrincipal.getUser();
	}

}
