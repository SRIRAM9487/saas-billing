package com.saas.billing_system.user.domain.exception;

import com.saas.billing_system.shared.exception.BaseException;

public class InvalidPasswordException extends BaseException {

  private InvalidPasswordType type;

  public InvalidPasswordException(String message,
      String logMessage,
      InvalidPasswordType type) {
    super(message, logMessage);
    this.type = type;
  }

  public static InvalidPasswordException requireAtleastOneLowerCaseLetter() {
    return new InvalidPasswordException("Require atleast one lower case letter present in the password", "",
        InvalidPasswordType.ATLEAST_ONE_LOWERCASE_LETTER);
  }

  public static InvalidPasswordException requireAtleastOneCapitalLetter() {
    return new InvalidPasswordException("Require atleast one Capital present in the password", "",
        InvalidPasswordType.ATLEAST_ONE_CAPITAL_LETTER);
  }

  public static InvalidPasswordException requireAtleastOneNumber() {
    return new InvalidPasswordException("Require atleast one Number present in the password", "",
        InvalidPasswordType.ATLEAST_ONE_NUMBER);
  }

  public static InvalidPasswordException requireAtleastEightCharacter() {
    return new InvalidPasswordException("Require atleast Eight character present in the password", "",
        InvalidPasswordType.ATLEAST_EIGHT_CHARACTERS);
  }

  public static InvalidPasswordException isEmpty() {
    return new InvalidPasswordException("Password is Empty.", "", InvalidPasswordType.EMPTY_PASSWORD);
  }

}
