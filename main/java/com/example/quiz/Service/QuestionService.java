package com.example.quiz.Service;

import java.util.List;

import com.example.quiz.Model.Question;

public interface QuestionService {
	//FOR POST METHOD
	Question saveQuestion(Question question);
	//FOR GET METHOD
	List<Question> getAllQuestions();
	//GET METHOD BY CATEGORY
	List<Question> getQuestionsByCategory(String category);

}
