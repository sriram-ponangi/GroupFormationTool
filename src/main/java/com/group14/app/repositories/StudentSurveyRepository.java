package com.group14.app.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.group14.app.models.AllQuestions;
import com.group14.app.models.NumericResponses;
import com.group14.app.models.SQLInput;
import com.group14.app.models.TextResponses;
import com.group14.app.utils.CRUDRepository;

@Repository
public class StudentSurveyRepository implements IStudentSurveyRepository {

	private CRUDRepository<SQLInput> db;

	private static final Logger LOG = LoggerFactory.getLogger(ChoicesRepository.class);

	public StudentSurveyRepository(CRUDRepository<SQLInput> db) {
		this.db = db;
	}

	private boolean flag = false;
	private int surveyId = 0;
	private int responseId = 0;

	public ArrayList<AllQuestions> getSurvey(String cid) throws SQLException {
		final ArrayList<AllQuestions> questionList = new ArrayList<AllQuestions>();
		List<Map<String, String>> optionList = new ArrayList<>();

		List<Object> params = new ArrayList<>();
		params.add(cid);

		final String SQL = "select * from AllQuestions as aq, SurveyQuestionsMapper as sqm, Surveys as s where sqm.question_id=aq.question_id and sqm.survey_id=s.survey_id and course_id=?;";
		List<HashMap<String, Object>> surveyData = db.readData(new SQLInput(SQL, params));
		if (surveyData != null) {
			surveyData.stream().forEach(row -> {
				AllQuestions survey = new AllQuestions();
				survey.setText((String) row.get("text"));
				survey.setType((String) row.get("type"));
				survey.setQid((Integer) row.get("question_id"));
				if ((survey.getType().equals("MCQM")) || (survey.getType().equals("MCQS"))) {
					Map<String, String> map = new HashMap<>();

					List<Object> params1 = new ArrayList<>();
					params1.add(survey.getQid());

					final String SQL1 = "select stored_as,display_text from MultipleChoiceQuestions as mcq, SurveyQuestionsMapper as sqm where sqm.question_id=mcq.question_id and mcq.question_id=?;";
					List<HashMap<String, Object>> optionsData = null;
					try {
						optionsData = db.readData(new SQLInput(SQL1, params1));
					} catch (SQLException e) {

						e.printStackTrace();
					}

					if (optionsData != null) {
						optionsData.stream().forEach(row1 -> {
							map.put(row1.get("stored_as").toString(), (String) row1.get("display_text"));
						});
						survey.setOptions(map);
						optionList.add(map);
					}
				}
				questionList.add(survey);
			});
		}
		return questionList;
	}

	public boolean isSurveyPublished(String cid) throws SQLException {
		List<Object> params = new ArrayList<>();
		params.add(cid);

		final String SQL = "select * from Surveys where course_id=?;";
		List<HashMap<String, Object>> publishedData = db.readData(new SQLInput(SQL, params));
		if (publishedData.size() != 0) {
			publishedData.stream().forEach(row -> {
				int isPublished = (int) row.get("published");
				if (isPublished == 0) {
					flag = false;
				} else {
					flag = true;
				}
			});
			if (flag) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public int getSurveyID(String cid) throws SQLException {
		List<Object> params = new ArrayList<>();
		params.add(cid);
		final String SQL = "select survey_id from Surveys where course_id=?;";
		List<HashMap<String, Object>> optionsData = db.readData(new SQLInput(SQL, params));
		if (optionsData != null) {
			optionsData.stream().forEach(row -> {
				surveyId = (int) row.get("survey_id");
			});
		}
		return surveyId;
	}

	public boolean addNumericSurveyResponse(String userId, String cid, String response, String qid)
			throws SQLException {
		List<String> numResponseList = Arrays.asList(response.split(","));
		List<String> qidList = Arrays.asList(qid.split(","));
		List<SQLInput> sqlList = new ArrayList<>();

		for (int i = 0; i < numResponseList.size(); i++) {
			String SQL_QID = "select * from SurveyQuestionsMapper where question_id=?;";
			List<Object> params3 = new ArrayList<>();
			params3.add(qidList.get(i));
			List<HashMap<String, Object>> qData = db.readData(new SQLInput(SQL_QID, params3));
			if (qData != null)
				qData.stream().forEach(row -> {
					NumericResponses numericResponses = new NumericResponses();
					numericResponses.setResponseId((int) row.get("response_id"));
					responseId = numericResponses.getResponseId();
				});
			else {
				LOG.error("Couldn't execute SQL");
			}
			String SQL_NUMERICRESPONSE = "insert into NumericResponses values (?,?,?);";
			List<Object> params2 = new ArrayList<>();
			params2.add(responseId);
			params2.add(userId);
			params2.add(numResponseList.get(i));
			SQLInput sql2 = new SQLInput(SQL_NUMERICRESPONSE, params2);
			sqlList.add(sql2);
		}
		db.saveTransaction(sqlList);
		return true;
	}

	public boolean addTextSurveyResponse(String userId, String cid, String response, String qid) throws SQLException {
		List<String> textResponseList = Arrays.asList(response.split(","));
		List<String> qidList = Arrays.asList(qid.split(","));
		List<SQLInput> sqlList = new ArrayList<>();
		for (int i = 0; i < textResponseList.size(); i++) {
			final String SQL_QID = "select * from SurveyQuestionsMapper where question_id=?;";
			List<Object> params3 = new ArrayList<>();
			params3.add(qidList.get(i));

			List<HashMap<String, Object>> qData = db.readData(new SQLInput(SQL_QID, params3));
			if (qData != null)
				qData.stream().forEach(row -> {
					TextResponses textResponses = new TextResponses();
					textResponses.setResponseId((int) row.get("response_id"));
					responseId = textResponses.getResponseId();

				});
			else {
				LOG.error("Couldn't execute SQL");
			}
			final String SQL_TEXTRESPONSE = "insert into TextResponses values (?,?,?);";
			List<Object> params2 = new ArrayList<>();
			params2.add(responseId);
			params2.add(userId);
			params2.add(textResponseList.get(i));
			SQLInput sql2 = new SQLInput(SQL_TEXTRESPONSE, params2);
			sqlList.add(sql2);
		}
		db.saveTransaction(sqlList);
		return true;
	}
}
