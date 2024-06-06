package com.example.quiz.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz.Model.QuestionWrapper;
import com.example.quiz.Model.Response;
import com.example.quiz.Model.user.UserByRole;
import com.example.quiz.Model.user.UserInfo;
import com.example.quiz.Service.QuizServiceImplementation;
import com.example.quiz.Service.UserService;

@RestController
@RequestMapping("/quiz")
public class QuizController {
	@Autowired
	private QuizServiceImplementation quizService;
	@Autowired
	private UserService userService;
	
	public QuizController(QuizServiceImplementation quizService) {
		super();
		this.quizService = quizService;
	}

	//can be accessed by all users
	@PostMapping("/create-user")
	public String createUser(@RequestBody UserInfo userInfo) {
		userService.createUser(userInfo);
		return "user created successfully";
	}

	//can only be accessed by ROLE_ADMIN
	@GetMapping("/user-role-user")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<UserByRole>> getAllUsersByRole(@RequestParam String role) {
		return userService.getAllUsersByRole(role);
	}
	
	 //can only be accessed by ROLE_ADMIN
	@PostMapping("create")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String Title){
		return quizService.createQuiz(category, numQ, Title);
	}
	
	//fetch questions for users based on the ID
	//can only be accessed by ROLE_USER
	@GetMapping("get/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable("id") Integer id){
		return quizService.getQuizQuestions(id);
	}
	
	//submit answers and calculate the score of the user
	//can only be accessed by ROLE_USER
	@PostMapping("submit/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Integer> submitQuiz(@PathVariable("id") Integer id, @RequestBody List<Response> responses){
		return quizService.calculateResult(id, responses);
	}
}
