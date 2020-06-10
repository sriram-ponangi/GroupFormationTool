package com.group14.app.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.group14.app.models.PasswordValidatorRules;
import com.group14.app.models.SQLInput;
import com.group14.app.utils.CRUDRepository;
import com.group14.app.utils.MySQLDBOperations;

public class PasswordValidatorReposiotry implements IPasswordValidatorReposiotry{
	
	private CRUDRepository<SQLInput> db = new MySQLDBOperations();
	
	private static final Logger LOG = LoggerFactory.getLogger(PasswordValidatorReposiotry.class);

	@Override
	public List<PasswordValidatorRules> getActiveRules() {
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
}
