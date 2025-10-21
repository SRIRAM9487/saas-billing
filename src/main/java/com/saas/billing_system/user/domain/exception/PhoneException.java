package com.saas.billing_system.user.domain.exception;

import com.saas.billing_system.shared.exception.BaseException;
import com.saas.billing_system.user.domain.exception.types.PhoneExceptionType;

public class PhoneException extends BaseException {

  public PhoneException(String message, String logMessage, String code) {
    super(message, logMessage, code);
  }

  public static PhoneException empty() {
    return new PhoneException(
        PhoneExceptionType.EMPTY_PHONE.getMessage(),
        "Attempted to process a phone number but it was null or empty.",
        PhoneExceptionType.EMPTY_PHONE.name());
  }

  public static PhoneException tooShort() {
    return new PhoneException(
        PhoneExceptionType.TOO_SHORT.getMessage(),
        "Phone number is too short: ",
        PhoneExceptionType.TOO_SHORT.name());
  }

  public static PhoneException tooLong() {
    return new PhoneException(
        PhoneExceptionType.TOO_LONG.getMessage(),
        "Phone number exceeds maximum allowed digits (16): ",
        PhoneExceptionType.TOO_LONG.name());
  }

  public static PhoneException invalidCharacters() {
    return new PhoneException(
        PhoneExceptionType.INVALID_CHARACTERS.getMessage(),
        "Phone number has invalid Characters ",
        PhoneExceptionType.INVALID_CHARACTERS.name());
  }

  public static PhoneException invalidCountryCode() {
    return new PhoneException(
        PhoneExceptionType.INVALID_COUNTRY_CODE.getMessage(),
        "Phone number has invalid Country Code ",
        PhoneExceptionType.INVALID_COUNTRY_CODE.name());
  }

  public static PhoneException invalidPhoneNumber() {
    return new PhoneException(
        PhoneExceptionType.INVALID_PHONE_NUMBER.getMessage(),
        "Phone number has invalid format or characters: ",
        PhoneExceptionType.INVALID_PHONE_NUMBER.name());
  }
}
