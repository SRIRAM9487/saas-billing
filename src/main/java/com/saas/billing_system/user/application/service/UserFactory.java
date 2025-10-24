package com.saas.billing_system.user.application.service;

import java.util.List;
import java.util.Optional;

import com.saas.billing_system.user.domain.entity.User;
import com.saas.billing_system.user.domain.vo.Email;
import com.saas.billing_system.user.domain.vo.UserId;
import com.saas.billing_system.user.infrastructure.persistence.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserFactory {

  private final UserRepository userRepo;
  private final Logger log = LoggerFactory.getLogger(UserFactory.class);

  public Optional<User> findUserById(String username) {
    log.trace("Find User By Id : {}", username);
    if (Email.isEmail(username)) {
      return userRepo.findByEmail_Value(username);
    }
    if (username
        .matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$")) {
      return userRepo.findById(UserId.fromString(username));
    }
    return userRepo.findByUserName(username);
  }

  public List<User> findAll() {
    log.trace("Find all users.");
    return userRepo.findAll();
  }
}
