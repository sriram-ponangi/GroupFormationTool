package com.group14.app.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.group14.app.models.PasswordValidatorRules;
import com.group14.app.models.SQLInput;
import com.group14.app.utils.CRUDRepository;

@Repository
public class PasswordReposiotry implements IPasswordReposiotry {

	private CRUDRepository<SQLInput> db;

	private static final Logger LOG = LoggerFactory.getLogger(PasswordReposiotry.class);

	public PasswordReposiotry(CRUDRepository<SQLInput> db) {
		this.db = db;
	}

	@Override
	public List<PasswordValidatorRules> getActiveRules() throws SQLException {
		final String SQL = " SELECT * FROM PasswordRules WHERE enabled = 1";
		final List<Object> params = new ArrayList<>();
		final SQLInput sqlInput = new SQLInput(SQL, params);

		final List<HashMap<String, Object>> passwordRules = db.readData(sqlInput);
		final List<PasswordValidatorRules> activeRules = new ArrayList<>();

		if (passwordRules != null)
			passwordRules.stream().forEach(row -> {
				PasswordValidatorRules rules = new PasswordValidatorRules();
				rules.setRuleId((String) row.get("rule_id"));
				rules.setRegEx((String) row.get("regular_expression"));
				rules.setMinMatchCount((Integer) row.get("min_match_count"));
				rules.setMaxMatchCount((Integer) row.get("max_match_count"));
				rules.setDescription((String) row.get("description"));
				rules.setEnabled((Integer) row.get("enabled"));
				activeRules.add(rules);
			});
		else {
			LOG.error("Could not Execute: " + SQL);
			return null;
		}
		return activeRules;
	}

	@Override
	public List<String> getPreviousPasswords(String userId, int limit) throws SQLException {
		final String SQL = " SELECT password FROM PasswordHistory where user_id = ? ORDER BY created_date DESC LIMIT ?";
		final List<Object> params = new ArrayList<>();
		params.add(userId);
		params.add(limit);
		final SQLInput sqlInput = new SQLInput(SQL, params);

		final List<HashMap<String, Object>> passwords = db.readData(sqlInput);
		final List<String> previousPasswords = new ArrayList<>();

		if (passwords != null) {
			passwords.stream().forEach(row -> {
				previousPasswords.add((String) row.get("password"));
			});
		} else {
			LOG.error("Could not Execute: " + SQL);
			return null;
		}
		return previousPasswords;
	}

	@Override
	public int[] updatePassword(String userId, String newPassword) throws SQLException {
		final List<SQLInput> transactionsQueries = new ArrayList<>();
		final List<Object> params = new ArrayList<>();
		params.add(newPassword);
		params.add(userId);

		final String SQL_UPDATE_USERS_TABLE = " UPDATE Users SET password = ? WHERE user_id = ?";
		transactionsQueries.add(new SQLInput(SQL_UPDATE_USERS_TABLE, params));

		final String SQL_UPDATE_PASSWORD_HISTORY_TABLE = " INSERT INTO PasswordHistory ( password, user_id) VALUES (?, ?)";
		transactionsQueries.add(new SQLInput(SQL_UPDATE_PASSWORD_HISTORY_TABLE, params));

		int[] rowsUpdated = db.saveTransaction(transactionsQueries);

		return rowsUpdated;
	}
}
