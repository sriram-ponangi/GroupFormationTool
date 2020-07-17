package com.group14.app.repositories;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.group14.app.models.AllQuestions;
import com.group14.app.models.SQLInput;
import com.group14.app.utils.CRUDRepository;

public class QuestionManagerRepositoryTest {

	@InjectMocks
	private QuestionManagerRepository qMR;

	@Mock
	private CRUDRepository<SQLInput> mockDB;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void findRole() throws SQLException {
		List<HashMap<String, Object>> usersData = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> row = new HashMap<String, Object>();
		row.put("instructor_id", "B00100001");

		usersData.add(row);

		when(mockDB.readData(any(SQLInput.class))).thenReturn(usersData);

		final String id = this.qMR.FindRoleForID(1);
		assertEquals("B00100001", id);
	}

	@Test
	void showCourseTest() throws SQLException {
		ArrayList<AllQuestions> list = new ArrayList<>();
		when(this.qMR.getAllQuestions("B00100001")).thenReturn(list);
	}

	@Test
	void getQuestionDetailsByIdTest() throws SQLException {
		List<HashMap<String, Object>> mockQuestionsData = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> row1 = new HashMap<>();
		row1.put("instructor_id", "B00100007");
		row1.put("question_id", 1);
		row1.put("title", "Q1 Title");
		row1.put("text", "Q1 Text");
		row1.put("created_date", null);
		row1.put("type", "MCQS");
		mockQuestionsData.add(row1);

		when(mockDB.readData(any(SQLInput.class))).thenReturn(mockQuestionsData);

		AllQuestions response = qMR.getQuestionDetailsById("1");
		assertEquals("B00100007", response.getInstructor_id());
		assertEquals(1, response.getQid());
		assertEquals("Q1 Title", response.getTitle());
		assertEquals("Q1 Text", response.getText());
		assertEquals(null, response.getCreatedDate());
		assertEquals("MCQS", response.getType());

	}

}
