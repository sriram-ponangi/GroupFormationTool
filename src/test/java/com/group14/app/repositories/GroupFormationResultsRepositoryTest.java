package com.group14.app.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.group14.app.models.NumericAnswers;
import com.group14.app.models.SQLInput;
import com.group14.app.models.SurveyRuleMapper;
import com.group14.app.utils.CRUDRepository;

public class GroupFormationResultsRepositoryTest {

	@InjectMocks
	private GroupFormationResultsRepository gFRR;

	@Mock
	private CRUDRepository<SQLInput> mockDB;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void getAllResponses() throws SQLException {

		List<HashMap<String, Object>> responseData = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> row = new HashMap<String, Object>();
		row.put("response_id", 1);
		row.put("student_id", "B00100004");
		row.put("answer", 1);

		responseData.add(row);

		when(mockDB.readData(any(SQLInput.class))).thenReturn(responseData);

		final List<NumericAnswers> data = this.gFRR.getAllStudentResponses("CSCI1001");

		assertEquals(1, data.get(0).getResponse_id());
		assertEquals("B00100004", data.get(0).getStudent_id());
		assertEquals(1, data.get(0).getAnswer());

	}
	
	@Test
	void getSurveyRules() throws SQLException {
		
		List<HashMap<String, Object>> ruleData = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> row = new HashMap<String, Object>();
		row.put("response_id", 1);
		row.put("rule_id", 1);
		row.put("additional_info", "");

		ruleData.add(row);

		when(mockDB.readData(any(SQLInput.class))).thenReturn(ruleData);
		
		final List<SurveyRuleMapper> data = this.gFRR.getAlgorithmRuleFromCourse("CSCI1001");

		assertEquals(1, data.get(0).getResponseId());
		assertEquals(1, data.get(0).getRuleId());
		assertEquals("", data.get(0).getAdditionalInfo());
	}

}
