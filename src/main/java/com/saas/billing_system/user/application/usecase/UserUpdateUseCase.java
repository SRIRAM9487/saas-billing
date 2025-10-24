package com.saas.billing_system.user.application.usecase;

import com.saas.billing_system.user.application.dto.request.UserUpdateRequestDto;
import com.saas.billing_system.user.application.service.UserLoginService;
import com.saas.billing_system.user.domain.entity.User;
import com.saas.billing_system.user.domain.exception.UserException;
import com.saas.billing_system.user.infrastructure.persistence.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserUpdateUseCase {

  private final Logger log = LoggerFactory.getLogger(UserUpdateUseCase.class);
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder encoder;
  private final UserLoginService userLoginService;

  public User updateUser(String userId, UserUpdateRequestDto requestDto) {

    log.trace("updating user : ", userId);

    User user = userLoginService.findUserById(userId).orElseThrow(() -> {
      log.trace("user not found : {}", userId);
      return UserException.notFound(userId);
    });

    log.trace("UserName updated : {}");
    user.setUserName(requestDto.userName());

    if (!requestDto.email().equals(user.getEmail().value())) {
      log.trace("Email Updated");
      user.updateEmail(requestDto.email());
    }

    if (!requestDto.phoneNumber().equals(user.getPhone().value())) {
      log.trace("PhoneNumber Updated");
      user.updatePhone(requestDto.phoneNumber());
    }

    if (requestDto.password() != null && !requestDto.password().isBlank()) {
      log.trace("Password Updated");
      user.updatePassword(encoder.encode(requestDto.password()));
    }

    User updateUser = userRepository.save(user);
    log.trace("User Updated successfully");

    return updateUser;
  }
}
