package com.group14.app.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.group14.app.models.NumericAnswers;
import com.group14.app.models.SQLInput;
import com.group14.app.models.Survey;
import com.group14.app.models.SurveyRuleMapper;
import com.group14.app.utils.CRUDRepository;

@Repository
public class GroupFormationResultsRepository implements IGroupFormationResultsRepository {

	private CRUDRepository<SQLInput> db;

	private static final Logger LOG = LoggerFactory.getLogger(GroupFormationAlgorithmRepository.class);

	public GroupFormationResultsRepository(CRUDRepository<SQLInput> db) {
		this.db = db;
	}

	@Override
	public List<NumericAnswers> getAllStudentResponses(String courseId) throws SQLException {
		final String SQL = "select * from NumericResponses where response_id in "
				+ "(select response_id from SurveyQuestionsMapper where survey_id = "
				+ "(select survey_id from Surveys where course_id = ?))";
		final List<Object> params = new ArrayList<>();
		params.add(courseId);

		final List<NumericAnswers> responses = new ArrayList<>();

		List<HashMap<String, Object>> questionsData = db.readData(new SQLInput(SQL, params));

		if (questionsData != null) {
			questionsData.stream().forEach(row -> {
				NumericAnswers response = new NumericAnswers();
				response.setResponse_id((int) row.get("response_id"));
				response.setStudent_id((String) row.get("student_id"));
				response.setAnswer((int) row.get("answer"));

				responses.add(response);
			});
		} else {
			LOG.error("Could not Execute: " + SQL);
			return null;
		}

		return responses;
	}

	@Override
	public List<SurveyRuleMapper> getAlgorithmRuleFromCourse(String courseId) throws SQLException {
		final String SQL = "select * from SurveyRuleMapper where response_id in "
				+ "(select response_id from SurveyQuestionsMapper where survey_id = "
				+ "(select survey_id from Surveys where course_id = ?))";

		final List<Object> params = new ArrayList<>();
		params.add(courseId);

		final List<SurveyRuleMapper> rules = new ArrayList<>();

		List<HashMap<String, Object>> questionsData = db.readData(new SQLInput(SQL, params));

		if (questionsData != null) {
			questionsData.stream().forEach(row -> {
				SurveyRuleMapper rule = new SurveyRuleMapper();
				rule.setRuleId((int) row.get("rule_id"));
				rule.setResponseId((int) row.get("response_id"));
				rule.setAdditionalInfo((String) row.get("additional_info"));

				rules.add(rule);
			});
		} else {
			LOG.error("Could not Execute: " + SQL);
			return null;
		}

		return rules;
	}

	@Override
	public int getGroupSize(String courseId) throws SQLException {

		final String SQL = "select * from Surveys where course_id=?";

		final List<Object> params = new ArrayList<>();
		params.add(courseId);

		List<HashMap<String, Object>> Data = db.readData(new SQLInput(SQL, params));

		final List<Survey> size = new ArrayList<>();

		if (Data != null) {
			Data.stream().forEach(row -> {
				Survey s = new Survey();
				s.setGroupSize((int) row.get("group_size"));
				s.setCourseId((String) row.get("course_id"));
				s.setPublished((int) row.get("published"));

				size.add(s);
			});
		} else {
			LOG.error("Could not Execute: " + SQL);
			return 0;
		}

		return size.get(0).getGroupSize();

	}

}
