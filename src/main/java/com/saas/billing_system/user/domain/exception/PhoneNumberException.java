package com.saas.billing_system.user.domain.exception;

import com.saas.billing_system.shared.exception.BaseException;
import com.saas.billing_system.user.domain.exception.types.PhoneExceptionType;

public class PhoneNumberException extends BaseException {

  public PhoneNumberException(String message, String logMessage, String code) {
    super(message, logMessage, code);
  }

  public static PhoneNumberException empty() {
    return new PhoneNumberException(
        PhoneExceptionType.EMPTY_PHONE.getMessage(),
        "Attempted to process a phone number but it was null or empty.",
        PhoneExceptionType.EMPTY_PHONE.name());
  }

  public static PhoneNumberException tooShort() {
    return new PhoneNumberException(
        PhoneExceptionType.TOO_SHORT.getMessage(),
        "Phone number is too short: ",
        PhoneExceptionType.TOO_SHORT.name());
  }

  public static PhoneNumberException tooLong() {
    return new PhoneNumberException(
        PhoneExceptionType.TOO_LONG.getMessage(),
        "Phone number exceeds maximum allowed digits (16): ",
        PhoneExceptionType.TOO_LONG.name());
  }

  public static PhoneNumberException invalidCharacters() {
    return new PhoneNumberException(
        PhoneExceptionType.INVALID_CHARACTERS.getMessage(),
        "Phone number has invalid Characters ",
        PhoneExceptionType.INVALID_CHARACTERS.name());
  }

  public static PhoneNumberException invalidCountryCode() {
    return new PhoneNumberException(
        PhoneExceptionType.INVALID_COUNTRY_CODE.getMessage(),
        "Phone number has invalid Country Code ",
        PhoneExceptionType.INVALID_COUNTRY_CODE.name());
  }

  public static PhoneNumberException invalidPhoneNumber() {
    return new PhoneNumberException(
        PhoneExceptionType.INVALID_PHONE_NUMBER.getMessage(),
        "Phone number has invalid format or characters: ",
        PhoneExceptionType.INVALID_PHONE_NUMBER.name());
  }
}
