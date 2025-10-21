package com.saas.billing_system.user.application.dto.response;

public record UserEmailVerificationResponseDto(String message, String email) {

  public static UserEmailVerificationResponseDto create(String message, String email) {
    return new UserEmailVerificationResponseDto(message, email);
  }

  public static UserEmailVerificationResponseDto success(String email) {
    return new UserEmailVerificationResponseDto("Email Verified Successfully", email);
  }
  public static UserEmailVerificationResponseDto generate(String email) {
    return new UserEmailVerificationResponseDto("Verification sent to your email", email);
  }
}
