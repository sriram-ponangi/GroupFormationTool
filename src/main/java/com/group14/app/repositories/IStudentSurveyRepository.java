package com.group14.app.repositories;

import java.sql.SQLException;
import java.util.ArrayList;

import com.group14.app.models.AllQuestions;

public interface IStudentSurveyRepository {
	public ArrayList<AllQuestions> getSurvey(String cid) throws SQLException;

	public boolean isSurveyPublished(String cid) throws SQLException;

	public boolean addTextSurveyResponse(String userId, String cid, String response, String qid) throws SQLException;

	public boolean addNumericSurveyResponse(String userId, String cid, String response, String qid) throws SQLException;

	public int getSurveyID(String cid) throws SQLException;
}
