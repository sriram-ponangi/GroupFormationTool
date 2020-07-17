package com.group14.app.repositories;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.group14.app.models.SQLInput;
import com.group14.app.utils.CRUDRepository;
import com.group14.app.models.AllQuestions;

@Repository
public class QuestionManagerRepository implements IQuestionManagerRepository {

	private static final Logger LOG = LoggerFactory.getLogger(QuestionManagerRepository.class);

	private CRUDRepository<SQLInput> db;

	public QuestionManagerRepository(CRUDRepository<SQLInput> db) {
		this.db = db;
	}

	@Override
	public ArrayList<AllQuestions> getAllQuestions(String instructorId) throws SQLException {

		String sqlQuery = "SELECT * FROM AllQuestions WHERE instructor_id = ?";
		List<Object> params = new ArrayList<>();
		params.add(instructorId);

		final ArrayList<AllQuestions> rows = new ArrayList<AllQuestions>();

		List<HashMap<String, Object>> questionsData = db.readData(new SQLInput(sqlQuery, params));

		if (questionsData != null)
			questionsData.stream().forEach(row -> {
				AllQuestions question = new AllQuestions();
				question.setQid((int) row.get("question_id"));
				question.setTitle((String) row.get("title"));
				question.setText((String) row.get("text"));
				Timestamp d = (Timestamp) row.get("created_date");
				question.setCreatedDate(d);
				rows.add(question);
			});
		else {
			System.out.println("Could not Execute: " + sqlQuery);
			return null;
		}
		return rows;
	}

	@Override
	public String FindRoleForID(int id) throws SQLException {

		String SQL_FIND_ROLE = "select instructor_id from AllQuestions where question_id= ?";
		List<Object> params = new ArrayList<>();
		params.add(id);
		List<HashMap<String, Object>> usersData = db.readData(new SQLInput(SQL_FIND_ROLE, params));
		AllQuestions qData = new AllQuestions();
		if (usersData != null)
			usersData.stream().forEach(row -> {

				qData.setInstructor_id((String) row.get("instructor_id"));

			});
		else {
			return null;
		}
		return qData.getInstructor_id();

	}

	@Override
	public AllQuestions getQuestionDetailsById(String questionId) throws SQLException {
		final String SQL = "SELECT * FROM AllQuestions WHERE question_id = ?";
		final List<Object> params = new ArrayList<>();
		params.add(questionId);

		final AllQuestions questionInfo = new AllQuestions();

		List<HashMap<String, Object>> questionsData = db.readData(new SQLInput(SQL, params));

		if (questionsData != null) {
			questionsData.stream().forEach(row -> {
				questionInfo.setInstructor_id((String) row.get("instructor_id"));
				questionInfo.setQid((int) row.get("question_id"));
				questionInfo.setTitle((String) row.get("title"));
				questionInfo.setText((String) row.get("text"));
				Timestamp d = (Timestamp) row.get("created_date");
				questionInfo.setCreatedDate(d);
				questionInfo.setType((String) row.get("type"));

			});
		} else {
			LOG.error("Could not Execute: {}", SQL);
			return null;
		}
		return questionInfo;
	}

}
