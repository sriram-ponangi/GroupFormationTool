package com.group14.app.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.group14.app.models.AllQuestions;
import com.group14.app.models.CourseRoleMapper;
import com.group14.app.models.Survey;
import com.group14.app.models.SurveyQuestionMapper;
import com.group14.app.repositories.PasswordReposiotry;
import com.group14.app.services.ICourseRoleMapperService;
import com.group14.app.services.IQuestionManagerService;
import com.group14.app.services.ISurveyQuestionMapperService;
import com.group14.app.services.ISurveyService;

@Controller
public class SurveyController {

	IQuestionManagerService iQuestionManagerService;
	ICourseRoleMapperService iCourseRoleMapperService;
	ISurveyService iSurveyService;
	ISurveyQuestionMapperService iSurveyQuestionMapperService;
	private static final Logger LOG = LoggerFactory.getLogger(SurveyController.class);

	public SurveyController(IQuestionManagerService iQuestionManagerService,
			ICourseRoleMapperService iCourseRoleMapperService, ISurveyService iSurveyService,
			ISurveyQuestionMapperService iSurveyQuestionMapperService) {
		this.iQuestionManagerService = iQuestionManagerService;
		this.iCourseRoleMapperService = iCourseRoleMapperService;
		this.iSurveyService = iSurveyService;
		this.iSurveyQuestionMapperService = iSurveyQuestionMapperService;
	}

	private Survey surveyData = new Survey();
	private CourseRoleMapper courseRoleMapper = new CourseRoleMapper();
	private ArrayList<AllQuestions> questionsList = new ArrayList<AllQuestions>();

	@RequestMapping(value = { "/ta/createSurvey", "/instructor/createSurvey" }, method = RequestMethod.GET)
	public String createSurveyTA(@RequestParam(name = "courseId") String courseId, Model model) throws SQLException {

		ArrayList<CourseRoleMapper> roles = iCourseRoleMapperService.getInstructorId(courseId);
		courseRoleMapper.setCourse_id(courseId);
		for (int i = 0; i < roles.size(); i++) {
			if (roles.get(i).getRole_id().equals("INSTRUCTOR")) {
				courseRoleMapper.setUser_id(roles.get(i).getUser_id());
			}
		}
		surveyData.setSurveyId(iSurveyService.getSurveyId(courseId));
		if (surveyData.getSurveyId() == 0) {
			LOG.info("There were no previous surveys.");
			Survey survey = new Survey();
			survey.setCourseId(courseId);
			survey.setGroupSize(3);
			survey.setPublished(0);

			int rowUpdated = iSurveyService.createSurvey(survey);

			questionsList = iQuestionManagerService.getAllQuestions(courseRoleMapper.getUser_id());
			model.addAttribute("questionsList", questionsList);
			model.addAttribute("questions", new ArrayList<AllQuestions>());
			return "createSurvey";
		} else {
			LOG.info("Loading the previous survey.");
			ArrayList<AllQuestions> questionsList = iQuestionManagerService
					.getAllQuestions(courseRoleMapper.getUser_id());
			ArrayList<SurveyQuestionMapper> surveyQuestions = iSurveyQuestionMapperService
					.getSurveyQuestions(surveyData.getSurveyId());

			for (int i = 0; i < surveyQuestions.size(); i++) {
				for (int j = 0; j < questionsList.size(); j++) {
					if (surveyQuestions.get(i).getQuestionId() == questionsList.get(j).getQid()) {
						questionsList.get(j).setSelected(true);
					}
				}
			}
			model.addAttribute("questionsList", questionsList);
			model.addAttribute("questions", new ArrayList<AllQuestions>());
			return "createSurvey";
		}
	}

	@RequestMapping(value = { "/instructor/createSurvey",
			"/ta/createSurvey" }, params = "save", method = RequestMethod.POST)
	public String saveSurveyI(@RequestParam("selectedQuestions") String[] selectedQuestions, Model model)
			throws SQLException {

		ArrayList<SurveyQuestionMapper> surveyQuestions = iSurveyQuestionMapperService
				.getSurveyQuestions(surveyData.getSurveyId());

		for (int i = 0; i < surveyQuestions.size(); i++) {
			int[] rowsUpdated = iSurveyQuestionMapperService
					.deleteSurveyQuestion(surveyQuestions.get(i).getResponseId());
		}
		LOG.info("Previous questions removed.");

		for (int i = 0; i < selectedQuestions.length; i++) {
			int rowsUpdated = iSurveyQuestionMapperService.addSurveyQuestion(surveyData.getSurveyId(),
					Integer.parseInt(selectedQuestions[i]));
		}
		LOG.info("New questions added.");
		model.addAttribute("successMessage", "Saving questions completed.");
		return "createGroupFormationAlgorithmPageSuccess";
	}

	@RequestMapping(value = { "/instructor/createSurvey",
			"/ta/createSurvey" }, params = "next", method = RequestMethod.POST)
	public String saveSurveyINext(@RequestParam("selectedQuestions") String[] selectedQuestions) throws SQLException {

		ArrayList<SurveyQuestionMapper> surveyQuestions = iSurveyQuestionMapperService
				.getSurveyQuestions(surveyData.getSurveyId());

		for (int i = 0; i < surveyQuestions.size(); i++) {
			int[] rowsUpdated = iSurveyQuestionMapperService
					.deleteSurveyQuestion(surveyQuestions.get(i).getResponseId());
		}
		LOG.info("Previous questions removed.");

		for (int i = 0; i < selectedQuestions.length; i++) {
			int rowsUpdated = iSurveyQuestionMapperService.addSurveyQuestion(surveyData.getSurveyId(),
					Integer.parseInt(selectedQuestions[i]));
			if (rowsUpdated == 0) {
			}
		}
		LOG.info("New questions added.");

		return "redirect:/instructor/createGroupFormationAlgorithm?courseId=" + courseRoleMapper.getCourse_id();
	}
}
