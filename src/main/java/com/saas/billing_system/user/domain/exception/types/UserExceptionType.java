package com.saas.billing_system.user.domain.exception.types;

public enum UserExceptionType {

  USER_NOT_FOUND("User not found"),
  USER_NOT_USER_NAME("User name not found");

  private final String message;

  UserExceptionType(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

}
