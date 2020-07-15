package com.group14.app.repositories;

import java.sql.SQLException;
import java.util.ArrayList;

import com.group14.app.models.AllQuestions;

public interface IQuestionManagerRepository {

	ArrayList<AllQuestions> getAllQuestions(String instructorId);

	String FindRoleForID(int role);
	
	AllQuestions getQuestionDetailsById(String questionId);

}
