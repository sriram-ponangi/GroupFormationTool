package com.group14.app.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.group14.app.models.AppUser;
import com.group14.app.models.PasswordValidatorRules;
import com.group14.app.repositories.IPasswordReposiotry;

@Service
public class PasswordService implements IPasswordService {

	private IPasswordReposiotry pvr;

	public PasswordService(IPasswordReposiotry pvr) {
		this.pvr = pvr;
	}

	@Override
	public List<PasswordValidatorRules> validatePassword(AppUser user, String newPassword) throws SQLException {
		List<PasswordValidatorRules> activeRules = pvr.getActiveRules();
		List<PasswordValidatorRules> failedRules = new ArrayList<>();

		for (PasswordValidatorRules rule : activeRules) {
			boolean ruleFailed = true;
			if (rule.getRuleId().equalsIgnoreCase("PASSWORD_HISTORY")) {
				List<String> previousPasswords = pvr.getPreviousPasswords(user.getUserId(), rule.getMinMatchCount());
				for (String p : previousPasswords) {
					if (p.equals(newPassword)) {
						ruleFailed = true;
						break;
					} else
						ruleFailed = false;
				}
			} else {
				int count = 0;
				Pattern p = Pattern.compile(rule.getRegEx());
				Matcher m = p.matcher(newPassword);
				while (m.find())
					count++;
				if ((rule.getMinMatchCount() == -1 && count <= rule.getMaxMatchCount()) || // Checking only Min Count
						(count >= rule.getMinMatchCount() && rule.getMaxMatchCount() == -1) || // Checking only Max
																								// Count
						(count >= rule.getMinMatchCount() && count <= rule.getMaxMatchCount()) // Checking both Min and
																								// Max Counts
				) {
					ruleFailed = false;
				}
			}

			if (ruleFailed) {
				failedRules.add(rule);
			}
		}
		return failedRules;
	}

	@Override
	public boolean updatePassword(String userId, String newPassword) throws SQLException {
		int[] rowsUpdated = pvr.updatePassword(userId, newPassword);
		for (int count : rowsUpdated) {
			if (count <= 0)
				return false;
		}
		return true;
	}

}
