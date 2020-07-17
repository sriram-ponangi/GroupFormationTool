package com.group14.app.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.group14.app.models.AllQuestions;
import com.group14.app.models.Questions;
import com.group14.app.repositories.QuestionManagerRepository;
import com.group14.app.services.IAnswerManagerService;
import com.group14.app.services.IQuestionManagerService;

@Controller
public class QuestionManagerController {

	IQuestionManagerService iQMS;
	IAnswerManagerService iAMS;
	private static final Logger LOG = LoggerFactory.getLogger(QuestionManagerController.class);

	public QuestionManagerController(IQuestionManagerService iQMS, IAnswerManagerService iAMS) {
		this.iQMS = iQMS;
		this.iAMS = iAMS;
	}

	private String bannerId;

	@GetMapping("/instructor/allquestions")
	public String AssignTaGET(Model model) throws SQLException {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			bannerId = ((UserDetails) principal).getUsername();
		} else {
			bannerId = principal.toString();
		}
		LOG.info("Displaying all questions to the instructor.");
		ArrayList<AllQuestions> questionsList = iQMS.getAllQuestions(bannerId);
		model.addAttribute("questionsList", questionsList);
		model.addAttribute("question", new AllQuestions());
		return "allQuestions";
	}

	@PostMapping("/instructor/createquestion")
	public String assignStudent() {
		return "redirect:/instructor/createquestion";
	}

	@PostMapping("/instructor/deletequestion")
	public String assignta(@ModelAttribute AllQuestions question) {
		return "redirect:/instructor/deletequestion?qid=" + question.getQid();
	}

}
