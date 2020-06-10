package com.group14.app.services;

import java.util.List;

import com.group14.app.models.AppUser;
import com.group14.app.models.PasswordValidatorRules;

public interface IPasswordValidatorService {
	/*
	 * Return all the failed rule description. If all rules are satisfied return
	 * empty list.
	 */
	List<PasswordValidatorRules> validatePassword(AppUser user, String newPassword);
}
