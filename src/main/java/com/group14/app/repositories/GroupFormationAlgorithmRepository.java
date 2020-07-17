package com.group14.app.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.group14.app.models.GroupFormationAlgoRule;
import com.group14.app.models.SQLInput;
import com.group14.app.models.SurveyRuleMapper;
import com.group14.app.utils.CRUDRepository;

@Repository
public class GroupFormationAlgorithmRepository implements IGroupFormationAlgorithmRepository {

	private CRUDRepository<SQLInput> db;

	private static final Logger LOG = LoggerFactory.getLogger(GroupFormationAlgorithmRepository.class);

	public GroupFormationAlgorithmRepository(CRUDRepository<SQLInput> db) {
		this.db = db;
	}

	@Override
	public List<GroupFormationAlgoRule> getAllGroupFormationAlgoRules() throws SQLException {
		final String SQL = "SELECT * FROM GroupFormationAlgorithmRules";
		final List<Object> params = new ArrayList<>();

		final List<GroupFormationAlgoRule> rules = new ArrayList<>();

		List<HashMap<String, Object>> questionsData = db.readData(new SQLInput(SQL, params));

		if (questionsData != null) {
			questionsData.stream().forEach(row -> {
				GroupFormationAlgoRule rule = new GroupFormationAlgoRule();
				rule.setDescription((String) row.get("description"));
				rule.setName((String) row.get("name"));
				rule.setQuestionType((String) row.get("question_type"));
				rule.setRuleId((Integer) row.get("rule_id"));

				rules.add(rule);
			});
		} else {
			LOG.error("Could not Execute: " + SQL);
			return null;
		}
		return rules;
	}

	@Override
	public boolean saveAlgorithmRules(List<SurveyRuleMapper> surveyQuestionRules) throws SQLException {
		// Inserting or Updating the survey rules.
		final String INSERT_SQL = "INSERT INTO  SurveyRuleMapper (rule_id, additional_info, response_id) VALUES (?, ?, ?)";
		final String UPDATE_SQL = "UPDATE SurveyRuleMapper SET rule_id = ? , additional_info = ? WHERE response_id = ?";
		final String SELECT_SQL = "SELECT * FROM SurveyRuleMapper WHERE response_id = ? ";

		for (SurveyRuleMapper rule : surveyQuestionRules) {
			final List<Object> params = new ArrayList<>();
			params.add(rule.getRuleId());
			params.add(rule.getAdditionalInfo());
			params.add(rule.getResponseId());

			final List<Object> selectParams = new ArrayList<>();
			int rowsUpdated = 0;
			selectParams.add(rule.getResponseId());
			if (db.existsById(new SQLInput(SELECT_SQL, selectParams))) {
				LOG.debug("Updating the RULE for responseId = {}", rule.getResponseId());
				rowsUpdated = db.save(new SQLInput(UPDATE_SQL, params));
			} else {
				LOG.debug("Inserting the RULE for responseId = {}", rule.getResponseId());
				rowsUpdated = db.save(new SQLInput(INSERT_SQL, params));
			}

			if (rowsUpdated <= 0) {
				LOG.error("Failed to save the rule: {}", rule);
				return false;
			}
		}
		return true;

	}

	@Override
	public SurveyRuleMapper getSavedAlgorithmRules(Integer surveyResponseId) throws SQLException {
		final String SQL = "SELECT * FROM SurveyRuleMapper WHERE response_id= ?";
		final List<Object> params = new ArrayList<>();
		params.add(surveyResponseId);
		final SurveyRuleMapper savedRule = new SurveyRuleMapper();

		List<HashMap<String, Object>> savedRulesData = db.readData(new SQLInput(SQL, params));

		if (savedRulesData != null) {
			savedRulesData.stream().forEach(row -> {
				savedRule.setRuleId((Integer) row.get("rule_id"));
				savedRule.setResponseId((Integer) row.get("response_id"));
				savedRule.setAdditionalInfo((String) row.get("additional_info"));
			});
		} else {
			LOG.error("Could not Execute: " + SQL);
			return savedRule;
		}
		return savedRule;
	}

}
