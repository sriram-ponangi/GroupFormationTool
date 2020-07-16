package com.group14.app.controllers;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.group14.app.models.AllQuestions;
import com.group14.app.models.AppUser;
import com.group14.app.models.MCQuestions;
import com.group14.app.repositories.IChoicesRepository;

@Controller
public class CreateQuestionController {

	private IChoicesRepository IChoicesRepository;

	public CreateQuestionController(IChoicesRepository IChoicesRepository) {
		this.IChoicesRepository = IChoicesRepository;
	}

	@GetMapping("/instructor/createquestion")
	public String createQuestionFirst(Model model) {
		model.addAttribute("question", new AllQuestions());
		return "createQuestion";
	}

	@PostMapping("/instructor/checkAdditionalInfo")
	public String createQuestionCheck(@ModelAttribute("question") AllQuestions question) throws SQLException {
		if ((question.getType().equalsIgnoreCase("Numbers")) || (question.getType().equalsIgnoreCase("Letters"))) {
			IChoicesRepository.addQuestionSingle(AppUser.getCurrentUser().getUserId(), question.getTitle(),
					question.getText(), question.getType());
			question.setInstructor_id(AppUser.getCurrentUser().getUserId());
			return "confirmation";
		} else {
			return "additionalInfo";
		}
	}

	@PostMapping("/instructor/insertMCQ")
	public String createOption(@ModelAttribute("question") AllQuestions question) throws SQLException {
		IChoicesRepository.addQuestionMultiple(AppUser.getCurrentUser().getUserId(), question.getTitle(),
				question.getText(), question.getType(), question.getDisplayText(), question.getStoredAs());

		question.setInstructor_id(AppUser.getCurrentUser().getUserId());
		return "confirmation";
	}

}
