package com.group14.app.repositories;

import java.util.ArrayList;

import com.group14.app.models.AllQuestions;

public interface IQuestionManagerRepository {
	
	public ArrayList<AllQuestions> getAllQuestions(String instructorId);
	String FindRoleForID(String role);

}
