package com.group14.app.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.group14.app.models.NumericAnswers;
import com.group14.app.models.SurveyRuleMapper;
import com.group14.app.repositories.IGroupFormationResultsRepository;

@Service
public class GroupFormationResultsService implements IGroupFormationResultsService {

	private IGroupFormationResultsRepository iGroupFormationResultsRepository;
	public Hashtable<Integer, List<Integer>> responseData = new Hashtable<Integer, List<Integer>>();
	public Hashtable<Integer, Integer> ruleData = new Hashtable<Integer, Integer>();
	public Map<String, List<NumericAnswers>> studentData = new HashMap<String, List<NumericAnswers>>();
	public List<Integer> similarCluster1;
	public List<Integer> dissimilarCluster1;
	public List<Integer> similarCluster2;
	public List<Integer> dissimilarCluster2;
	public List<Integer> similarCluster3;
	public List<Integer> dissimilarCluster3;
	public List<Integer> similarCluster4;
	public List<Integer> dissimilarCluster4;
	public List<Integer> similarCluster7;
	public List<Integer> dissimilarCluster7;
	public List<Integer> similarCluster8;
	public List<Integer> dissimilarCluster8;
	Random random = new Random();
	public List<String> group1 = new ArrayList<String>();
	public List<String> group2 = new ArrayList<String>();
	public ArrayList<List<Integer>> mainCluster = new ArrayList<List<Integer>>();

	public GroupFormationResultsService(IGroupFormationResultsRepository iGroupFormationResultsRepository) {
		this.iGroupFormationResultsRepository = iGroupFormationResultsRepository;
	}

	@Override
	public ArrayList<List<String>> getDataforGroups(String courseId) throws SQLException {

		List<NumericAnswers> responses = iGroupFormationResultsRepository.getAllStudentResponses(courseId);
		List<SurveyRuleMapper> rules = iGroupFormationResultsRepository.getAlgorithmRuleFromCourse(courseId);
		int groupSize = iGroupFormationResultsRepository.getGroupSize(courseId);
		ArrayList<List<Integer>> groupsBySimilarity = new ArrayList<List<Integer>>();
		ArrayList<List<String>> formedGroups = new ArrayList<List<String>>();
		Map<Integer, List<NumericAnswers>> groupData = new HashMap<Integer, List<NumericAnswers>>();

		studentData = responses.stream().collect(Collectors.groupingBy(NumericAnswers::getStudent_id));
		groupData = responses.stream().collect(Collectors.groupingBy(NumericAnswers::getResponse_id));

		Set<Integer> keys = groupData.keySet();
		Set<String> sKey = studentData.keySet();

		for (Integer key : keys) {

			List<Integer> answers = new ArrayList<Integer>();
			for (int i = 0; i < groupData.get(key).size(); i++) {

				answers.add(groupData.get(key).get(i).getAnswer());
			}
			responseData.put(key, answers);
		}

		System.out.println(groupSize);
		for (int i = 0; i < groupSize * 2; i++) {
			for (String key : sKey) {
				int rInteger = random.nextInt(6);
				if (rInteger % 2 == 0) {
					if (group1.size() < groupSize) {
						if (group1.contains(studentData.get(key).get(i).getStudent_id()) == false) {
							group1.add(studentData.get(key).get(i).getStudent_id());
						}
					}
				} else {
					if (group2.size() < groupSize) {
						if (group2.contains(studentData.get(key).get(i).getStudent_id()) == false) {
							group2.add(studentData.get(key).get(i).getStudent_id());
						}
					}
				}

			}
		}

		formedGroups.add(group1);
		formedGroups.add(group2);

		for (Integer key : keys) {
			for (int i = 0; i < rules.size(); i++) {
				if (key == rules.get(i).getResponseId()) {

					groupsBySimilarity = findGroup(rules, key, i);
				}
			}

		}

		return formedGroups;

	}

