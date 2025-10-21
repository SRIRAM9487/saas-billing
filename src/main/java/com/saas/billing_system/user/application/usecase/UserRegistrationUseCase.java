package com.saas.billing_system.user.application.usecase;

import com.saas.billing_system.user.application.dto.request.UserRegisterRequestDto;
import com.saas.billing_system.user.domain.entity.User;
import com.saas.billing_system.user.infrastructure.persistence.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRegistrationUseCase {

  private final UserRepository userRepository;
  private final Logger log = LoggerFactory.getLogger(UserRegistrationUseCase.class);
  private final ApplicationEventPublisher applicationEventPublisher;

  public User register(UserRegisterRequestDto userRegisterRequestDto) {
    log.debug("Executing User Registration usecase");
    log.trace("User Register Dto : {}", userRegisterRequestDto);
    User newUser = UserRegisterRequestDto.toUser(userRegisterRequestDto);
    User savedUser = userRepository.save(newUser);
    log.trace("User Registration successfull {}", savedUser);
    
    //applicationEventPublisher.publishEvent(new EmailVerificationEvent(this, savedUser.getEmail().value(),savedUser.getUserName()));
    return savedUser;
  }

}
