package com.group14.app.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.group14.app.models.AllQuestions;
import com.group14.app.models.NumericResponses;
import com.group14.app.models.TextResponses;
import com.group14.app.models.UserPrincipal;
import com.group14.app.repositories.IStudentSurveyRepository;

@Controller
public class StudentSurveyController {

	private IStudentSurveyRepository IStudentSurveyRepository;
	private static final Logger LOG = LoggerFactory.getLogger(StudentSurveyController.class);

	public StudentSurveyController(IStudentSurveyRepository IStudentSurveyRepository) {
		this.IStudentSurveyRepository = IStudentSurveyRepository;
	}

	@PostMapping("/submitSurvey/{courseId}")
	public String responseSubmit(@PathVariable("courseId") String courseId, @ModelAttribute NumericResponses answernum,
			@ModelAttribute TextResponses answertext) throws SQLException {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserPrincipal userPrincipal = (UserPrincipal) principal;
		String userId = userPrincipal.getUser().getUserId();
		LOG.info("Submitting survey for courseId: {}", courseId);
		IStudentSurveyRepository.addNumericSurveyResponse(userId, courseId, answernum.getNumanswer(),
				answernum.getQid());
		IStudentSurveyRepository.addTextSurveyResponse(userId, courseId, answertext.getTextanswer(),
				answertext.getTqid());
		return "surveysubmitted";
	}

	@GetMapping("/student/survey/{courseId}")
	public String showSurvey(@PathVariable("courseId") String courseId, Model model) throws SQLException {
		model.addAttribute("courseId", courseId);
		model.addAttribute("answernum", new NumericResponses());
		model.addAttribute("answertext", new TextResponses());
		LOG.info("Displaying survey page to student for courseId: {}", courseId);
		if (IStudentSurveyRepository.isSurveyPublished(courseId)) {
			ArrayList<AllQuestions> surveyList = IStudentSurveyRepository.getSurvey(courseId);
			model.addAttribute("surveyList", surveyList);
			return "surveys";
		} else {
			return "surveyNotPublished";
		}
	}
}
