package com.saas.billing_system.user.domain.exception;

import com.saas.billing_system.shared.exception.BaseException;
import com.saas.billing_system.user.domain.exception.types.PasswordExceptionType;


public class PasswordException extends BaseException {

  public PasswordException(String message, String logMessage, String code) {
    super(message, logMessage,  code);
  }

  public static PasswordException create(String message, String logMessage, String code) {
    return new PasswordException(message, logMessage,  code);
  }

  public static PasswordException requireAtleastOneLowerCaseLetter() {
    return create(
        "Require atleast one lower case letter present in the password",
        PasswordExceptionType.ATLEAST_ONE_LOWERCASE_LETTER.getMessage(),
        PasswordExceptionType.ATLEAST_ONE_LOWERCASE_LETTER.name());
  }

  public static PasswordException requireAtleastOneCapitalLetter() {
    return create(
        "Require atleast one Capital present in the password",
        PasswordExceptionType.ATLEAST_ONE_CAPITAL_LETTER.getMessage(),
        PasswordExceptionType.ATLEAST_ONE_CAPITAL_LETTER.name());
  }

  public static PasswordException requireAtleastOneNumber() {
    return create(
        "Require atleast one Number present in the password",
        PasswordExceptionType.ATLEAST_ONE_NUMBER.getMessage(),
        PasswordExceptionType.ATLEAST_ONE_NUMBER.name());
  }

  public static PasswordException requireAtleastEightCharacter() {
    return create(
        "Require atleast Eight character present in the password",
        PasswordExceptionType.ATLEAST_EIGHT_CHARACTERS.getMessage(),
        PasswordExceptionType.ATLEAST_EIGHT_CHARACTERS.name());
  }

  public static PasswordException isEmpty() {
    return create(
        "Password is Empty.",
        PasswordExceptionType.EMPTY_PASSWORD.getMessage(),
        PasswordExceptionType.EMPTY_PASSWORD.name());
  }

}
