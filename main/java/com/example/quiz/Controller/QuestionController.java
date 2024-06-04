package com.example.quiz.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz.Model.Question;
import com.example.quiz.Service.QuestionServiceImplementation;

@RestController
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionServiceImplementation questionService;
	
	public QuestionController(QuestionServiceImplementation questionService) {
		super();
		this.questionService = questionService;
	}
	
	@PostMapping()
	public ResponseEntity<Question> saveQuestions(@RequestBody Question question){
		//error catching
		try {
			Question savedQuestion = questionService.saveQuestion(question);
			return new ResponseEntity<Question>(savedQuestion, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping()
	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
			return new ResponseEntity<List<Question>>(questionService.getAllQuestions(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("category/{category}")
	public List<Question> getQuestionsByCategory(@PathVariable("category") String category){
		return questionService.getQuestionsByCategory(category);
	}
}
