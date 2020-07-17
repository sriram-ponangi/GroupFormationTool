package com.group14.app.services;

import java.sql.SQLException;
import java.util.List;

import com.group14.app.models.AppUser;
import com.group14.app.models.PasswordValidatorRules;

public interface IPasswordService {
	/*
	 * Must be implemented to return all the failed rule description. If all rules
	 * are satisfied return empty list.
	 */
	List<PasswordValidatorRules> validatePassword(AppUser user, String newPassword) throws SQLException;

	boolean updatePassword(String userId, String newPassword) throws SQLException;
}
