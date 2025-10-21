package com.saas.billing_system.user.domain.exception.types;

public enum EmailExceptionType {

  MISSING_AT_SYMBOL("Email must contain '@' symbol."),
  MULTIPLE_AT_SYMBOLS("Email must contain only one '@' symbol."),
  EMPTY_LOCAL_PART("Email local part (before '@') cannot be empty."),
  EMPTY_DOMAIN_PART("Email domain part (after '@') cannot be empty."),
  INVALID_LOCAL_CHARS("Local part contains invalid characters."),
  LEADING_OR_TRAILING_DOT("Local part cannot start or end with a dot."),
  CONSECUTIVE_DOTS("Local part cannot contain consecutive dots."),
  INVALID_DOMAIN_CHARS("Domain part contains invalid characters."),
  INVALID_DOMAIN_FORMAT("Domain format is invalid or incomplete (e.g., missing TLD)."),
  MISSING_TLD("Email domain is missing a top-level domain (e.g., '.com')."),
  EMPTY_EMAIL("Email cannot be null or empty.");

  private final String message;

  EmailExceptionType(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

}
