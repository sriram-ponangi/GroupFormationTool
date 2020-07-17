package com.group14.app.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.group14.app.models.AllQuestions;
import com.group14.app.models.SQLInput;
import com.group14.app.utils.CRUDRepository;
import com.group14.app.utils.MySQLDBOperations;

@Repository
public class ChoicesRepository implements IChoicesRepository {

	public int qid;

	private CRUDRepository<SQLInput> db;

	private static final Logger LOG = LoggerFactory.getLogger(ChoicesRepository.class);

	public ChoicesRepository(CRUDRepository<SQLInput> db) {
		this.db = db;
	}

	public boolean addQuestionMultiple(String id, String title, String text, String type, String displayText,
			String savedAs) throws SQLException {
		String SQL_INSERT_QUESTION = "insert into AllQuestions (instructor_id,title,text,type) values (?,?,?,?)";
		List<Object> params1 = new ArrayList<>();
		params1.add(id);
		params1.add(title);
		params1.add(text);
		params1.add(type);
		SQLInput sql1 = new SQLInput(SQL_INSERT_QUESTION, params1);
		System.out.println("Inserted Question");

		List<SQLInput> sqlList = new ArrayList<>();
		sqlList.add(sql1);
		db.saveTransaction(sqlList);

		String SQL_QID = "select * from AllQuestions order by created_Date desc limit 1;";
		List<Object> params3 = new ArrayList<>();

		List<HashMap<String, Object>> qData = db.readData(new SQLInput(SQL_QID, params3));
		System.out.println(qData);
		if (qData != null)
			qData.stream().forEach(row -> {

				AllQuestions ques = new AllQuestions();
				ques.setQid((int) row.get("question_id"));
				qid = ques.getQid();
				System.out.println(qid);

			});
		else {
			LOG.error("Couldn't execute SQL");
		}

		System.out.println(qid);

		List<String> saList = Arrays.asList(savedAs.split(","));
		List<String> dtList = Arrays.asList(displayText.split(","));

		List<SQLInput> sqlList1 = new ArrayList<>();

		for (int i = 0; i < dtList.size(); i++) {
			String SQL_INSERT_OPTION = "insert into MultipleChoiceQuestions (question_id,stored_as,display_text) values (?,?,?)";
			List<Object> params2 = new ArrayList<>();
			params2.add(qid);
			params2.add(saList.get(i));
			params2.add(dtList.get(i));

			SQLInput sql2 = new SQLInput(SQL_INSERT_OPTION, params2);

			sqlList1.add(sql2);

		}

		System.out.println(sqlList);
		System.out.println(sqlList1);

		db.saveTransaction(sqlList1);

		return true;
	}

	public boolean addQuestionSingle(String id, String title, String text, String type) throws SQLException {
		String SQL_INSERT_QUESTION = "insert into AllQuestions (instructor_id,title,text,type) values (?,?,?,?)";
		List<Object> params1 = new ArrayList<>();
		params1.add(id);
		params1.add(title);
		params1.add(text);
		params1.add(type);
		SQLInput sql1 = new SQLInput(SQL_INSERT_QUESTION, params1);
		System.out.println("Inserted Question");

		List<SQLInput> sqlList = new ArrayList<>();
		sqlList.add(sql1);

		System.out.println(sqlList);

		db.saveTransaction(sqlList);

		return true;
	}
}
