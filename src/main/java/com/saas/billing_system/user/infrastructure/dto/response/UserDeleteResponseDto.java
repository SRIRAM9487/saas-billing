package com.saas.billing_system.user.infrastructure.dto.response;

import com.saas.billing_system.user.domain.entity.User;

public record UserDeleteResponseDto(String userId) {

  public static UserDeleteResponseDto fromUser(User user) {
    return new UserDeleteResponseDto(user.getUserName());
  }
}
