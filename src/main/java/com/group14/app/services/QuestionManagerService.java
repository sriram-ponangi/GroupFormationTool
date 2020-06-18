package com.group14.app.services;

import org.springframework.stereotype.Service;
import com.group14.app.repositories.IQuestionManagerRepository;

@Service
public class QuestionManagerService implements IQuestionManagerService {

	private IQuestionManagerRepository iQMR;

	public QuestionManagerService(IQuestionManagerRepository iQMR) {
		this.iQMR = iQMR;
	}

	@Override
	public boolean getRoleFromID(String id, String currentUser) {
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
}
