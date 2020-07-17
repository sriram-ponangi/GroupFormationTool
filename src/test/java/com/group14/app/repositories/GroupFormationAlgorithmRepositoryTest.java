package com.group14.app.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.group14.app.models.GroupFormationAlgoRule;
import com.group14.app.models.SQLInput;
import com.group14.app.models.SurveyRuleMapper;
import com.group14.app.utils.CRUDRepository;

public class GroupFormationAlgorithmRepositoryTest {

	@InjectMocks
	GroupFormationAlgorithmRepository groupFormationAlgorithmRepository;

	@Mock
	private CRUDRepository<SQLInput> mockDB;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllGroupFormationAlgoRulesTest_BasicSuccessCase() throws SQLException {
		List<HashMap<String, Object>> mockDBResponse = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> row1 = new HashMap<>();
		row1.put("description", "description");
		row1.put("name", "name");
		row1.put("question_type", "question_type");
		row1.put("rule_id", 1);
		HashMap<String, Object> row2 = new HashMap<>();
		row2.put("description", "description");
		row2.put("name", "name");
		row2.put("question_type", "question_type");
		row2.put("rule_id", 1);
		mockDBResponse.add(row1);
		mockDBResponse.add(row2);

		when(mockDB.readData(any(SQLInput.class))).thenReturn(mockDBResponse);

		List<GroupFormationAlgoRule> response = groupFormationAlgorithmRepository.getAllGroupFormationAlgoRules();

		assertEquals(2, response.size());
		assertEquals("description", response.get(0).getDescription());
		assertEquals("name", response.get(0).getName());
		assertEquals("question_type", response.get(0).getQuestionType());
		assertEquals(1, response.get(0).getRuleId());

		assertEquals("description", response.get(1).getDescription());
		assertEquals("name", response.get(1).getName());
		assertEquals("question_type", response.get(1).getQuestionType());
		assertEquals(1, response.get(1).getRuleId());

	}

	@Test
	public void getAllGroupFormationAlgoRulesTest_BasicFailedCase() throws SQLException {
		List<HashMap<String, Object>> mockDBResponse = new ArrayList<HashMap<String, Object>>();

		when(mockDB.readData(any(SQLInput.class))).thenReturn(mockDBResponse);

		List<GroupFormationAlgoRule> response = groupFormationAlgorithmRepository.getAllGroupFormationAlgoRules();

		assertEquals(0, response.size());
	}

	@Test
	public void saveAlgorithmRulesTest_BasicSuccessCase() throws SQLException {
		List<SurveyRuleMapper> mockSurveyQuestionRules = new ArrayList<>();
		mockSurveyQuestionRules.add(new SurveyRuleMapper());
		mockSurveyQuestionRules.add(new SurveyRuleMapper());
		mockSurveyQuestionRules.add(new SurveyRuleMapper());

		when(mockDB.existsById(any(SQLInput.class))).thenReturn(true).thenReturn(false).thenReturn(true);
		when(mockDB.save(any(SQLInput.class))).thenReturn(1).thenReturn(1).thenReturn(1);

		boolean response = groupFormationAlgorithmRepository.saveAlgorithmRules(mockSurveyQuestionRules);
		assertEquals(true, response);
	}

	@Test
	public void saveAlgorithmRulesTest_BasicFailedCase() throws SQLException {
		List<SurveyRuleMapper> mockSurveyQuestionRules = new ArrayList<>();
		mockSurveyQuestionRules.add(new SurveyRuleMapper());
		mockSurveyQuestionRules.add(new SurveyRuleMapper());
		mockSurveyQuestionRules.add(new SurveyRuleMapper());

		when(mockDB.existsById(any(SQLInput.class))).thenReturn(true).thenReturn(true).thenReturn(true);
		when(mockDB.save(any(SQLInput.class))).thenReturn(1).thenReturn(1).thenReturn(0);

		boolean response = groupFormationAlgorithmRepository.saveAlgorithmRules(mockSurveyQuestionRules);
		assertEquals(false, response);
	}

	@Test
	public void getSavedAlgorithmRulesTest_BasicSuccessCase() throws SQLException {
		List<HashMap<String, Object>> mockDBResponse = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> row1 = new HashMap<>();
		row1.put("additional_info", "additional_info");
		row1.put("response_id", 1);
		row1.put("rule_id", 1);
		mockDBResponse.add(row1);

		when(mockDB.readData(any(SQLInput.class))).thenReturn(mockDBResponse);

		SurveyRuleMapper response = groupFormationAlgorithmRepository.getSavedAlgorithmRules(any(Integer.class));

		assertEquals(1, response.getResponseId());
		assertEquals(1, response.getRuleId());
		assertEquals("additional_info", response.getAdditionalInfo());
	}

	@Test
	public void getSavedAlgorithmRulesTest_BasicFailedCase() throws SQLException {
		List<HashMap<String, Object>> mockDBResponse = new ArrayList<HashMap<String, Object>>();

		when(mockDB.readData(any(SQLInput.class))).thenReturn(mockDBResponse);

		SurveyRuleMapper response = groupFormationAlgorithmRepository.getSavedAlgorithmRules(any(Integer.class));

		assertEquals(0, response.getResponseId());
		assertEquals(0, response.getRuleId());
		assertNull(response.getAdditionalInfo());
	}

}
