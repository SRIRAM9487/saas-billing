package com.saas.billing_system.user.infrastructure.controller.rest;

import java.util.List;

import com.saas.billing_system.shared.dto.response.ApiResponseDto;
import com.saas.billing_system.user.application.service.UserDetailsUseCase;
import com.saas.billing_system.user.application.usecase.UserDeleteUseCase;
import com.saas.billing_system.user.application.usecase.UserEmailVerificationUseCase;
import com.saas.billing_system.user.application.usecase.UserLockUseCase;
import com.saas.billing_system.user.application.usecase.UserLoginUseCase;
import com.saas.billing_system.user.application.usecase.UserRegistrationUseCase;
import com.saas.billing_system.user.application.usecase.UserUpdateUseCase;
import com.saas.billing_system.user.domain.entity.User;
import com.saas.billing_system.user.infrastructure.dto.request.UserLoginOtpVeirificationRequestDto;
import com.saas.billing_system.user.infrastructure.dto.request.UserLoginRequestDto;
import com.saas.billing_system.user.infrastructure.dto.request.UserRegisterRequestDto;
import com.saas.billing_system.user.infrastructure.dto.request.UserUpdateRequestDto;
import com.saas.billing_system.user.infrastructure.dto.response.UserDeleteResponseDto;
import com.saas.billing_system.user.infrastructure.dto.response.UserDetailsResponseDto;
import com.saas.billing_system.user.infrastructure.dto.response.UserEmailVerificationResponseDto;
import com.saas.billing_system.user.infrastructure.dto.response.UserLockResponseDto;
import com.saas.billing_system.user.infrastructure.dto.response.UserLoginResponseDto;
import com.saas.billing_system.user.infrastructure.dto.response.UserRegistrationResponseDto;
import com.saas.billing_system.user.infrastructure.dto.response.UserUpdateResponseDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  private final UserLoginUseCase loginUseCase;
  private final UserUpdateUseCase updateUseCase;
  private final UserLockUseCase lockUseCase;
  private final UserDeleteUseCase deleteUseCase;
  private final UserDetailsUseCase detailsUseCase;

  @GetMapping("/server")
  public String server() {
    return "Testing end point";
  }

  @GetMapping("/server/test")
  public String serverTest() {
    return "private Testing end point";
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
    loginUseCase.login(requestDto);
    ApiResponseDto<UserLoginResponseDto> response = ApiResponseDto.create(UserLoginResponseDto.verificationSent());
    return ResponseEntity.ok(response);
  }

  @PostMapping("/login/verify")
  public ResponseEntity<ApiResponseDto<UserLoginResponseDto>> loginVerification(
      @RequestBody UserLoginOtpVeirificationRequestDto requestDto) {
    log.debug("login Veirification Api called");
    log.trace("Request payload : {}", requestDto.toString());
    String token = loginUseCase.verifyLoginOtp(requestDto);
    ApiResponseDto<UserLoginResponseDto> response = ApiResponseDto
        .create(UserLoginResponseDto.verificationSuccess(token));
    return ResponseEntity.ok(response);
  }

  @PostMapping("/update/{userId}")
  public ResponseEntity<ApiResponseDto<UserUpdateResponseDto>> updateUser(@PathVariable("userId") String userId,
      @RequestBody(required = true) UserUpdateRequestDto requestDto) {
    log.debug("user update requested");
    log.trace("Requested payload userId : {} dto : {}", userId, requestDto.toString());
    User user = updateUseCase.updateUser(userId, requestDto);
    log.trace("Updated user : ", user.toString());
    ApiResponseDto<UserUpdateResponseDto> response = ApiResponseDto.create(UserUpdateResponseDto.fromUser(user));
    return ResponseEntity.ok(response);
  }

  @PatchMapping("/lock/{userId}")
  public ResponseEntity<ApiResponseDto<UserLockResponseDto>> lockToggleUser(@PathVariable("userId") String userId) {
    log.debug("user Lock requested");
    log.trace("Requeste payload : {}", userId);
    User user = lockUseCase.toggleLock(userId);
    ApiResponseDto<UserLockResponseDto> response = ApiResponseDto.create(UserLockResponseDto.fromUser(user));
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/delete/{userId}")
  public ResponseEntity<ApiResponseDto<UserDeleteResponseDto>> deleteUser(@PathVariable("userId") String userId) {
    log.debug("user Lock requested");
    log.trace("Requeste payload : {}", userId);
    User user = deleteUseCase.deleteUser(userId);
    ApiResponseDto<UserDeleteResponseDto> response = ApiResponseDto.create(UserDeleteResponseDto.fromUser(user));
    return ResponseEntity.ok(response);
  }

  @GetMapping("/all")
  public ResponseEntity<ApiResponseDto<List<UserDetailsResponseDto>>> getAllUser() {
    log.debug("all user api requested");
    List<User> users = detailsUseCase.getAll();
    ApiResponseDto<List<UserDetailsResponseDto>> response = ApiResponseDto
        .create(UserDetailsResponseDto.fromUser(users));
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{userId}")
  public ResponseEntity<ApiResponseDto<UserDetailsResponseDto>> getUserById(@PathVariable("userId") String userId) {
    log.debug("all user api requested");
    User user = detailsUseCase.getUserById(userId);
    ApiResponseDto<UserDetailsResponseDto> response = ApiResponseDto.create(UserDetailsResponseDto.fromUser(user));
    return ResponseEntity.ok(response);
  }
}
