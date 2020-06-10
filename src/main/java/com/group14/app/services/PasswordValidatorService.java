package com.group14.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.group14.app.models.AppUser;
import com.group14.app.models.PasswordValidatorRules;
import com.group14.app.repositories.IPasswordValidatorReposiotry;
import com.group14.app.repositories.PasswordValidatorReposiotry;

public class PasswordValidatorService implements IPasswordValidatorService{
	
	private IPasswordValidatorReposiotry pvr = new PasswordValidatorReposiotry();
		
	@Override
	public List<PasswordValidatorRules> validatePassword(AppUser user, String newPassword) {
		List<PasswordValidatorRules> activeRules = pvr.getActiveRules();
		List<PasswordValidatorRules> failedRules = new ArrayList<>();
		
		for(PasswordValidatorRules rule: activeRules) {
			boolean ruleFailed = true;
			if(rule.getRuleId().equalsIgnoreCase("PASSWORD_HISTORY")) {
				
			}
			else {
				int count=0;
				Pattern p = Pattern.compile(rule.getRegEx());
				Matcher m = p.matcher(newPassword);
				while(m.find())
					count++;
				if(
				(rule.getMinMatchCount() == -1 && count <= rule.getMaxMatchCount()) || // Checking only Min Count
				(count >= rule.getMinMatchCount() && rule.getMaxMatchCount() == -1) || // Checking only Max Count
				(count >= rule.getMinMatchCount() && count <= rule.getMaxMatchCount()) // Checking both Min and Max Counts
				){
					ruleFailed = false;
				}				
			}
			
			if(ruleFailed) {
				failedRules.add(rule);
			}
		}
		return failedRules;
	}	

}
