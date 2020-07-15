package com.group14.app.services;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.group14.app.models.AllQuestions;
import com.group14.app.repositories.IQuestionManagerRepository;

@Service
public class QuestionManagerService implements IQuestionManagerService {

	private IQuestionManagerRepository iQMR;

	public QuestionManagerService(IQuestionManagerRepository iQMR) {
		this.iQMR = iQMR;
	}

	@Override
	public boolean getRoleFromID(int id, String currentUser) throws SQLException {
		String role;

		role = iQMR.FindRoleForID(id);

		if (role == null) {
			return false;
		} else {

			if (role.equals(currentUser)) {
				return true;
			} else {
				return false;
			}
		}

	}

	@Override
	public ArrayList<AllQuestions> getAllQuestions(String instructorId) throws SQLException {
		ArrayList<AllQuestions> questions = new ArrayList<AllQuestions>();

		questions = iQMR.getAllQuestions(instructorId);

		return questions;
	}
}
