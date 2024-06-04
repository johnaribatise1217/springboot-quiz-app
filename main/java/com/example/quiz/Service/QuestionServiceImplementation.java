package com.example.quiz.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quiz.DAO.QuestionDao;
import com.example.quiz.Model.Question;

@Service
public class QuestionServiceImplementation implements QuestionService {
	
	//repository object
	@Autowired
	private QuestionDao questionRepositoryDao;
	
	public QuestionServiceImplementation(QuestionDao questionRepositoryDao) {
		this.questionRepositoryDao = questionRepositoryDao;
	}
	
	@Override
	public Question saveQuestion(Question question) {
		return questionRepositoryDao.save(question);
	}
	
	@Override
	public List<Question> getAllQuestions(){
		return questionRepositoryDao.findAll();
	}

	@Override
	public List<Question> getQuestionsByCategory(String category){
		return questionRepositoryDao.findQuestionsByCategory(category);
	}
	
}
