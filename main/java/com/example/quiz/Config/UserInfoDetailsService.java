package com.example.quiz.Config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.quiz.DAO.UserRepository;
import com.example.quiz.Model.user.UserInfo;

@Component
public class UserInfoDetailsService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //find the user with the username from the userRepository
    Optional<UserInfo> userInfo = userRepository.findByUsername(username);
    //map the userInfo object to the UserInfoDetails class
    return userInfo.map(UserInfoDetails :: new).orElseThrow(
      () -> new UsernameNotFoundException(username + " was not found")
    );
  }
}
