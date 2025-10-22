package com.saas.billing_system.user.infrastructure.controller;

import com.saas.billing_system.shared.dto.response.ApiResponseDto;
import com.saas.billing_system.user.application.dto.request.UserLoginOtpVeirificationRequestDto;
import com.saas.billing_system.user.application.dto.request.UserLoginRequestDto;
import com.saas.billing_system.user.application.dto.request.UserRegisterRequestDto;
import com.saas.billing_system.user.application.dto.response.UserEmailVerificationResponseDto;
import com.saas.billing_system.user.application.dto.response.UserLoginResponseDto;
import com.saas.billing_system.user.application.dto.response.UserRegistrationResponseDto;
import com.saas.billing_system.user.application.usecase.UserEmailVerificationUseCase;
import com.saas.billing_system.user.application.usecase.UserLoginUseCase;
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
  private final UserLoginUseCase userLoginUseCase;

  @GetMapping("/server")
  public String server() {
    return "User Controller";
  }

  @PostMapping("/register")
  public ResponseEntity<ApiResponseDto<UserRegistrationResponseDto>> register(
      @RequestBody UserRegisterRequestDto registerRequestDto) {
    log.debug("register Api called");
    log.trace("Request payload : {}", registerRequestDto.toString());
    User user = registrationUseCase.register(registerRequestDto);
    log.trace("User registration successfull");
    ApiResponseDto<UserRegistrationResponseDto> response = ApiResponseDto
        .create(UserRegistrationResponseDto.fromUser(user));

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PatchMapping("/email/verify")
  public ResponseEntity<ApiResponseDto<UserEmailVerificationResponseDto>> generateEmailVerification(
      @RequestParam("email") String email) {
    log.debug("Email Verification Request Api called");
    log.trace("Request payload  email : {}", email);
    User user = emailVerificationUseCase.generateEmailVerification(email);
    log.trace("Verification token generated");
    ApiResponseDto<UserEmailVerificationResponseDto> response = ApiResponseDto
        .create(UserEmailVerificationResponseDto.generate(user.getEmail().value()));
    return ResponseEntity.ok(response);
  }

  @GetMapping("/email/verify")
  public ResponseEntity<ApiResponseDto<UserEmailVerificationResponseDto>> verifyEmail(
      @RequestParam("token") String token, @RequestParam("email") String email) {
    log.debug("Email Verify Api called");
    log.trace("Request payload token : {} email : {}", token, email);
    User user = emailVerificationUseCase.verifyEmail(email, token);
    log.trace("Verification successfull");
    ApiResponseDto<UserEmailVerificationResponseDto> response = ApiResponseDto
        .create(UserEmailVerificationResponseDto.success(user.getEmail().value()));
    return ResponseEntity.ok(response);
  }

  @PostMapping("/login")
  public ResponseEntity<ApiResponseDto<UserLoginResponseDto>> login(@RequestBody UserLoginRequestDto requestDto) {
    log.debug("login Api called");
    log.trace("Request payload : ", requestDto.toString());
    userLoginUseCase.login(requestDto);
    ApiResponseDto<UserLoginResponseDto> response = ApiResponseDto.create(UserLoginResponseDto.verificationSent());
    return ResponseEntity.ok(response);
  }

  @PostMapping("/login/verify")
  public ResponseEntity<ApiResponseDto<UserLoginResponseDto>> loginVerification(
      @RequestBody UserLoginOtpVeirificationRequestDto requestDto) {
    log.debug("login Veirification Api called");
    log.trace("Request payload : ", requestDto.toString());
    String token = userLoginUseCase.verifyLoginOtp(requestDto);
    ApiResponseDto<UserLoginResponseDto> response = ApiResponseDto
        .create(UserLoginResponseDto.verificationSuccess(token));
    return ResponseEntity.ok(response);
  }
}
