package com.example.quiz.Model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserByRole {
  private Integer id;
  private String email;
  private String username;
  private String role;
}
