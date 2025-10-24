package com.saas.billing_system.user.infrastructure.dto.response;

import com.saas.billing_system.user.domain.entity.User;

public record UserUpdateResponseDto(
    String userName,
    String email,
    String phoneNumber) {

  public static UserUpdateResponseDto fromUser(User user) {
    return new UserUpdateResponseDto(
        user.getUserName(),
        user.getEmail().value(),
        user.getPhone().value());
  }
}
