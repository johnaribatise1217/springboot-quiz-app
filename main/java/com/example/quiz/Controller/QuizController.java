package com.example.quiz.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz.Model.QuestionWrapper;
import com.example.quiz.Model.Response;
import com.example.quiz.Service.QuizServiceImplementation;

@RestController
@RequestMapping("/quiz")
public class QuizController {
	@Autowired
	private QuizServiceImplementation quizService;
	
	 public QuizController(QuizServiceImplementation quizService) {
		 super();
		 this.quizService = quizService;
	 }
	
	 //can only be accessed by ROLE_ADMIN
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String Title){
		return quizService.createQuiz(category, numQ, Title);
	}
	
	//fetch questions for users based on the ID
	//can only be accessed by ROLE_USER
	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable("id") Integer id){
		return quizService.getQuizQuestions(id);
	}
	
	//submit answers and calculate the score of the user
	//can only be accessed by ROLE_USER
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable("id") Integer id, @RequestBody List<Response> responses){
		return quizService.calculateResult(id, responses);
	}
}
