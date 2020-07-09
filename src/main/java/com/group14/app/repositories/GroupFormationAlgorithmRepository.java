package com.group14.app.repositories;

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
	public List<GroupFormationAlgoRule> getAllGroupFormationAlgoRules() {
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
		}
		else {
			LOG.debug("Could not Execute: " + SQL);
			return null;
		}
		return rules;
	}

	@Override
	public int getSavedSurveyRuleId(String responseId) {
		final String SQL = "SELECT rule_id FROM SurveyRuleMapper WHERE response_id= ?";
		final List<Object> params = new ArrayList<>();		
		params.add(responseId);
		final GroupFormationAlgoRule rule = new GroupFormationAlgoRule();

		List<HashMap<String, Object>> rulesData = db.readData(new SQLInput(SQL, params));

		if (rulesData != null) {
			rulesData.stream().forEach(row -> {				
				rule.setRuleId((Integer) row.get("rule_id"));				
			});
		}
		else {
			LOG.debug("Could not Execute: " + SQL);
			return -1;
		}
		return rule.getRuleId();
	}

	@Override
	public void saveSurveyRules(List<SurveyRuleMapper> surveyQuestionRules) {
		final String INSERT_SQL = "INSERT INTO  SurveyRuleMapper (rule_id, additional_info, response_id) VALUES (?, ?, ?)";
		final String UPDATE_SQL = "UPDATE SurveyRuleMapper SET rule_id = ? , additional_info = ? WHERE response_id = ?";
		final String SELECT_SQL = "SELECT * FROM SurveyRuleMapper WHERE response_id = ? ";
		
		for(SurveyRuleMapper rule : surveyQuestionRules) {
			final List<Object> params = new ArrayList<>();
			params.add(rule.getRuleId());
			params.add(rule.getAdditionalInfo());
			params.add(rule.getResponseId());
			
			final List<Object> selectParams = new ArrayList<>();
			selectParams.add(rule.getResponseId());
			if(db.existsById(new SQLInput(SELECT_SQL, selectParams))) {
				LOG.info("Updating the RULE for responseId = {}",rule.getResponseId() );
				db.save(new SQLInput(UPDATE_SQL, params));
			}else {
				LOG.info("Inserting the RULE for responseId = {}",rule.getResponseId() );
				db.save(new SQLInput(INSERT_SQL, params));
			}			
		}		
	}

}
