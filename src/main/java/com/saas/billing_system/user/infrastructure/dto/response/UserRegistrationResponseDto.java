package com.saas.billing_system.user.infrastructure.dto.response;

import com.saas.billing_system.user.domain.entity.User;

public record UserRegistrationResponseDto(
    String UserName,
    String email,
    String phone) {

  public static UserRegistrationResponseDto fromUser(User user) {
    return new UserRegistrationResponseDto(
        user.getUserName(),
        user.getEmail().value(),
        user.getPhone().value());
  }

}
