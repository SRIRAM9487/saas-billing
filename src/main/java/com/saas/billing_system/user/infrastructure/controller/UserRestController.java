package com.saas.billing_system.user.infrastructure.controller;

import com.saas.billing_system.user.application.dto.request.UserRegisterRequestDto;
import com.saas.billing_system.user.application.dto.response.UserEmailVerificationResponseDto;
import com.saas.billing_system.user.application.dto.response.UserRegistrationResponseDto;
import com.saas.billing_system.user.application.usecase.UserEmailVerificationUseCase;
import com.saas.billing_system.user.application.usecase.UserRegistrationUseCase;
import com.saas.billing_system.user.domain.entity.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserRestController {

  private final Logger log = LoggerFactory.getLogger(UserRestController.class);
  private final UserRegistrationUseCase registrationUseCase;
  private final UserEmailVerificationUseCase emailVerificationUseCase;

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

  @GetMapping("/email/verify")
  public ResponseEntity<?> verifyEmail(@RequestParam("token") String token, @RequestParam("email") String email) {
    log.debug("Email Verify Api called");
    log.trace("Request payload token : {} email : {}", token, email);
    emailVerificationUseCase.verifyEmail(email, token);
    log.trace("Verification successfull");
    return ResponseEntity.ok(UserEmailVerificationResponseDto.success(email));
  }

  @PatchMapping("/email/verify")
  public ResponseEntity<?> generateEmailVerification(@RequestParam("email") String email) {
    log.debug("Email Verification Request Api called");
    log.trace("Request payload  email : {}", email);
    emailVerificationUseCase.generateEmailVerification(email);
    log.trace("Verification token generated");
    return ResponseEntity.ok(UserEmailVerificationResponseDto.generate(email));
  }

}
