package com.saas.billing_system.user.domain.exception;

public enum InvalidPasswordType {

  ATLEAST_ONE_LOWERCASE_LETTER("Password must contain at least one lowercase letter."),
  ATLEAST_ONE_CAPITAL_LETTER("Password must contain at least one uppercase letter."),
  ATLEAST_ONE_NUMBER("Password must contain at least one number."),
  ATLEAST_EIGHT_CHARACTERS("Password must be at least 8 characters long."),
  EMPTY_PASSWORD("Password cannot be empty.");

  private final String message;

  InvalidPasswordType(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

}
