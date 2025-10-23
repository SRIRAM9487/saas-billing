package com.saas.billing_system.user.application.service;

import com.saas.billing_system.user.domain.entity.User;
import com.saas.billing_system.user.domain.entity.UserImpl;
import com.saas.billing_system.user.domain.exception.UserException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
  private final UserLoginService userLoginService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.trace("Authenticating User : " + username);
    User existingUser = userLoginService.findUserById(username).orElseThrow(() -> {
      log.trace("User not found : {}", username);
      return UserException.notFound(username);
    });
    log.trace("User found : " + username);
    return new UserImpl(existingUser);
  }
}
