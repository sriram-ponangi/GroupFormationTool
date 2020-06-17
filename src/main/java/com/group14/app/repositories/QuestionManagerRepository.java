package com.group14.app.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.group14.app.models.AllQuestions;
import com.group14.app.models.SQLInput;
import com.group14.app.utils.CRUDRepository;

@Repository
public class QuestionManagerRepository implements IQuestionManagerRepository {

	private CRUDRepository<SQLInput> db;

	public QuestionManagerRepository(CRUDRepository<SQLInput> db) {
		this.db = db;
	}

	@Override
	public String FindRoleForID(String id) {

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

}
