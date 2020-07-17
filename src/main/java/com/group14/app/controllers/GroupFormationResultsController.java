package com.group14.app.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group14.app.services.IGroupFormationResultsService;

@Controller
public class GroupFormationResultsController {

	IGroupFormationResultsService iGroupFormationResultsService;

	public GroupFormationResultsController(IGroupFormationResultsService iGroupFormationResultsService) {
		this.iGroupFormationResultsService = iGroupFormationResultsService;
	}

	@GetMapping("/ta/groupFormationResults")
	public String createGroups(@RequestParam(name = "courseId") String courseId, Model model) throws SQLException {

		System.out.println(courseId);

		ArrayList<List<String>> groups = iGroupFormationResultsService.getDataforGroups(courseId);
		model.addAttribute("groups", groups);

		return "DisplayGroups";

	}

}
