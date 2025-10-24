package com.saas.billing_system.user.application.service;

import java.util.List;

import com.saas.billing_system.user.domain.entity.User;
import com.saas.billing_system.user.domain.exception.UserException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsUseCase {

  private final UserFactory userFactory;
  private final Logger log = LoggerFactory.getLogger(UserDetailsUseCase.class);

  public List<User> getAll() {
    log.trace("Fetching all users");
    List<User> users = userFactory.findAll();
    if (users == null) {
      log.trace("No user found in the database.");
      throw UserException.notFound();
    }
    log.trace("Users fetched");
    return users;
  }

  public User getUserById(String userId) {
    log.trace("Fetching user : {}", userId);

    User user = userFactory.findUserById(userId).orElseThrow(() -> {
      log.trace("User not found : {}", userId);
      return UserException.notFound(userId);
    });

    log.trace("User : {} has been fetched ", user.getUserName());

    return user;
  }

}
