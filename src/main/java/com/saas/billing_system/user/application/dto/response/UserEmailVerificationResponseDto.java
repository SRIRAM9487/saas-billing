package com.saas.billing_system.user.application.dto.response;

public record UserEmailVerificationResponseDto(String email) {

  public static UserEmailVerificationResponseDto create(String email) {
    return new UserEmailVerificationResponseDto(email);
  }

}
