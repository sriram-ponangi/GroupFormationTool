package com.group14.app.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group14.app.models.AllQuestions;
import com.group14.app.models.AppUser;
import com.group14.app.models.GroupFormationAlgoRule;
import com.group14.app.models.Survey;
import com.group14.app.models.SurveyAlgorithmInfo;
import com.group14.app.models.SurveyQuestionMapper;
import com.group14.app.models.SurveyRuleMapper;
import com.group14.app.repositories.IGroupFormationAlgorithmRepository;
import com.group14.app.repositories.ISurveyRepository;
import com.group14.app.services.IGroupFormationAlgorithmService;

@Controller
public class GroupFormationAlgorithmController {

	private static final Logger LOG = LoggerFactory.getLogger(GroupFormationAlgorithmController.class);

	private IGroupFormationAlgorithmService groupFormationAlgorithmService;
	private ISurveyRepository surveyRepository;
	private IGroupFormationAlgorithmRepository groupFormationAlgorithmRepository;

	public GroupFormationAlgorithmController(IGroupFormationAlgorithmService groupFormationAlgorithmService,
			ISurveyRepository surveyRepository, IGroupFormationAlgorithmRepository groupFormationAlgorithmRepository) {
		this.groupFormationAlgorithmService = groupFormationAlgorithmService;
		this.surveyRepository = surveyRepository;
		this.groupFormationAlgorithmRepository = groupFormationAlgorithmRepository;
	}

	@GetMapping("/instructor/createGroupFormationAlgorithm")
	public String getCreateAlgorithmPage(@RequestParam String courseId, Model model) throws SQLException {

		if (AppUser.hasInstructorOrTARoleForCourse(courseId)) {

			Survey survey = surveyRepository.getSurveyInfo(courseId);

			if (survey == null || survey.getSurveyId() == 0) {
				model.addAttribute("courseId", courseId);
				model.addAttribute("errorMessage", "Sorry a survey has not been created for this course yet.");
				return "createGroupFormationAlgorithmPageError";
			}

			List<SurveyQuestionMapper> surveyQuestions = surveyRepository.getSurveyQuestionsInfo(survey.getSurveyId());

			List<Integer> surveyQuestionIdList = surveyQuestions.stream().map(e -> e.getQuestionId())
					.collect(Collectors.toList());

			List<AllQuestions> surveyQuestionInfoList = groupFormationAlgorithmService
					.getAllSurveyQuestionDetailsById(surveyQuestionIdList);

			List<GroupFormationAlgoRule> allAlgorithmRules = groupFormationAlgorithmRepository
					.getAllGroupFormationAlgoRules();

			Map<Integer, Integer> questionsResponseIds = groupFormationAlgorithmService
					.mapQuestionIdWithResponseIdForSurvey(surveyQuestions);

			Map<Integer, SurveyRuleMapper> savedRulesInfo = groupFormationAlgorithmService
					.mapQuestionIdWithSavedAlgorithmRules(surveyQuestions);

			LOG.info("Creating group formation algorithm for courseId: {}", courseId);
			model.addAttribute("survey", survey);
			model.addAttribute("questionsResponseIds", questionsResponseIds);
			model.addAttribute("savedRulesInfo", savedRulesInfo);
			model.addAttribute("surveyQuestionInfoList", surveyQuestionInfoList);
			model.addAttribute("allAlgorithmRules", allAlgorithmRules);
			model.addAttribute("algoInfo", new SurveyAlgorithmInfo());
			return "createGroupFormationAlgorithmPage";

		} else {
			LOG.info("Permission denied for Group formation algorithm.");
			model.addAttribute("courseId", courseId);
			model.addAttribute("errorMessage", "Sorry you do not have the permission to perform this action.");
			return "createGroupFormationAlgorithmPageError";
		}
	}

	@PostMapping(value = "/instructor/createGroupFormationAlgorithm")
	public String saveGroupFormationAlgorithm(@ModelAttribute SurveyAlgorithmInfo info, Model model)
			throws SQLException {
		boolean isSaveSuccessful = groupFormationAlgorithmService.saveSurveyAlgorithm(info);
		if (isSaveSuccessful) {
			model.addAttribute("courseId", info.getCourseId());

			if (info.getAction().equalsIgnoreCase("RUN")) {
				if (info.getPublished() == 1) {
				    LOG.info("Applying the GROUP FORMATION ALGORITHM");
					return "redirect:/ta/groupFormationResults?courseId=" + info.getCourseId();
				} else {
					model.addAttribute("warnMessage",
							"Survey is not published so cannot run the algorithm, but saving the algorithm is completed.");
					return "createGroupFormationAlgorithmPageWarn";
				}
				
			}
			model.addAttribute("successMessage", "Saving the algorithm completed.");
			return "createGroupFormationAlgorithmPageSuccess";
		} else {
			model.addAttribute("courseId", info.getCourseId());
			model.addAttribute("errorMessage",
					"Was not able to save some of the rules. Sorry, cannot run the algorithm now. Please try again later");
			return "createGroupFormationAlgorithmPageError";
		}

	}

}
