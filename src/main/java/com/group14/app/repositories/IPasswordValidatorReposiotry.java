package com.group14.app.repositories;

import java.util.List;

import com.group14.app.models.PasswordValidatorRules;

public interface IPasswordValidatorReposiotry {
	List<PasswordValidatorRules> getActiveRules();
}
