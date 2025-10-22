package com.saas.billing_system.user.application.service;

import com.saas.billing_system.user.domain.entity.User;

import org.springframework.stereotype.Service;

@Service
public class JwtService {

  public String generate(User user) {
    return "this is an jwt";
  }

  public String generate(String userID) {
    return "this is an jwt";
  }
}
