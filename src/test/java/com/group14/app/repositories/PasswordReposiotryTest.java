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

import com.group14.app.models.PasswordValidatorRules;
import com.group14.app.models.SQLInput;
import com.group14.app.utils.CRUDRepository;

public class PasswordReposiotryTest {

	@InjectMocks
	private PasswordReposiotry pvr;

	@Mock
	private CRUDRepository<SQLInput> mockDB;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getActiveRulesTest_basic() throws SQLException {
		List<HashMap<String, Object>> activeRules = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> row = new HashMap<String, Object>();
		row.put("rule_id", "rule_id");
		row.put("regular_expression", "regular_expression");
		row.put("min_match_count", 1);
		row.put("max_match_count", 1);
		row.put("description", "description");
		row.put("enabled", 1);
		activeRules.add(row);

		when(mockDB.readData(any(SQLInput.class))).thenReturn(activeRules);

		List<PasswordValidatorRules> result = this.pvr.getActiveRules();

		assertEquals("rule_id", result.get(0).getRuleId());
		assertEquals("regular_expression", result.get(0).getRegEx());
		assertEquals(1, result.get(0).getMaxMatchCount());
		assertEquals(1, result.get(0).getMinMatchCount());
		assertEquals("description", result.get(0).getDescription());
		assertEquals(1, result.get(0).getEnabled());
	}

	@Test
	public void getPreviousPasswordsTest_basic() throws SQLException {
		List<HashMap<String, Object>> previousPasswords = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> row = new HashMap<String, Object>();
		row.put("password", "password");
		previousPasswords.add(row);

		when(mockDB.readData(any(SQLInput.class))).thenReturn(previousPasswords);

		List<String> result = this.pvr.getPreviousPasswords("", 1);

		assertEquals("password", result.get(0));
	}

	@Test
	public void updatePasswordTest_basic() throws SQLException {
		when(mockDB.saveTransaction(any())).thenReturn(new int[] { 1, 1 });

		int[] result = this.pvr.updatePassword("", "");
		assertEquals(1, result[0]);
		assertEquals(1, result[1]);
	}

}
