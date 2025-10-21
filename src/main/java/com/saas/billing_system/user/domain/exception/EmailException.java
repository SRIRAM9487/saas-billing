package com.saas.billing_system.user.domain.exception;

import com.saas.billing_system.shared.exception.BaseException;
import com.saas.billing_system.user.domain.exception.types.EmailExceptionType;

public class EmailException extends BaseException {

  public EmailException(String message, String logMessage, String code) {
    super(message, logMessage, code);
  }

  public static EmailException empty() {
    return new EmailException(
        EmailExceptionType.EMPTY_EMAIL.getMessage(),
        "Attempted to process an email but it was null or empty.",
        EmailExceptionType.EMPTY_EMAIL.name());
  }

  public static EmailException missingAtSymbol(String email) {
    return new EmailException(
        EmailExceptionType.MISSING_AT_SYMBOL.getMessage(),
        "Email missing \"@\": " + email,
        EmailExceptionType.MISSING_AT_SYMBOL.name());
  }

  public static EmailException multipleAtSymbols(String email) {
    return new EmailException(
        EmailExceptionType.MULTIPLE_AT_SYMBOLS.getMessage(),
        "Email contains multiple '@' symbols: " + email,
        EmailExceptionType.MULTIPLE_AT_SYMBOLS.name());
  }

  public static EmailException emptyLocalPart(String email) {
    return new EmailException(
        EmailExceptionType.EMPTY_LOCAL_PART.getMessage(),
        "Local part of email is empty: " + email,
        EmailExceptionType.EMPTY_LOCAL_PART.name());
  }

  public static EmailException emptyDomainPart(String email) {
    return new EmailException(
        EmailExceptionType.EMPTY_DOMAIN_PART.getMessage(),
        "Domain part of email is empty: " + email,
        EmailExceptionType.EMPTY_DOMAIN_PART.name());
  }

  public static EmailException invalidLocalChars(String email) {
    return new EmailException(
        EmailExceptionType.INVALID_LOCAL_CHARS.getMessage(),
        "Local part contains invalid characters: " + email,
        EmailExceptionType.INVALID_LOCAL_CHARS.name());
  }

  public static EmailException leadingOrTrailingDot(String email) {
    return new EmailException(
        EmailExceptionType.LEADING_OR_TRAILING_DOT.getMessage(),
        "Local part starts or ends with a dot: " + email,
        EmailExceptionType.LEADING_OR_TRAILING_DOT.name());
  }

  public static EmailException consecutiveDots(String email) {
    return new EmailException(
        EmailExceptionType.CONSECUTIVE_DOTS.getMessage(),
        "Local part contains consecutive dots: " + email,
        EmailExceptionType.CONSECUTIVE_DOTS.name());
  }

  public static EmailException invalidDomainChars(String email) {
    return new EmailException(
        EmailExceptionType.INVALID_DOMAIN_CHARS.getMessage(),
        "Domain part contains invalid characters: " + email,
        EmailExceptionType.INVALID_DOMAIN_CHARS.name());
  }

  public static EmailException invalidDomainFormat(String email) {
    return new EmailException(
        EmailExceptionType.INVALID_DOMAIN_FORMAT.getMessage(),
        "Domain format is invalid or incomplete: " + email,
        EmailExceptionType.INVALID_DOMAIN_FORMAT.name());
  }

}
