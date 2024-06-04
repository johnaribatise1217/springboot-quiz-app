package com.example.quiz.DAO;  

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.quiz.Model.Question;

@EnableJpaRepositories
@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
	List<Question> findQuestionsByCategory(String category);

	@Query(value = "SELECT * FROM question q Where q.category= :category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
	List<Question> findRandomQuestionsByCategory(@Param("category") String category, @Param("numQ") int numQ);
}
 