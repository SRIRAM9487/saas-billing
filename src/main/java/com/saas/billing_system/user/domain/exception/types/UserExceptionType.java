package com.saas.billing_system.user.domain.exception.types;

public enum UserExceptionType {
  USER_NOT_FOUND("User not found"),
  AUTHENTICATION_FAILED("Authentication failed"),
  EMAIL_NOT_VERIFIED("Email not verified"),
  INVALID_OTP("Invalid OTP"),
  USER_NAME_NOT_FOUND("User name not found");

  private final String message;

  UserExceptionType(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
