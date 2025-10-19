package com.saas.billing_system.user.domain.exception;

public enum InvalidPasswordType {

  ATLEAST_ONE_LOWERCASE_LETTER,
  ATLEAST_ONE_CAPITAL_LETTER,
  ATLEAST_ONE_NUMBER,
  ATLEAST_EIGHT_CHARACTERS,
  EMPTY_PASSWORD

}
