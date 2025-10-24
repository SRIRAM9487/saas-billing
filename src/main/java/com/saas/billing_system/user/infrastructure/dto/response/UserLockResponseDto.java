package com.saas.billing_system.user.infrastructure.dto.response;

import com.saas.billing_system.user.domain.entity.User;

public record UserLockResponseDto(String userId, boolean locked) {

  public static UserLockResponseDto fromUser(User user) {
    return new UserLockResponseDto(user.getUserName(), !user.isAccountNonLocked());
  }
}
