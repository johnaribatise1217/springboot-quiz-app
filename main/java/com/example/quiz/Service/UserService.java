package com.example.quiz.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.quiz.DAO.UserRepository;
import com.example.quiz.Model.user.InfoWrapper;
import com.example.quiz.Model.user.UserByRole;
import com.example.quiz.Model.user.UserInfo;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PasswordEncoder encoder;

  //create user method
  public InfoWrapper createUser(UserInfo userInfo){
    userInfo.setPassword(encoder.encode(userInfo.getPassword()));
    UserInfo saveUser = userRepository.save(userInfo);

    InfoWrapper userResponse = new InfoWrapper(
      saveUser.getId(),
      saveUser.getUsername(),
      saveUser.getEmail(),
      saveUser.getRoles()
    );

    return userResponse;
  }

  public ResponseEntity<List<UserByRole>> getAllUsersByRole(String role){
    //we have to get all the users with role = "ROLE_USER"
    List<UserInfo> roleUsers = userRepository.findUserByRoles(role);
    List<UserByRole> usersWithRole = new ArrayList<>();

    //then store all the users in roleUsers into usersWithRole (we are not mapping the user password)
    for(UserInfo u : roleUsers){
      UserByRole user = new UserByRole(
        u.getId(),
        u.getEmail(),
        u.getUsername(),
        u.getRoles()
      );
      usersWithRole.add(user);
    }

    return new ResponseEntity<List<UserByRole>>(usersWithRole, HttpStatus.OK);
  }
}
