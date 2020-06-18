package com.group14.app.repositories;

import java.util.List;

import com.group14.app.models.PasswordValidatorRules;

public interface IPasswordReposiotry {
	List<PasswordValidatorRules> getActiveRules();

	List<String> getPreviousPasswords(String userId, int limit);

	int[] updatePassword(String userId, String newPassword);
}
