package com.group14.app.repositories;

import java.sql.SQLException;
import java.util.List;

import com.group14.app.models.PasswordValidatorRules;

public interface IPasswordReposiotry {
	List<PasswordValidatorRules> getActiveRules() throws SQLException;

	List<String> getPreviousPasswords(String userId, int limit) throws SQLException;

	int[] updatePassword(String userId, String newPassword) throws SQLException;
}
