package com.saas.billing_system.user.infrastructure.controller;

import com.saas.billing_system.user.application.dto.request.UserRegisterRequestDto;
import com.saas.billing_system.user.application.dto.response.UserRegistrationResponseDto;
import com.saas.billing_system.user.application.usecase.UserRegistrationUseCase;
import com.saas.billing_system.user.domain.entity.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final UserRegistrationUseCase registrationUseCase;
  private final Logger log = LoggerFactory.getLogger(UserController.class);

  @GetMapping("/server")
  public String server() {
    return "User Controller";
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody UserRegisterRequestDto registerRequestDto) {
    log.debug("register Api called");
    log.trace("Request payload : {}", registerRequestDto.toString());
    User user = registrationUseCase.register(registerRequestDto);
    log.trace("User registration successfull");
    return ResponseEntity.status(HttpStatus.CREATED).body(UserRegistrationResponseDto.fromUser(user));
  }

}
