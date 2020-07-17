package com.group14.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.group14.app.models.AllQuestions;
import com.group14.app.models.SurveyAlgorithmInfo;
import com.group14.app.models.SurveyQuestionMapper;
import com.group14.app.models.SurveyRuleMapper;
import com.group14.app.repositories.IGroupFormationAlgorithmRepository;
import com.group14.app.repositories.IQuestionManagerRepository;
import com.group14.app.repositories.ISurveyRepository;

public class GroupFormationAlgorithmServiceTest {
	@InjectMocks
	private GroupFormationAlgorithmService groupFormationAlgorithmService;

	@Mock
	private ISurveyRepository surveyRepository;

	@Mock
	private IQuestionManagerRepository questionManagerRepository;

	@Mock
	private IGroupFormationAlgorithmRepository groupFormationAlgorithmRepository;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllSurveyQuestionDetailsByIdTest() throws SQLException {
		AllQuestions question1 = new AllQuestions();
		question1.setDisplayText("question1");
		AllQuestions question2 = new AllQuestions();
		question2.setDisplayText("question2");
		AllQuestions question3 = new AllQuestions();
		question3.setDisplayText("question3");

		when(questionManagerRepository.getQuestionDetailsById(any(String.class))).thenReturn(question1)
				.thenReturn(question2).thenReturn(question3);

		List<Integer> questionIds = new ArrayList<>();
		questionIds.add(1);
		questionIds.add(2);
		questionIds.add(3);

		List<AllQuestions> response = groupFormationAlgorithmService.getAllSurveyQuestionDetailsById(questionIds);

		assertEquals(3, response.size());
		assertEquals("question1", response.get(0).getDisplayText());
		assertEquals("question2", response.get(1).getDisplayText());
		assertEquals("question3", response.get(2).getDisplayText());
	}

	@Test
	public void mapQuestionIdWithResponseIdForSurveyTest() throws SQLException {
		List<SurveyQuestionMapper> surveyQuestionsMock = new ArrayList<>();
		SurveyQuestionMapper mock1 = new SurveyQuestionMapper();
		mock1.setQuestionId(1);
		mock1.setResponseId(1);
		surveyQuestionsMock.add(mock1);
		SurveyQuestionMapper mock2 = new SurveyQuestionMapper();
		mock2.setQuestionId(2);
		mock2.setResponseId(2);
		surveyQuestionsMock.add(mock2);
		SurveyQuestionMapper mock3 = new SurveyQuestionMapper();
		mock3.setQuestionId(3);
		mock3.setResponseId(3);
		surveyQuestionsMock.add(mock3);

		Map<Integer, Integer> response = groupFormationAlgorithmService
				.mapQuestionIdWithResponseIdForSurvey(surveyQuestionsMock);

		assertEquals(1, response.get(1));
		assertEquals(2, response.get(2));
		assertEquals(3, response.get(3));
	}

	@Test
	public void saveSurveyAlgorithmTest_BasicSuccessCase() throws SQLException {
		SurveyAlgorithmInfo mockInfo = new SurveyAlgorithmInfo();
		mockInfo.setPublished(1);
		mockInfo.setSurveyId(1);

		when(surveyRepository.publishSurvey(any(Integer.class))).thenReturn(1);
		when(groupFormationAlgorithmRepository.saveAlgorithmRules(any())).thenReturn(true);
		boolean response = groupFormationAlgorithmService.saveSurveyAlgorithm(mockInfo);
		assertEquals(true, response);
	}

	@Test
	public void saveSurveyAlgorithmTest_BasicFailedCases() throws SQLException {
		SurveyAlgorithmInfo mockInfo = new SurveyAlgorithmInfo();
		mockInfo.setPublished(0);
		mockInfo.setSurveyId(1);

		// CASE-1: Test for when publishing survey fails
		when(surveyRepository.unpublishSurvey(any(Integer.class))).thenReturn(0);
		when(groupFormationAlgorithmRepository.saveAlgorithmRules(any())).thenReturn(true);
		boolean response = groupFormationAlgorithmService.saveSurveyAlgorithm(mockInfo);
		assertEquals(false, response);

		// CASE-2: Test for when saving algorithm fails
		when(surveyRepository.unpublishSurvey(any(Integer.class))).thenReturn(1);
		when(groupFormationAlgorithmRepository.saveAlgorithmRules(any())).thenReturn(false);
		boolean response2 = groupFormationAlgorithmService.saveSurveyAlgorithm(mockInfo);
		assertEquals(false, response2);
	}

	@Test
	public void mapQuestionIdWithSavedAlgorithmRulesTest() throws SQLException {
		List<SurveyQuestionMapper> mockSurveyQuestions = new ArrayList<>();
		SurveyQuestionMapper mock1 = new SurveyQuestionMapper();
		mock1.setQuestionId(1);
		mock1.setResponseId(1);
		mock1.setSurveyId(1);
		SurveyQuestionMapper mock2 = new SurveyQuestionMapper();
		mock2.setQuestionId(2);
		mock2.setResponseId(2);
		mock2.setSurveyId(1);
		mockSurveyQuestions.add(mock1);
		mockSurveyQuestions.add(mock2);

		SurveyRuleMapper mockResponse1 = new SurveyRuleMapper();
		mockResponse1.setAdditionalInfo("Additional Info Q1");
		mockResponse1.setResponseId(1);
		mockResponse1.setRuleId(2);
		SurveyRuleMapper mockResponse2 = new SurveyRuleMapper();

		when(groupFormationAlgorithmRepository.getSavedAlgorithmRules(any(Integer.class))).thenReturn(mockResponse1)
				.thenReturn(mockResponse2);

		Map<Integer, SurveyRuleMapper> response = groupFormationAlgorithmService
				.mapQuestionIdWithSavedAlgorithmRules(mockSurveyQuestions);

		assertEquals("Additional Info Q1", response.get(1).getAdditionalInfo());
		assertEquals(2, response.get(1).getRuleId());
		assertEquals(1, response.get(1).getResponseId());
		assertEquals(null, response.get(2).getAdditionalInfo());
		assertEquals(0, response.get(2).getRuleId());
		assertEquals(0, response.get(2).getResponseId());
	}

}
