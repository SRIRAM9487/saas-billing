package com.saas.billing_system.user.domain.exception;

import com.saas.billing_system.shared.exception.BaseException;


public class InvalidPasswordException extends BaseException {

  public InvalidPasswordException(String message, String logMessage, String code) {
    super(message, logMessage,  code);
  }

  public static InvalidPasswordException create(String message, String logMessage, String code) {
    return new InvalidPasswordException(message, logMessage,  code);
  }

  public static InvalidPasswordException requireAtleastOneLowerCaseLetter() {
    return create(
        "Require atleast one lower case letter present in the password",
        InvalidPasswordType.ATLEAST_ONE_LOWERCASE_LETTER.getMessage(),
        InvalidPasswordType.ATLEAST_ONE_LOWERCASE_LETTER.name());
  }

  public static InvalidPasswordException requireAtleastOneCapitalLetter() {
    return create(
        "Require atleast one Capital present in the password",
        InvalidPasswordType.ATLEAST_ONE_CAPITAL_LETTER.getMessage(),
        InvalidPasswordType.ATLEAST_ONE_CAPITAL_LETTER.name());
  }

  public static InvalidPasswordException requireAtleastOneNumber() {
    return create(
        "Require atleast one Number present in the password",
        InvalidPasswordType.ATLEAST_ONE_NUMBER.getMessage(),
        InvalidPasswordType.ATLEAST_ONE_NUMBER.name());
  }

  public static InvalidPasswordException requireAtleastEightCharacter() {
    return create(
        "Require atleast Eight character present in the password",
        InvalidPasswordType.ATLEAST_EIGHT_CHARACTERS.getMessage(),
        InvalidPasswordType.ATLEAST_EIGHT_CHARACTERS.name());
  }

  public static InvalidPasswordException isEmpty() {
    return create(
        "Password is Empty.",
        InvalidPasswordType.EMPTY_PASSWORD.getMessage(),
        InvalidPasswordType.EMPTY_PASSWORD.name());
  }

}
