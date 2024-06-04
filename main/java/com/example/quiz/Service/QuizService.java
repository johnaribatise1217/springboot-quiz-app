package com.example.quiz.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.quiz.Model.QuestionWrapper;
import com.example.quiz.Model.Response;

public interface QuizService {

	ResponseEntity<String> createQuiz(String category, int numQ, String title);

	ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id);

	ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses);

}
