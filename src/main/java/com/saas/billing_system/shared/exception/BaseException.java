package com.saas.billing_system.shared.exception;

import java.time.LocalDateTime;
import java.util.UUID;

import com.saas.billing_system.shared.context.UserContextHolder;

public abstract class BaseException extends RuntimeException {

  private final String logMessage;
  private final UUID userId;
  private final LocalDateTime exceptionTime;
  private final String code;

  public BaseException(
      String message,
      String logMessage,
      String code) {
    super(message);
    this.logMessage = logMessage;
    this.code = code;
    this.userId = UserContextHolder.getUserId();
    this.exceptionTime = LocalDateTime.now();
  }

  public String getLogMessage() {
    return logMessage;
  }

  public UUID getUserId() {
    return userId;
  }

  public LocalDateTime getExceptionTime() {
    return exceptionTime;
  }

  public String getCode() {
    return code;
  }

}
