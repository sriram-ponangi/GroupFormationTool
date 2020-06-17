package com.group14.app.repositories;

import java.util.ArrayList;

import com.group14.app.models.Questions;

public interface IQuestionManagerRepository {
	
	public ArrayList<Questions> getAllQuestions(String instructorId);

}
