package com.saas.billing_system.user.infrastructure.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.saas.billing_system.user.domain.entity.User;

public record UserDetailsResponseDto(
    String userId,
    String userName,
    String email,
    boolean verified,
    String phoneNumber,
    boolean isEnabled,
    boolean islocked,
    LocalDateTime createdAt,
    LocalDateTime updateAt) {

  public static UserDetailsResponseDto fromUser(User user) {
    return new UserDetailsResponseDto(
        user.getId().id().toString(),
        user.getUserName(),
        user.getEmail().value(),
        user.isVerified(),
        user.getPhone().value(),
        user.isEnabled(),
        user.isLocked(),
        user.getCreatedAt(),
        user.getUpdatedAt());
  }

  public static List<UserDetailsResponseDto> fromUser(List<User> users) {
    return users.stream().map(UserDetailsResponseDto::fromUser).collect(Collectors.toList());
  }
}
