package com.saas.billing_system.user.application.service;

import java.util.Optional;

import com.saas.billing_system.user.domain.entity.User;
import com.saas.billing_system.user.domain.entity.UserImpl;
import com.saas.billing_system.user.domain.vo.Email;
import com.saas.billing_system.user.infrastructure.persistence.UserRepository;

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

  private final UserRepository userRepo;
  private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.trace("Authenticating User : " + username);
    User existingUser = findUserById(username).orElseThrow();
    return new UserImpl(existingUser);
  }

  private Optional<User> findUserById(String username) {

    if (Email.isEmail(username))
      return userRepo.findByEmail_Value(username);

    return userRepo.findByUserName(username);

  }

}
