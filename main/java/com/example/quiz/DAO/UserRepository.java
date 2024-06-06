package com.example.quiz.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.quiz.Model.user.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer> {
  @Query(value = "SELECT * FROM user_info u Where u.roles= :role", nativeQuery = true)
  List<UserInfo> findUserByRoles(@Param("role") String role);

  Optional<UserInfo> findByUsername(String username);
}
