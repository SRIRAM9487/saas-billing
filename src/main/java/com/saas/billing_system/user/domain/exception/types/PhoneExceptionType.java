package com.saas.billing_system.user.domain.exception.types;

public enum PhoneExceptionType {

  EMPTY_PHONE("Phone number cannot be null or empty."),
  TOO_SHORT("Phone number is too short."),
  TOO_LONG("Phone number cannot exceed 16 digits."),
  INVALID_CHARACTERS("Phone number contains invalid characters."),
  INVALID_COUNTRY_CODE("Phone number has an invalid country code."),
  INVALID_PHONE_NUMBER("Invalid phone number.");

  private String message;

  PhoneExceptionType(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

}
