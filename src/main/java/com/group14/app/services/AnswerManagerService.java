package com.group14.app.services;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.group14.app.repositories.IAnswerManagerRepository;

@Service
public class AnswerManagerService implements IAnswerManagerService {

	private IAnswerManagerRepository iAMR;

	public AnswerManagerService(IAnswerManagerRepository iAMR) {
		this.iAMR = iAMR;
	}

	@Override
	public void deleteData(int id) {
		try {
			iAMR.deleteAnswers(id);// deletes Text Responses
			iAMR.deleteMapping(id);// deletes Response Mapping
			iAMR.deleteQuestions(id);// deletes All Questions
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
