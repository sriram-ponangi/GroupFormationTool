package com.group14.app.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.group14.app.models.NumericAnswers;

public interface IGroupFormationResultsService {

	ArrayList<List<String>> getDataforGroups(String courseId) throws SQLException;

}
