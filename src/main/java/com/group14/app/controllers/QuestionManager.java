package com.group14.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group14.app.models.AppUser;
import com.group14.app.models.Courses;
import com.group14.app.models.Questions;
import com.group14.app.services.IAnswerManagerService;
import com.group14.app.services.IQuestionManagerService;

@Controller
public class QuestionManager {

	IQuestionManagerService iQMS;
	IAnswerManagerService iAMS;

	public QuestionManager(IQuestionManagerService iQMS,IAnswerManagerService iAMS) {
		this.iQMS = iQMS;
		this.iAMS = iAMS;
	}

	@GetMapping("/instructor/deleteQuestion")
	public String deleteQuestion(Model model, @RequestParam(name = "qid") String qid) {

		String currentUserRole;
		int id = Integer.parseInt(qid);

		currentUserRole = AppUser.getCurrentUser().getUserId().toString();

		if (iQMS.getRoleFromID(qid, currentUserRole) == true) {
			iAMS.deleteData(id);
			return "AllQuestions";
		} else {
			return "index.html";
		}

	}

}
