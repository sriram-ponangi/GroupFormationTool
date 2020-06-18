package com.group14.app.repositories;

public interface IChoicesRepository {

	public boolean addQuestionMultiple(String id,String title, String text, String type,String displayText, String savedAs);
	public boolean addQuestionSingle(String id,String title, String text, String type);
	
}
