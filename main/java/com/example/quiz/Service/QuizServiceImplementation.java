package com.example.quiz.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quiz.DAO.QuestionDao;
import com.example.quiz.DAO.QuizDao;
import com.example.quiz.Model.Question;
import com.example.quiz.Model.QuestionWrapper;
import com.example.quiz.Model.Quiz;
import com.example.quiz.Model.Response;

@Service
public class QuizServiceImplementation implements QuizService {
	
	@Autowired
	private QuizDao quizDao;
	
	@Autowired
	private QuestionDao questionDao;
	
	public QuizServiceImplementation(QuizDao quizDao, QuestionDao questionDao) {
		this.quizDao = quizDao;
		this.questionDao = questionDao;
	}
	
	@Override
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		//get List of Questions from the question database/table
		List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
		
		//instantiate your Quiz object and set your title and question then save the Quiz into the Quiz repository
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDao.save(quiz);
		
		return new ResponseEntity<>("Success", HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		Optional<Quiz> quiz = quizDao.findById(id);
		List<Question> questionsFromDB = quiz.get().getQuestions();
		List<QuestionWrapper> questionsForUsers = new ArrayList<>();
		
		//we have to store data in our questionForUsers objects
		for(Question q : questionsFromDB) {
			QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(),
			q.getOption2(), q.getOption3(), q.getOption4());
			questionsForUsers.add(qw);
		}
		
		return new ResponseEntity<List<QuestionWrapper>>(questionsForUsers, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		//find the quiz by id
		Optional<Quiz>  quiz = quizDao.findById(id);
		//get the quiz questions
		List<Question> questions = quiz.get().getQuestions();
		//set number of right answers = 0
		int right = 0;
		//set question index
		int i = 0;
		//then check for each answer in our response, is it equals to the correct answer in the database
		for(Response response : responses) {
			if(response.getResponse().equals(questions.get(i).getRightAnswer()))
				right++;
			
			i++;
		}
		return new ResponseEntity<>(right, HttpStatus.OK);
	}
}
