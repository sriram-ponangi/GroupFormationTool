
package com.group14.app.repositories;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.group14.app.models.Questions;

public class QuestionManagerRepositoryTest {
	
	private QuestionManagerRepository questionManagerRepository = mock(QuestionManagerRepository.class);
	
	ArrayList<Questions> list=new ArrayList<>();
	Questions questions = new Questions();
	
	@Test
	void showCourseTest() {
		
		when(this.questionManagerRepository.getAllQuestions("B00100001")).thenReturn(list);
	}	
}
