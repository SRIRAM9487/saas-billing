package com.saas.billing_system.user.domain.exception.types;

public enum PhoneExceptionType {

  INVALID_PHONE_NUMBER("Invalid Phone Number");

  private String message;

  PhoneExceptionType(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

}
