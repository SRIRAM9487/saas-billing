package com.saas.billing_system.user.application.dto.request;

import com.saas.billing_system.user.domain.entity.User;

public record UserRegisterRequestDto(
    String userName,
    String email,
    String phone,
    String role,
    String password) {

  public static User toUser(UserRegisterRequestDto requestDto) {
    return User.create(
        requestDto.userName(),
        requestDto.password(),
        requestDto.email(),
        requestDto.phone(),
        requestDto.role());
  }

}
