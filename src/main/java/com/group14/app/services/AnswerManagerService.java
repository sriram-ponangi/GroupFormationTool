package com.group14.app.services;

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
		iAMR.deleteQuestionAsTransaction(id);// deletes Questions and Corresponding Answers
	}
}
