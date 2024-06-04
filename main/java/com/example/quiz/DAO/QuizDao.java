package com.example.quiz.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.quiz.Model.Quiz;

@Repository
@EnableJpaRepositories
public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
