package com.saas.billing_system.user.application.usecase;

import com.saas.billing_system.user.application.dto.request.UserLoginOtpVeirificationRequestDto;
import com.saas.billing_system.user.application.dto.request.UserLoginRequestDto;
import com.saas.billing_system.user.application.event.LoginVerificationEvent;
import com.saas.billing_system.user.application.service.JwtService;
import com.saas.billing_system.user.application.service.UserFactory;
import com.saas.billing_system.user.application.service.UserLoginService;
import com.saas.billing_system.user.domain.entity.User;
import com.saas.billing_system.user.domain.exception.UserException;
import com.saas.billing_system.user.infrastructure.persistence.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserLoginUseCase {

  private final Logger log = LoggerFactory.getLogger(UserLoginUseCase.class);
  private final ApplicationEventPublisher applicationEventPublisher;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final UserLoginService userLoginService;
  private final UserFactory userFactory;

  public String login(UserLoginRequestDto requestDto) {

    log.debug("Authenticating the user : {}", requestDto.toString());

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(requestDto.userId(), requestDto.password()));
    log.trace("User Authentication : {}", authentication.isAuthenticated());

    if (!authentication.isAuthenticated())
      throw UserException.authenticationFailed(requestDto.userId());

    User user = userFactory.findUserById(requestDto.userId()).orElseThrow(() -> {
      return UserException.notFound(requestDto.userId());
    });

    log.trace("User  Verified : {}", user.isVerified());
    if (!user.isVerified())
      throw UserException.emailNotVerified(user.getEmail().value());

    log.trace("Login otp sent to the user Email : {}", user.getEmail().value());
    applicationEventPublisher.publishEvent(new LoginVerificationEvent(this, user.getEmail().value(),
        userLoginService.generateLoginOtp(user.getEmail().value())));

    return "otp sent";
  }

  public String verifyLoginOtp(UserLoginOtpVeirificationRequestDto requestDto) {
    log.debug("Otp verification requested");
    log.trace("for user : {}", requestDto.email());
    if (!userLoginService.verifyOtp(requestDto.email(), requestDto.otp())) {
      log.trace("Invalid otp try again");
      throw UserException.invalidOtp(requestDto.email());
    }
    User user = userFactory.findUserById(requestDto.email()).orElseThrow(() -> {
      return UserException.notFound(requestDto.email());
    });
    String jwt = jwtService.generate(user);
    log.trace("jwt generated for user {}", requestDto.email());
    return jwt;
  }

}
