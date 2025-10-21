package com.saas.billing_system.notification.domain.exception;

import com.saas.billing_system.shared.exception.BaseException;

public class GMailException extends BaseException {

  public GMailException(String message, String logMessage, String code) {
    super(message, logMessage, code);
  }

  public static GMailException create(String message, String code) {
    return new GMailException(message, "Failed to Email for the user", code);
  }

  public static GMailException failed() {
    return new GMailException("Failed to send verification email",
        "GMailException: Unable to send email via SMTP",
        "EMAIL_001");

  }

}