	private ArrayList<List<Integer>> findGroup(List<SurveyRuleMapper> rules, Integer key, int index) {
		for (int i = 0; i < responseData.get(key).size(); i++) {
			similarCluster1 = new ArrayList<Integer>();
			dissimilarCluster1 = new ArrayList<Integer>();
			similarCluster2 = new ArrayList<Integer>();
			dissimilarCluster2 = new ArrayList<Integer>();
			similarCluster3 = new ArrayList<Integer>();
			dissimilarCluster3 = new ArrayList<Integer>();
			similarCluster4 = new ArrayList<Integer>();
			dissimilarCluster4 = new ArrayList<Integer>();
			similarCluster7 = new ArrayList<Integer>();
			dissimilarCluster7 = new ArrayList<Integer>();
			similarCluster8 = new ArrayList<Integer>();
			dissimilarCluster8 = new ArrayList<Integer>();
			for (int j = 0; j < responseData.get(key).size(); j++) {

				if (rules.get(index).getRuleId() == 1) {
					similarMCQGroup(key, j, i);
				} else if (rules.get(index).getRuleId() == 2) {
					dissimilarMCQGroup(key, j, i);
				} else if (rules.get(index).getRuleId() == 3) {
					similarMultiMCQGroup(key, j, i);
				} else if (rules.get(index).getRuleId() == 4) {
					dissimilarMultiMCQGroup(key, j, i);
				} else if (rules.get(index).getRuleId() == 7) {
					similarNumericGroup(key, j, i);
				} else if (rules.get(index).getRuleId() == 8) {
					dissimilarNumericGroup(key, j, i);
				}

			}

		}
		if (similarCluster1.size() != 0) {
			mainCluster.add(similarCluster1);
		}
		if (dissimilarCluster1.size() != 0) {
			mainCluster.add(dissimilarCluster1);
		}
		if (similarCluster2.size() != 0) {
			mainCluster.add(similarCluster2);
		}
		if (dissimilarCluster2.size() != 0) {
			mainCluster.add(dissimilarCluster2);
		}
		if (similarCluster3.size() != 0) {
			mainCluster.add(similarCluster3);
		}
		if (dissimilarCluster3.size() != 0) {
			mainCluster.add(dissimilarCluster3);
		}
		if (similarCluster4.size() != 0) {
			mainCluster.add(similarCluster4);
		}
		if (dissimilarCluster4.size() != 0) {
			mainCluster.add(dissimilarCluster4);
		}
		if (similarCluster7.size() != 0) {
			mainCluster.add(similarCluster7);
		}
		if (dissimilarCluster7.size() != 0) {
			mainCluster.add(dissimilarCluster7);
		}
		if (similarCluster8.size() != 0) {
			mainCluster.add(similarCluster8);
		}
		if (dissimilarCluster8.size() != 0) {
			mainCluster.add(dissimilarCluster8);
		}

		return mainCluster;

	}

	private void dissimilarNumericGroup(Integer key, int j, int i) {

		if (j != i) {
			if (responseData.get(key).get(i) != responseData.get(key).get(j)) {
				similarCluster8.add(responseData.get(key).get(j));
			} else {
				dissimilarCluster8.add(responseData.get(key).get(j));
			}
		} else {
			dissimilarCluster8.add(responseData.get(key).get(i));
		}

	}

	private void similarNumericGroup(Integer key, int j, int i) {
		if (j != i) {
			if (responseData.get(key).get(i) == responseData.get(key).get(j)) {
				similarCluster7.add(responseData.get(key).get(j));
			} else {
				dissimilarCluster7.add(responseData.get(key).get(j));
			}
		} else {
			similarCluster7.add(responseData.get(key).get(i));
		}

	}

	private void dissimilarMultiMCQGroup(Integer key, int j, int i) {

		if (j != i) {
			if (responseData.get(key).get(i) != responseData.get(key).get(j)) {
				similarCluster4.add(responseData.get(key).get(j));
			} else {
				dissimilarCluster4.add(responseData.get(key).get(j));
			}
		} else {
			dissimilarCluster4.add(responseData.get(key).get(i));
		}

	}

	private void similarMultiMCQGroup(Integer key, int j, int i) {

		if (j != i) {
			if (responseData.get(key).get(i) == responseData.get(key).get(j)) {
				similarCluster3.add(responseData.get(key).get(j));
			} else {
				dissimilarCluster3.add(responseData.get(key).get(j));
			}
		} else {
			similarCluster3.add(responseData.get(key).get(i));
		}

	}

	private void dissimilarMCQGroup(Integer key, int j, int i) {
		if (j != i) {
			if (responseData.get(key).get(i) != responseData.get(key).get(j)) {
				similarCluster2.add(responseData.get(key).get(j));
			} else {
				dissimilarCluster2.add(responseData.get(key).get(j));
			}
		} else {
			dissimilarCluster2.add(responseData.get(key).get(i));
		}

	}

	private void similarMCQGroup(Integer key, int j, int i) {
		if (j != i) {
			if (responseData.get(key).get(i) == responseData.get(key).get(j)) {
				similarCluster1.add(responseData.get(key).get(j));
			} else {
				dissimilarCluster1.add(responseData.get(key).get(j));
			}
		} else {
			similarCluster1.add(responseData.get(key).get(i));
		}
	}

}
