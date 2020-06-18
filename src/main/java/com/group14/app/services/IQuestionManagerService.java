package com.group14.app.services;

import java.util.ArrayList;

import com.group14.app.models.AllQuestions;

public interface IQuestionManagerService {

	boolean getRoleFromID(int id, String currentUser);
	public ArrayList<AllQuestions> getAllQuestions(String instructorId);
}
