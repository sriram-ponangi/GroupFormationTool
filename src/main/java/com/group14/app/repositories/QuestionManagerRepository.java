package com.group14.app.repositories;
   
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.group14.app.models.Questions;
import com.group14.app.models.SQLInput;
import com.group14.app.utils.CRUDRepository;
import com.group14.app.utils.MySQLDBOperations;

@Repository
public class QuestionManagerRepository implements IQuestionManagerRepository {

	private CRUDRepository<SQLInput> db = new MySQLDBOperations();

	
	@Override
	public ArrayList<Questions> getAllQuestions(String instructorId) {
		
		String sqlQuery = "SELECT * FROM AllQuestions WHERE instructor_id = ?";
		List<Object> params = new ArrayList<>();
		params.add(instructorId);
		
		final ArrayList<Questions> rows = new ArrayList<Questions>();
		
		List<HashMap<String, Object>> questionsData = db.readData(new SQLInput(sqlQuery, params));

		if (questionsData != null)
			questionsData.stream().forEach(row -> {
				Questions question = new Questions();
				question.setQuestionId((int) row.get("question_id"));
				question.setTitle((String) row.get("title"));
				question.setText((String) row.get("text"));
				Timestamp d = (Timestamp) row.get("created_date");
				question.setCreatedDate(d);
				rows.add(question);
			});
		else {
			System.out.println("Could not Execute: "+ sqlQuery);
			return null;
		}
		return rows;
	}
	

}
